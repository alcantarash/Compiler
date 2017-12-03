package semantic;

import java.io.*;
import lexer.*;
import symbols.*;
import main.*;

import Util.Util;

import parser.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadStmt extends Parser {

    Identifier identifier;

	public ReadStmt(Parser head) {
		super(head);
	}

	@Override
	public void analysis () {
        switch (token.tag) {

            case Tag.READ: 

                {                    
                    try {
                        eat(Tag.READ);
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
                identifier.analysis();
                
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
                    error(token.toString());
        }
	}
}
