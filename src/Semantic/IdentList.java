package Semantic;

import Lexer.*;

public class IdentList extends Parser {

    public Identifier identifier;

    public IdentList(Parser head) {
        super(head);
    }

    @Override
    public void analise() {
        switch (token.tag) {
            case Tag.ID:
                this.tipo = parser.tipo;
                identifier = new Identifier(this);
                identifier.decl = true;
                identifier.analise();
                break;
            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "A Lista de identificadores não foi encontrada.");
                erro();
        }
    }
}
