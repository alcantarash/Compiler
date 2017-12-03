package Semantic;

import Lexer.Lexer;
import Lexer.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IfStmt extends Parser {

    Expression expression;
    StmtList stmtList;
    IfStmtContinue ifStmtContinue;

    public IfStmt(Parser parser) {
        super(parser);
    }

    @Override
    public void analise() {
        switch (token.tag) {
            case Tag.IF:
                try {
                    eat(Tag.IF);
                } catch (IOException ex) {
                    Logger.getLogger(IfStmtContinue.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    eat(Tag.AP);
                } catch (IOException ex) {
                    Logger.getLogger(IfStmtContinue.class.getName()).log(Level.SEVERE, null, ex);
                }

                expression = new Expression(this);
                expression.analise();

                try {
                    eat(Tag.FP);
                } catch (IOException ex) {
                    Logger.getLogger(IfStmtContinue.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    eat(Tag.THEN);
                } catch (IOException ex) {
                    Logger.getLogger(IfStmtContinue.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    stmtList = new StmtList(this);
                } catch (IOException ex) {
                    Logger.getLogger(IfStmt.class.getName()).log(Level.SEVERE, null, ex);
                }
                stmtList.analise();

                try {
                    eat(Tag.END);
                } catch (IOException ex) {
                    Logger.getLogger(IfStmtContinue.class.getName()).log(Level.SEVERE, null, ex);
                }

                ifStmtContinue = new IfStmtContinue(this);
                ifStmtContinue.analise();

                break;

            default:
                System.out.println("Erro sintatico na linha " + Lexer.line + ":\n" + "Comando 'IF' não encontrado.");
                erro();
        }
    }
}
