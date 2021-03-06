package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.semantic.QuadQueue;
import com.linkinpark213.compiler.analyzer.semantic.SymbolList;
import com.linkinpark213.compiler.analyzer.syntactic.TokenQueue;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Constant;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Separator;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Keyword;
import com.linkinpark213.compiler.error.semantic.SemanticError;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class CaseBlock extends VN {
    @Override
    public boolean analyze(TokenQueue tokenQueue, SymbolList symbolList) throws SemanticError {
        /*
        *  <Case Block> ::= case <Constant> : <Statement String>
        * */
        ArrayList<V> production = new ArrayList<V>();
        production.add(new Keyword("case"));
        production.add(new Constant());
        production.add(new Separator(":"));
        production.add(new StatementString());
        productions.add(production);
        return super.analyze(tokenQueue, symbolList);
    }

    public void semanticAction(QuadQueue quadQueue, SymbolList symbolList, Identifier identifier, int chain) {
        Constant constant = (Constant) children.get(1);
        StatementString statementString = (StatementString) children.get(3);

        Quad trueQuad = new Quad();
        Quad falseQuad = new Quad();
        Quad jumpQuad = new Quad();

        trueQuad.setOperator("je");
        trueQuad.setVariableA(identifier.getName());
        trueQuad.setVariableB("" + constant.getFormattedValue());
        trueQuad.setResult("0");
        quadQueue.add(trueQuad);

        falseQuad.setOperator("j");
        falseQuad.setVariableA("_");
        falseQuad.setVariableB("_");
        falseQuad.setResult("0");
        quadQueue.add(falseQuad);

        trueQuad.setResult("" + quadQueue.nxq());

        try {
            statementString.semanticAction(quadQueue, symbolList);
        } catch (SemanticError semanticError) {
            semanticError.printStackTrace();
        }

        falseQuad.setResult("" + (quadQueue.nxq() + 1));

        jumpQuad.setOperator("j");
        jumpQuad.setVariableA("_");
        jumpQuad.setVariableB("_");
        jumpQuad.setResult("" + chain);
        quadQueue.add(jumpQuad);
    }
}
