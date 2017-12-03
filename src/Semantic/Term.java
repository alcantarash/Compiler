package Semantic;

import Lexer.Lexer;
import Util.Util;

public class Term extends Parser {

    FactorA factorA;
    TermExt termExt;

    public Term(Parser parser) {
        super(parser);
    }

    @Override
    public void analise() {
        factorA = new FactorA(this);
        factorA.analise();
        this.tipo = factorA.tipo;
        termExt = new TermExt(this);
        termExt.analise();

        if (!termExt.tipo.equals("void")) {
            if (!Util.isNumeric(factorA.tipo) || !Util.isNumeric(termExt.tipo)) {
                if (!factorA.tipo.equals(termExt.tipo)) {
                    System.out.println("Erro semantico na linha " + Lexer.line + ":\n" + "O operador e incompativel com o operando.");
                    erro();
                }
            }
        }
        if (Util.isNumeric(termExt.tipo) && Util.isNumeric(factorA.tipo)) {
            this.tipo = Util.getNumericType(factorA.tipo, termExt.tipo);
        } else {
            this.tipo = termExt.tipo;
        }
    }
}
