package Semantic;

import Lexer.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Program extends Parser {

    //Identifier identifier;
    public DeclList declList;
    public StmtList stmtList;

    public Program(Parser parser) {
        super(parser);
    }

    @Override
    public void analise() {

        try {
            eat(Tag.PROGRAM);
        } catch (IOException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        }

        if ((token.tag == Tag.INTEGER) || (token.tag == Tag.STRING)) {
            declList = new DeclList(this);
            declList.analise();

            stmtList = new stmtList(this);
            stmtList.analise();
        } else {
            stmtList = new stmtList(this);
            stmtList.analise();
        }
        
        try {
            eat(Tag.END);
        } catch (IOException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Analise Semantica concluida ^^ !");
    }

}
