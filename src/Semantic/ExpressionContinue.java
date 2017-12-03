package Semantic;

import Lexer.Lexer;
import Lexer.Tag;

public class ExpressionContinue extends Parser {

    Relop relop;
    Expression expression;

    public ExpressionContinue(Parser parser) {
        super(parser);
    }

    @Override
    public void analise() {
        switch (token.tag) {
            case Tag.EQ:
            case Tag.GR:
            case Tag.GE:
            case Tag.LS:
            case Tag.LE:
            case Tag.NE:
                relop = new Relop(this);
                relop.analise();

                expression = new Expression(this);
                expression.analise();

                if ((!expression.tipo.equals("integer")) && (!expression.tipo.equals("literal"))) {
                    System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Tipo de operador incompativel.");
                    erro();
                }
                this.tipo = expression.tipo;
        }
    }

}
