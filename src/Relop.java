package semantic;

import java.io.*;
import lexer.*;
import symbols.*;
import main.*;

import parser.*;

import Util.Util;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Relop extends Parser {

	public Relop(Parser head) {
		super(head);
	}

	@Override
	public void analysis () {
	    switch(token.tag) {
		     case Tag.EQ: {
	            
		    	try {
	                eat(Tag.EQ);
	            } catch (IOException ex) {
	               Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	        this.type = "bool";
			break;
			
		    case 'Tag.GR': {
	            
		    	try {
	                eat('Tag.GR');
	            } catch (IOException ex) {
	                Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	        this.type = "bool";
			break;
			
		    case Tag.GE: {
	            
		    	try {
	                eat(Tag.GE);
	            } catch (IOException ex) {
	                Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	        this.type = "bool";
			break;			
		 
 		    case 'Tag.LS': {
 	            
 		    	try {
 	                eat('Tag.LS');
 	            } catch (IOException ex) {
 	                Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
 	            }
 	        }
 	        this.type = "bool";
 			break;
			
		    case Tag.LE: {
	            
		    	try {
	                eat(Tag.LE);
	            } catch (IOException ex) {
	                Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	        this.type = "bool";
			break;

			case Tag.NE: {
	            
		    	try {
	                eat(Tag.NE);
	            } catch (IOException ex) {
	                Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	        this.type = "bool";
			break;
			
		
		    default:
	            System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "Operador relacional não foi encontrado.");
                error(token.toString());
	                
	    }

	}
}
