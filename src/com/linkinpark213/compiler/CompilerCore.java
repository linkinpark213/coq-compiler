package com.linkinpark213.compiler;

import com.linkinpark213.compiler.analyzer.lexical.tokens.Token;
import com.linkinpark213.compiler.analyzer.semantic.Quad;
import com.linkinpark213.compiler.analyzer.syntactic.v.vn.Program;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ooo on 2017/6/2 0002.
 */
public class CompilerCore {
    public void printLexicalAnalysisResult(ArrayList<Token> tokens) {
        System.out.println("=============================");
        System.out.println("=        Token List         =");
        System.out.println("=============================");
        System.out.println(tokens);
        for (int i = 0; i < tokens.size(); i++) {
            System.out.println(tokens.get(i).fullString());
        }
        System.out.println("=============================");
    }

    public void printSyntacticalAnalysisResult(Program program) {
        System.out.println("=============================");
        System.out.println("=       Syntax Tree         =");
        System.out.println("=============================");
        PrintWriter printWriter = new PrintWriter(System.out);
        program.printSyntacticalAnalysisTree(0, printWriter);
        System.out.println("=============================");
    }

    public void printSemanticAnalysisResult(ArrayList<Quad> quadQueue) {
        System.out.println("=============================");
        System.out.println("=        Quad List          =");
        System.out.println("=============================");
        for (Quad quad : quadQueue) {
            System.out.println(quad.toString());
        }
        System.out.println("=============================");
    }

    public String readCode(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
                stringBuilder.append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
