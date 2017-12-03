package Semantic;

import Lexer.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IfStmtContinue extends Parser {

    StmtList stmtList;

    public IfStmtContinue(Parser parser) {
        super(parser);
    }

    @Override
    public void analise() {
        switch (token.tag) {
            case Tag.ELSE:
                try {
                    eat(Tag.ELSE);
                } catch (IOException ex) {
                    Logger.getLogger(IfStmtContinue.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    stmtList = new StmtList(this);
                } catch (IOException ex) {
                    Logger.getLogger(IfStmtContinue.class.getName()).log(Level.SEVERE, null, ex);
                }
                stmtList.analise();

                try {
                    eat(Tag.END);
                } catch (IOException ex) {
                    Logger.getLogger(IfStmtContinue.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
}
