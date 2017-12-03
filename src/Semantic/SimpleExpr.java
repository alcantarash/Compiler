package Semantic;

import Lexer.Lexer;
import Util.Util;

public class SimpleExpr extends Parser {
    Term term;
    SimpleExprContinue simpleExprExt;

    public SimpleExpr(Parser parser) {
        super(parser);
    }

    @Override
    public void analise() {
        term = new Term(this);
        term.analise();
        this.tipo = term.type;
        simpleExprExt = new SimpleExprContinue(this);
        simpleExprExt.analise();

        if (!simpleExprExt.type.equals("void")) {
            if (!Util.isNumeric(term.type) || !Util.isNumeric(simpleExprExt.type)) {
                if (!term.type.equals(simpleExprExt.type)) {
                    System.out.println("Erro semantico na linha " + Lexer.line + ":\n" + "Os operandos e operadores sao incompatíveis.");
                    erro();
                }
            }
        }
        if (Util.isNumeric(term.type) && Util.isNumeric(simpleExprExt.tipo)) {
            this.tipo = Util.getNumericType(term.tipo, simpleExprExt.tipo);
        } else {
            this.tipo = simpleExprExt.type;
        }
    }
}
