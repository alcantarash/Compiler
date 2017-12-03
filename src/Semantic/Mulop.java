package semantic;

import java.io.*;
import lexer.*;
import symbols.*;
import main.*;

import parser.*;

import Util.Util;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mulop extends Parser {

    public Mulop(Parser head) {
        super(head);
    }

    @Override
    public void analysis () {
        switch(token.tag) {
        
            case 'Tag.MULT': {
                
                try {
                    eat('Tag.MULT');
                } catch (IOException ex) {
                    Logger.getLogger(Mulop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.type = head.type;
            break;
            
            case 'Tag.DIV': {
                
                try {
                    eat('Tag.DIV');
                } catch (IOException ex) {
                    Logger.getLogger(Mulop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.type = head.type;
            break;
            
            case Tag.AND: {
                
                try {
                    eat(Tag.AND);
                } catch (IOException ex) {
                    Logger.getLogger(Mulop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.type = "bool";
            break;
            
            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n"+ "Operador aritmético nao foi encontrado.");
                error(token.toString());
        }
    }
}
