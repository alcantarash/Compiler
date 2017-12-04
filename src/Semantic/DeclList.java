package Semantic;

import Lexer.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeclList extends Parser {

    public Decl decl;
    public DeclList declList;

    public DeclList(Parser parser) {
        super(parser);
    }

    @Override
    public void analise() {
        if ((token.tag == Tag.INTEGER) || (token.tag == Tag.STRING)) {
            decl = new Decl(this);
            System.out.println(token.tag);
            decl.analise();
            try {
                eat(Tag.PVIR);
            } catch (IOException ex) {
                Logger.getLogger(DeclList.class.getName()).log(Level.SEVERE, null, ex);
            }

            declList = new DeclList(this);
            declList.analise();
        }
    }

}
