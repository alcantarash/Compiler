package Semantic;

import Lexer.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class Identifier extends Parser {

    public Identifier(Parser head) {
        super(head);
    }

    @Override
    public void analise() {

        switch (token.tag) {
            case Tag.ID:
                Token tok = symbolsTable.get(token.lexeme);//Conferir

                if (decl) {//Talvez mudar
                    this.tipo = parser.tipo;

                    if (tok != null) {
                        tok = new Token(token.lexeme, Tag.ID);
                        tok.type = this.tipo;
                        symbolsTable.put(tok);
                        idList.add(tok);
                    }
                } else {
                    if (!tok.decl) {
                        System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Variavel nao declarada: '" + token.lexeme + "'.");
                        erro();
                    }
                    this.tipo = tok.type;
                }
                try {
                    eat(Tag.ID);
                } catch (IOException ex) {
                    Logger.getLogger(Identifier.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "Identificador nao foi encontrado.");
        }
    }

}
