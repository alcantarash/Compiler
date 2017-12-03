package Semantic;

import Lexer.*;
import Util.Util;
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
                /*Ainda não sei se isso ai embaixo vai prestar
                *
                */
                if (!Util.canAssign(identifier.tipo, simpleExpr.tipo)) {

                    System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Tipos de operandos incompatíveis.");
                    erro();
                }
                break;

            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "A Atribuição nao foi encontrada.");
                erro();
        }
    }

}
