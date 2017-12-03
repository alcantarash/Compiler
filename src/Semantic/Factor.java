package Semantic;

import Lexer.Lexer;
import Lexer.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Factor extends Parser {

    Identifier identifier;
    Constant constant;
    Expression expression;

    public Factor(Parser parser) {
        super(parser);
    }

    @Override
    public void analise() {
        switch (token.tag) {
            case Tag.ID:
                identifier = new Identifier(this);
                identifier.analise();
                this.tipo = identifier.tipo;
                break;

            case Tag.NUM:
            case Tag.LITERAL:
                constant = new Constant(this);
                constant.analise();
                this.tipo = constant.tipo;
                break;

            case Tag.AP:
                try {
                    eat(Tag.AP);
                } catch (IOException ex) {
                    Logger.getLogger(Factor.class.getName()).log(Level.SEVERE, null, ex);
                }
                expression = new Expression(this);
                expression.analise();
                this.tipo = expression.tipo;
                try {
                    eat(Tag.FP);
                } catch (IOException ex) {
                    Logger.getLogger(Factor.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            default:
                System.out.println("Erro sintatico na linha " + Lexer.line + ":\n" + "A expressao nao foi encontrada.");
                erro();
        }
    }
}
