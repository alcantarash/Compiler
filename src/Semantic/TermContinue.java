package Semantic;

import Lexer.Lexer;
import Lexer.Tag;
import Util.Util;

public class TermContinue extends Parser {

    Mulop mulop;
    FactorA factorA;
    TermContinue termContinue;

    public TermContinue(Parser parser) {
        super(parser);
        this.tipo = parser.tipo;
    }

    @Override
    public void analise() {
        switch (token.tag) {
            case Tag.MULT:
            case Tag.DIV:
            case Tag.AND:
                mulop.analise();
                this.tipo = mulop.tipo;

                factorA = new FactorA(this);
                factorA.analise();

                if (!Util.isNumeric(mulop.tipo) || !Util.isNumeric(factorA.tipo)) {
                    if (!mulop.tipo.equals(factorA.tipo)) {
                        System.out.println("Erro semantico na linha " + Lexer.line + ":\n" + "O operador e incompativel com o operando.");
                        erro();
                    }
                }

                termContinue = new TermContinue(this);
                termContinue.analise();

                if (!termContinue.tipo.equals("void")) {
                    if (!mulop.tipo.equals(termContinue.tipo)) {
                        System.out.println("Erro semantico na linha " + Lexer.line + ":\n" + "O operador e incompativel com o operando.");
                        erro();
                    }
                }
                if (Util.isNumeric(mulop.tipo)) {
                    this.tipo = Util.getNumericType(factorA.tipo, termContinue.tipo);
                } else {
                    this.tipo = mulop.type;
                } 
                break;                
        }
    }
}
