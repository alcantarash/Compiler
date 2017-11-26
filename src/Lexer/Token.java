package Lexer;

public class Token {

    public final int tag;
    public String lexeme;
    public String type;
    public boolean decl;

    public Token(int t) {
        tag = t;
    }

    public Token(String lexeme, int tag) {
        this.tag = tag;
        this.lexeme = lexeme;
        this.type = "";
    }

    @Override
    public String toString() {
        return "" + tag;
    }

    public String getLexeme() {
        return lexeme;
    }

    public static final Token eq = new Word("==", Tag.EQ),
            ge = new Token(">=", Tag.GE),
            gr = new Token(">", Tag.GR),
            le = new Token("<=", Tag.LE),
            ls = new Token("<", Tag.LS),
            ne = new Token("!=", Tag.NE),
            or = new Token("||", Tag.OR),
            and = new Token("&&", Tag.AND),
            not = new Token("!", Tag.NOT),
            assign = new Token("=", Tag.ASSIGN),
            sum = new Token("+", Tag.SUM),
            minus = new Token("-", Tag.MINUS),
            mult = new Token("*", Tag.MULT),
            div = new Token("/", Tag.DIV),
            ap = new Token("(", Tag.AP),
            fp = new Token(")", Tag.FP),
            vir = new Token(",", Tag.VIR),
            pvir = new Token(";", Tag.PVIR),
            eof = new Token("@", Tag.EOF);

}
