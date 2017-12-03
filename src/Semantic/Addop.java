package Semantic;

import java.io.*;
import Lexer.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Addop extends Parser {

    public Addop(Parser head) {
        super(head);
    }

    @Override
    public void analise() {
        switch (token.tag) {

            case Tag.SUM: {
                try {
                    eat(Tag.SUM);
                } catch (IOException ex) {
                    Logger.getLogger(Addop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            this.tipo = parser.tipo;
            break;

            case Tag.MINUS: {
                try {
                    eat(Tag.MINUS);
                } catch (IOException ex) {
                    Logger.getLogger(Addop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.tipo = parser.tipo;
            break;

            case Tag.OR: {
                try {
                    eat(Tag.OR);
                } catch (IOException ex) {
                    Logger.getLogger(Addop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.tipo = "bool";
            break;

            default:
                System.out.println("Erro sintatico na linha " + Lexer.line + ":\n" + "O Operador aritmetico nao foi encontrado.\nUse:('-', 'OR', '+') ");
                erro();
        }
    }
}
