package Semantic;

import Lexer.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Type extends Parser {

    public Type(Parser head) {
        super(head);
    }

    @Override
    public void analise() {
        switch (token.tag) {
            case Tag.INTEGER:
                try {
                    eat(Tag.INTEGER);
                } catch (IOException ex) {
                    Logger.getLogger(Type.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.tipo = "integer";
                break;
            case Tag.STRING:
                try {
                    eat(Tag.STRING);
                } catch (IOException ex) {
                    Logger.getLogger(Type.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.tipo = "literal";
                break;
            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "Tipo (INTEGER/STRING) nao encontrado");
                erro();
        }
    }

}
