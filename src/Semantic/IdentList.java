package Semantic;

import Lexer.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IdentList extends Parser {

    public Identifier identifier;

    public IdentList(Parser head) {
        super(head);
    }

    @Override
    public void analise() {
        switch (token.tag) {
            case Tag.ID:
                identifier = new Identifier(this);
                identifier.decl = true;
                identifier.analise();
                if (token.tag == Tag.VIR) {
                    this.tipo = parser.tipo;
                    try {
                        eat(Tag.VIR);
                    } catch (IOException ex) {
                        Logger.getLogger(IdentList.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    identifier.analise();
                }
                break;
            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "A Lista de identificadores não foi encontrada.");
                erro();
        }
    }
}
