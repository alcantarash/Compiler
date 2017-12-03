package Syntactic;

import Lexer.*;
import java.io.IOException;

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

        if (this.token == null) {
            System.out.println("\nErro na linha " + lex.line + ": Final de arquivo inesperado");
        } else if (this.token.tag == 0) {
            System.out.println("\nErro na linha " + lex.line + ": Final de arquivo inesperado");
        } else {
            System.err.println("\nErro na linha " + Lexer.line + ": Token " + token.toString() + " não esperado.");
        }
        System.exit(0);
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
        switch (token.tag) {
            case Tag.INTEGER:
            case Tag.STRING:
                decl();
                declList();
                break;
            case Tag.ID:
            case Tag.DO:
            case Tag.IF:
            case Tag.SCAN:
            case Tag.PRINT:
                stmtList();
                break;
            default:
                error(token.toString());
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
                stmtList();
                break;
        }
    }

    //stmt ::= assign-stmt ";" | if-stmt | while-stmt | read-stmt";" | write-stmt";"
    private void stmt() throws IOException {
        switch (token.tag) {
            case Tag.ID:
                assignStmt();
                eat(Tag.PVIR);
                break;
            case Tag.IF:
                ifStmt();
                break;
            case Tag.DO:
                whileStmt();
                break;
            case Tag.SCAN:
                readStmt();
                eat(Tag.PVIR);
                break;
            case Tag.PRINT:
                writeStmt();
                eat(Tag.PVIR);
                break;
            default:
                error(token.toString());
        }
    }

    //assign-stmt ::= identifier "=" simple_expr
    private void assignStmt() throws IOException {
        switch (token.tag) {// identifier ":=" simple_expr
            case Tag.ID:
                identifier();
                eat(Tag.ASSIGN);
                simpleExpr();
                break;
        }
    }

    //simple-expr ::= term | simple-expr-conitnue addop term
    private void simpleExpr() throws IOException {
        term();
        simpleExprContinue();
    }

    //simple-expr-continue ::= @ | addop term simple-expr-continue
    private void simpleExprContinue() throws IOException {
        switch (token.tag) {
            case Tag.SUM:
            case Tag.MINUS:
            case Tag.OR:
                addop();
                term();
                simpleExprContinue();
        }
    }

    //addop ::= "+"  |  "-"  |  "||"
    private void addop() throws IOException {
        switch (token.tag) {
            case Tag.SUM:
                eat(Tag.SUM);
                break;
            case Tag.MINUS:
                eat(Tag.MINUS);
                break;
            case Tag.OR:
                eat(Tag.OR);
                break;
            default:
                error(token.toString());
        }
    }

    //term ::= factor-a term-continue
    private void term() throws IOException {
        factorA();
        termContinue();
    }

    //term-coninue ::= @ | mulop factor-a term-continue 
    private void termContinue() throws IOException {
        switch (token.tag) {
            case Tag.MULT:
            case Tag.DIV:
            case Tag.AND:
                mulop();
                factorA();
                termContinue();
                break;
        }
    }

    //mulop ::= "*" | "/" | "&&"
    private void mulop() throws IOException {
        switch (token.tag) {
            case Tag.MULT:
                eat(Tag.MULT);
                break;
            case Tag.DIV:
                eat(Tag.DIV);
                break;
            case Tag.AND:
                eat(Tag.AND);
                break;
            default:
                error(token.toString());
        }
    }

    //fator-a ::= factor | ! factor | "-" factor
    private void factorA() throws IOException {
        switch (token.tag) {
            case Tag.NOT:
                factor();
                break;
            case Tag.MINUS:
                factor();
                break;
            case Tag.AP:
            case Tag.ID:
            case Tag.NUM:
            case Tag.LITERAL:
                factor();
                break;
            default:
                error(token.toString());
        }
    }

    //factor ::= identifier | constant | "(" expression ")"
    private void factor() throws IOException {
        switch (token.tag) {
            case Tag.ID:
                identifier();
                break;
            case Tag.NUM:
            case Tag.LITERAL:
                constant();
                break;
            case Tag.AP:
                eat(Tag.AP);
                expression();
                eat(Tag.FP);
                break;
            default:
                error(token.toString());
        }
    }

    //constant	::= integer_const | literal
    private void constant() throws IOException {
        switch (token.tag) {
            case Tag.NUM://integer_const ::= digit  {digit}
                eat(Tag.NUM);
                break;
            case Tag.LITERAL:
                eat(Tag.LITERAL);
                break;
            default:
                error(token.toString());
        }
    }

    //expression ::= simple-expr expression-continue
    private void expression() throws IOException {
        simpleExpr();
        expressionContinue();
    }

    //expression' ::= @ | relop simple-expr
    private void expressionContinue() throws IOException {
        switch (token.tag) {
            case Tag.EQ:
            case Tag.GR:
            case Tag.GE:
            case Tag.LS:
            case Tag.LE:
            case Tag.NE:
                relop();
                simpleExpr();
        }
    }

    //relop ::= "==" | ">" | ">=" | "<" | "<=" | "!="
    private void relop() throws IOException {
        switch (token.tag) {
            case Tag.EQ:
                eat(Tag.EQ);
                break;
            case Tag.GR:
                eat(Tag.GR);
                break;
            case Tag.GE:
                eat(Tag.GE);
                break;
            case Tag.LS:
                eat(Tag.LS);
                break;
            case Tag.LE:
                eat(Tag.LE);
                break;
            case Tag.NE:
                eat(Tag.NE);
                break;
            default:
                error(token.toString());
        }
    }

    //if-stmt ::= if condition then stmt-list end if-stmt'
    private void ifStmt() throws IOException {
        eat(Tag.IF);
        eat(Tag.AP);
        expression();
        eat(Tag.FP);
        eat(Tag.THEN);
        stmtList();
        eat(Tag.END);
        ifStmtContinue();
    }

    //if-stmt' 	::= @ | else stmt-list end
    private void ifStmtContinue() throws IOException {
        switch (token.tag) {
            case Tag.ELSE:
                eat(Tag.ELSE);
                stmtList();
                eat(Tag.END);
                break;
        }
    }

    //while-stmt ::= do   stmt-list stmt-sufix
    private void whileStmt() throws IOException {
        eat(Tag.DO);
        stmtList();
        stmtSuffix();
    }

    //stmt-sufix ::= while condition end
    private void stmtSuffix() throws IOException {
        eat(Tag.WHILE);
        eat(Tag.AP);
        expression();
        eat(Tag.FP);
        eat(Tag.END);//condition ::= expression
    }

    //read-stmt	::= scan "(" identifier ")"
    private void readStmt() throws IOException {
        eat(Tag.SCAN);
        eat(Tag.AP);
        identifier();
        eat(Tag.FP);
    }

    //write-stmt ::= print "(" writable ")"
    private void writeStmt() throws IOException {
        eat(Tag.PRINT);
        eat(Tag.AP);
        writable();
        eat(Tag.FP);
    }

    //literal	::= "“" {caractere} "”" 
    void literal() throws IOException {
        switch (token.tag) {
            case Tag.LITERAL:
                eat(Tag.LITERAL);
                break;
            default:
                error(token.toString());
        }
    }

    //writable ::= simple-expr | literal
    private void writable() throws IOException {
        switch (token.tag) {
            case Tag.ID:
            case Tag.SUM:
            case Tag.MINUS:
            case Tag.OR:
            case Tag.MULT:
            case Tag.DIV:
            case Tag.AND:
            case Tag.AP:
                simpleExpr();
                break;
            case Tag.LITERAL:
                literal();
                break;
        }
    }
}
