package Semantic;

import Lexer.Lexer;
import Util.Util;

public class Term extends Parser {

    FactorA factorA;
    TermContinue termContinue;

    public Term(Parser parser) {
        super(parser);
    }

    @Override
    public void analise() {
        factorA = new FactorA(this);
        factorA.analise();
        this.tipo = factorA.tipo;
        termContinue = new TermContinue(this);
        termContinue.analise();

        if (!termContinue.tipo.equals("void")) {
            if (!Util.isNumeric(factorA.tipo) || !Util.isNumeric(termContinue.tipo)) {
                if (!factorA.tipo.equals(termContinue.tipo)) {
                    System.out.println("Erro semantico na linha " + Lexer.line + ":\n" + "O operador e incompativel com o operando.");
                    erro();
                }
            }
        }
        if (Util.isNumeric(termContinue.tipo) && Util.isNumeric(factorA.tipo)) {
            this.tipo = Util.getNumericType(factorA.tipo, termContinue.tipo);
        } else {
            this.tipo = termContinue.tipo;
        }
    }
}
