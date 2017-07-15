package com.linkinpark213.compiler.analyzer.syntactic.v.vn;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.syntactic.Quad;
import com.linkinpark213.compiler.analyzer.syntactic.v.V;
import com.linkinpark213.compiler.analyzer.syntactic.v.vt.Identifier;

import java.util.ArrayList;

/**
 * Created by ooo on 2017/7/3 0003.
 */
public class IdentifierString extends VN {
    @Override
    public boolean analyze(VN parent, ArrayList<Token> tokenQueue, ArrayList<Quad> quadQueue) {
        /*
        * <Identifier String> ::= <Identifier> <Identifier String Alter>
        *                       | <Identifier>
        * */
        ArrayList<V> production = new ArrayList<V>();
        production.add(new Identifier());
        production.add(new IdentifierStringAlter());
        productions.add(production);
        return super.analyze(parent, tokenQueue, quadQueue);
    }
}
