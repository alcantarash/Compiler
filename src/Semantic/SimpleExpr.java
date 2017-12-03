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
        this.tipo = term.type;
        simpleExprContinue = new SimpleExprContinue(this);
        simpleExprContinue.analise();

        if (!simpleExprContinue.type.equals("void")) {
            if (!Util.isNumeric(term.type) || !Util.isNumeric(simpleExprContinue.type)) {
                if (!term.type.equals(simpleExprContinue.type)) {
                    System.out.println("Erro semantico na linha " + Lexer.line + ":\n" + "Os operandos e operadores sao incompatíveis.");
                    erro();
                }
            }
        }
        if (Util.isNumeric(term.type) && Util.isNumeric(simpleExprContinue.tipo)) {
            this.tipo = Util.getNumericType(term.tipo, simpleExprContinue.tipo);
        } else {
            this.tipo = simpleExprContinue.type;
        }
    }
}
