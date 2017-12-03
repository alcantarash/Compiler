package Semantic;

import Lexer.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WhileStmt extends Parser {

    Expression expression;
    StmtList stmtList;
    StmtSuffix stmtSuffix;

    public WhileStmt(Parser parser) {
        super(parser);
    }

    @Override
    public void analise() {
        try {
            eat(Tag.DO);
        } catch (IOException ex) {
            Logger.getLogger(WhileStmt.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            stmtList = new StmtList(this);
        } catch (IOException ex) {
            Logger.getLogger(WhileStmt.class.getName()).log(Level.SEVERE, null, ex);
        }
        stmtList.analise();

        stmtSuffix = new StmtSuffix(this);
        stmtSuffix.analise();
    }
}
