package Lexer;


public class Token {

    public final int tag;
    public String type;
    public boolean decl;

    public Token(int t) {
        tag = t;
    }

    @Override
    public String toString() {
        return "" + tag;
    }

}
