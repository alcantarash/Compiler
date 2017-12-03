/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semantic;
import java.io.*;
import Lexer.*;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author liber
 */
public class Addop extends Parser{
    	public Addop(Parser head) {
		super(head);
	}

	@Override
	public void analise () {
        switch (token.tag) {
	    
        	case '+': {
        		try {
        			eat('+');
        		} catch (IOException ex) {
        			Logger.getLogger(Addop.class.getName()).log(Level.SEVERE, null, ex);
        		}
        	}
            
            this.tipo = parser.tipo;
            break;
	    
        	case '-': {
	            try {
	                eat('-');
	            } catch (IOException ex) {
	                Logger.getLogger(Addop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
            this.tipo = parser.tipo;
            break;
            
		    case Tag.OR: {
	            try {
	                eat(Tag.OR);
	            } catch (IOException ex) {
	                Logger.getLogger(Addop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
            this.tipo = "bool";
            break;
		    
		    default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "O Operador aritmético nao foi encontrado.\nUse:('-', 'OR', '+') ");
                erro(token.toString());
		}
	}
}
