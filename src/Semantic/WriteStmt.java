package Semantic;

import java.io.*;
import Lexer.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteStmt extends Parser{
    
    Writable writable;

	public WriteStmt(Parser head) {
		super(head);
	}

	@Override
	public void analise () {
        switch (token.tag) {
            case Tag.PRINT:
                {
                    try {
                        eat(Tag.PRINT);
                    } catch (IOException ex) {
                        Logger.getLogger(WriteStmt.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                {
                    try {
                        eat('(');
                    } catch (IOException ex) {
                        Logger.getLogger(WriteStmt.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                writable = new Writable(this);
                writable.analise();
                
                {
                    try {
                        eat(')');
                    } catch (IOException ex) {
                        Logger.getLogger(WriteStmt.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            
            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "Comando  (WRITE) encontrado.");
                erro();
        }
    }
}
