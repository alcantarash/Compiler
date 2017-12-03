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
public class Relop extends Parser{
    	public Relop(Parser head) {
		super(head);
	}

	@Override
	public void analise () {
	    switch(token.tag) {
		    case Tag.EQ: {
	            
		    	try {
	                eat(Tag.EQ);
	            } catch (IOException ex) {
	                Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	        this.tipo = "bool";
			break;
			
		    case '>': {
	            
		    	try {
	                eat('>');
	            } catch (IOException ex) {
	                Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	        this.tipo = "bool";
			break;
			
		    case Tag.GE: {
	            
		    	try {
	                eat(Tag.GE);
	            } catch (IOException ex) {
	                Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	        this.tipo = "bool";
			break;			
		 
 		    case '<': {
 	            
 		    	try {
 	                eat('<');
 	            } catch (IOException ex) {
 	                Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
 	            }
 	        }
 	        this.tipo = "bool";
 			break;
			
		    case Tag.LE: {
	            
		    	try {
	                eat(Tag.LE);
	            } catch (IOException ex) {
	                Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	        this.tipo = "bool";
			break;

			case Tag.NE: {
	            
		    	try {
	                eat(Tag.NE);
	            } catch (IOException ex) {
	                Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	        this.tipo = "bool";
			break;
			
		
		    default:
	            System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "Operador relacional não foi encontrado.");
                erro();
	                
	    }

	}
}