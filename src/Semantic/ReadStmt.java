package Semantic;

import java.io.*;
import Lexer.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadStmt extends Parser{
    
    Identifier identifier;

	public ReadStmt(Parser head) {
		super(head);
	}

	@Override
	public void analise () {
        switch (token.tag) {

            case Tag.SCAN: 

                {                    
                    try {
                        eat(Tag.SCAN);
                    } catch (IOException ex) {
                        Logger.getLogger(ReadStmt.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                {
                    try {
                        eat('(');
                    } catch (IOException ex) {
                        Logger.getLogger(ReadStmt.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                identifier = new Identifier(this);
                identifier.analise();
                
                {
                    try {
                        eat(')');
                    } catch (IOException ex) {
                        Logger.getLogger(ReadStmt.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                break;
                
                default:
                    System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "Comando de entrada (READ) não foi encontrado.");
                    erro();
        }
	}
}
