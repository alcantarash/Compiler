package Semantic;

import Lexer.Lexer;
import Util.Util;

public class SimpleExpr extends Parser {
    Term term;
    SimpleExprContinue simpleExprContinue;

    public SimpleExpr(Parser parser) {
        super(parser);
    }

    @Override
    public void analise() {
        term = new Term(this);
        term.analise();
        this.tipo = term.tipo;
        simpleExprContinue = new SimpleExprContinue(this);
        simpleExprContinue.analise();

        if (!simpleExprContinue.tipo.equals("void")) {
            if (!Util.isNumeric(term.tipo) || !Util.isNumeric(simpleExprContinue.tipo)) {
                if (!term.tipo.equals(simpleExprContinue.tipo)) {
                    System.out.println("Erro semantico na linha " + Lexer.line + ":\n" + "Os operandos e/ou operadores sao incompatíveis.");
                    erro();
                }
            }
        }
        if (Util.isNumeric(term.tipo) && Util.isNumeric(simpleExprContinue.tipo)) {
            this.tipo = Util.getNumericType(term.tipo, simpleExprContinue.tipo);
        } else {
            this.tipo = simpleExprContinue.tipo;
        }
    }
}
