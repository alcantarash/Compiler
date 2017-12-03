package Semantic;

import Lexer.Lexer;
import Lexer.Tag;
import Util.Util;

public class SimpleExprContinue extends Parser {

    Addop addop;
    Term term;
    SimpleExprContinue simpleExprContinue;

    public SimpleExprContinue(Parser parser) {
        super(parser);
        this.tipo = parser.tipo;
    }

    @Override
    public void analise() {

        switch (token.tag) {
            case Tag.SUM:
            case Tag.MINUS:
            case Tag.OR:
                addop = new Addop(this);
                addop.analise();
                this.tipo = addop.tipo;
                term = new Term(this);
                term.analise();

                if (!Util.isNumeric(addop.tipo) || !Util.isNumeric(term.tipo)) {
                    if (!addop.tipo.equals(term.tipo)) {
                        System.out.println("Erro semantico na linha " + Lexer.line + ":\n" + "Operador incompativel com o operando.");
                        erro();
                    }
                }
        }
    }
}
