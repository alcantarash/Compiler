package Semantic;

import Lexer.*;

/**
 *
 * @author Teste
 */
class Identifier extends Parser {

    public Identifier(Parser head) {
        super(head);
    }

    @Override
    public void analise() {

        switch (token.tag) {
            case Tag.ID:
                Token tok = (Token) ts.get(word.lexeme);//Conferir
                
                if (decl){
                    
                }
        }
    }

}
