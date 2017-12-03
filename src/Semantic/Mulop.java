package Semantic;

import Lexer.Lexer;
import Lexer.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mulop extends Parser {

    public Mulop(Parser parser) {
        super(parser);
    }

    @Override
    public void analise() {
        switch (token.tag) {
            case Tag.MULT: {
                try {
                    eat(Tag.MULT);
                } catch (IOException ex) {
                    Logger.getLogger(Mulop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.tipo = parser.tipo;
            break;

            case Tag.DIV: {
                try {
                    eat(Tag.DIV);
                } catch (IOException ex) {
                    Logger.getLogger(Mulop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.tipo = "bool";//??????
            break;

            default:
                System.out.println("Erro sintatico na linha " + Lexer.line + ":\n" + "Operador aritmetico nao foi encontrado.");
                erro();
        }
    }
}
