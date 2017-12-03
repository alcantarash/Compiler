package Semantic;

import Lexer.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StmtSuffix extends Parser {

    Expression expression;
    StmtList stmtList;

    public StmtSuffix(Parser parser) {
        super(parser);
    }

    @Override
    public void analise() {
        try {
            eat(Tag.WHILE);
        } catch (IOException ex) {
            Logger.getLogger(WhileStmt.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            eat(Tag.AP);
        } catch (IOException ex) {
            Logger.getLogger(WhileStmt.class.getName()).log(Level.SEVERE, null, ex);
        }

        expression = new Expression(this);
        expression.analise();

        try {
            eat(Tag.FP);
        } catch (IOException ex) {
            Logger.getLogger(WhileStmt.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            eat(Tag.END);
        } catch (IOException ex) {
            Logger.getLogger(StmtSuffix.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
