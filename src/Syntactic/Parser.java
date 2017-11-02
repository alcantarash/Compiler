/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Syntactic;

import Lexer.*;
import java.io.IOException;

/**
 *
 * @author aluno
 */
public class Parser {

    public static Lexer lex;
    public static Token token;

    public Parser(Lexer lexer) throws IOException {
        lex = lexer;
        move();
    }

    void move() throws IOException {
        token = lex.scan();
        //lex.print(token);
    }

    void eat(int tag) throws IOException {
        if (token.tag == tag) {
            move();
        } else {
            error(token.toString());
        }
    }

    void error(String s) {
        System.out.println("Erro!!!");
        System.out.println(" -- linha " + this.lex.line);
        System.out.println(" -- token inválido: " + s);
        throw new Error();
    }

    //program 	::= program [decl-list] stmt-list end
    public void program() throws IOException {
        eat(Tag.PROGRAM);
        declList();
        stmtList();
        eat(Tag.END);
        System.out.println("Programa correto!\n");
    }

    //decl-list ::= decl {decl}
    private void declList() throws IOException {
        if (token.tag == Tag.ID) {
            decl();
            eat(Tag.PVIR);
            declList();
        }
    }

    //decl ::= type ident-list ";"
    private void decl() throws IOException {
        type();
        identList();
        eat(Tag.PVIR);
    }

    //type ::= int | string
    private void type() throws IOException {
        switch (token.tag) {
            case Tag.INTEGER:
                eat(Tag.INTEGER);
                break;

            case Tag.STRING:
                eat(Tag.STRING);
                break;

            default:
                error(token.toString());
        }
    }

    //ident-list ::= identifier {"," identifier}
    private void identList() throws IOException {
        identifier();
        if (token.tag == Tag.VIR) {
            eat(Tag.VIR);
            identList();
        }
    }

    //identifier ::= letter {letter | digit}
    private void identifier() throws IOException {

        switch (token.tag) {
            case Tag.ID:
                eat(Tag.ID);
                break;
            default:
                error(token.toString());
        }
    }

    //stmt-list ::= stmt {stmt}
    private void stmtList() throws IOException {
        switch (token.tag) {
            case Tag.ID:
            case Tag.DO:
            case Tag.IF:
            case Tag.SCAN:
            case Tag.PRINT:
                stmt();
                /*if (token.tag == Tag.ID) {
                    stmt();
                }*/
                break;

            default:
                error(token.toString());
        }
    }

    //stmt ::= assign-stmt ";" | if-stmt | while-stmt | read-stmt";" | write-stmt";"
    private void stmt() throws IOException {
        switch (token.tag) {
            case Tag.ID:
                assignStmt();
                break;
            case Tag.IF:
                ifStmt();
                break;
            case Tag.DO:
                doStmt();
                break;
            case Tag.READ:
                readStmt();
                break;
            case Tag.WRITE:
                writeStmt();
                break;
            default:
                error(token.toString());
        }
    }

    //assign-stmt ::= identifier "=" simple_expr
    private void assignStmt() throws IOException {
        eat(Tag.ID);
        eat(Tag.ASSIGN);
        simpleExpr();
    }

    //simple-expr ::= term | simple-expr-other addop term
    private void simpleExpr() throws IOException {
        term();
        simpleExprOther();
    }

    //term ::= factor-a | term mulop factor-a
    private void term() throws IOException {
        factorA();
        termExt();
    }

    //simple-expr-ext-> @ | addop term simple-expr-other
    private void simpleExprOther() throws IOException {
        switch (token.tag) {
            case '+':
            case '-':
            case Tag.OR:
                addop();
                term();
                simpleExprOther();
        }
    }

}
