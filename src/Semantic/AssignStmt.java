package Semantic;

import Lexer.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AssignStmt extends Parser {

    Identifier identifier;
    SimpleExpr simpleExpr;

    public AssignStmt(Parser parser) {
        super(parser);
    }

    @Override
    public void analise() {
        switch (token.tag) {
            case Tag.ID:
                identifier = new Identifier(this);
                identifier.analise();
                try {
                    eat(Tag.ASSIGN);
                } catch (IOException ex) {
                    Logger.getLogger(AssignStmt.class.getName()).log(Level.SEVERE, null, ex);
                }
            simpleExpr = new SimpleExpr(this);
            simpleExpr.analise();
            //Continuar
        }
    }

}
