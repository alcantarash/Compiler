package Semantic;

import Lexer.Lexer;
import Lexer.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FactorA extends Parser {

    Factor factor;

    public FactorA(Parser parser) {
        super(parser);
    }

    @Override
    public void analise() {
        switch (token.tag) {
            case Tag.MINUS:
                try {
                    eat(Tag.MINUS);
                } catch (IOException ex) {
                    Logger.getLogger(FactorA.class.getName()).log(Level.SEVERE, null, ex);
                }

                factor = new Factor(this);
                factor.analise();
                if ((!factor.tipo.equals("integer")) && (!factor.tipo.equals("literal"))) {//Verificar
                    System.out.println("Erro Semântico na linha " + Lexer.line + ":\n" + "Operador númerico nao foi encontrado.");
                    erro();
                }
                this.tipo = factor.tipo;
                break;

            case Tag.NOT:
                try {
                    eat(Tag.NOT);
                } catch (IOException ex) {
                    Logger.getLogger(FactorA.class.getName()).log(Level.SEVERE, null, ex);
                }
                factor = new Factor(this);
                factor.analise();
                if (!factor.tipo.equals("bool")) {
                    System.out.println("Erro Semantico na linha " + Lexer.line + ":\n" + "Operador booleano nao foi encontrado.");
                    erro();
                }
                this.tipo = factor.tipo;
                break;

            case Tag.AP:
            case Tag.ID:
            case Tag.NUM:
            case Tag.LITERAL:
                factor = new Factor(this);
                factor.analise();
                this.tipo = factor.tipo;
                break;

            default:
                System.out.println("Erro sintatico na linha " + Lexer.line + ":\n" + "A expressao nao foi encontrada.");
                erro();
        }
    }
}
