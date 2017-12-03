package Semantic;

import Lexer.Lexer;
import Lexer.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Constant extends Parser {

    public Constant(Parser parser) {
        super(parser);
    }

    @Override
    public void analise() {
        switch (token.tag) {
            case Tag.NUM:
                try {
                    eat(Tag.NUM);
                } catch (IOException ex) {
                    Logger.getLogger(Constant.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.tipo = "int";
                break;

            case Tag.LITERAL:
                try {
                    eat(Tag.LITERAL);
                } catch (IOException ex) {
                    Logger.getLogger(Constant.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.tipo = "literal";
                break;

            default:
                System.out.println("Erro sintatico na linha " + Lexer.line + ":\n" + "O tipo constant nao foi encontrada.");
                erro();
        }
    }
}
