package Semantic;

import Lexer.Lexer;

public class Expression extends Parser {

    SimpleExpr simpleExpr;
    ExpressionContinue expressionContinue;

    public Expression(Parser parser) {
        super(parser);
    }

    @Override
    public void analise() {
        simpleExpr = new SimpleExpr(this);
        simpleExpr.analise();

        expressionContinue = new ExpressionContinue(this);
        expressionContinue.analise();

        if (!expressionContinue.tipo.equals("void")) {
            if (!simpleExpr.tipo.equals(expressionContinue.tipo)) {
                System.out.println("Erro semântico na linha " + Lexer.line + ":\n" + "Os tipos de operandos sao incompativeis");
                erro();
            }
            this.tipo = "bool";
        } else {
            this.tipo = simpleExpr.tipo;
        }
    }
}
