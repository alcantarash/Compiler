package Lexer;

public class Word extends Token {

    public String lexeme = "";

    public Word(String s, int tag) {
        super(tag);
        lexeme = s;
    }

    @Override
    public String toString() {
        return lexeme;
    }

    public static final Word eq = new Word("==", Tag.EQ),
            ge = new Word(">=", Tag.GE),
            gr = new Word(">", Tag.GR),
            le = new Word("<=", Tag.LE),
            ls = new Word("<", Tag.LS),
            ne = new Word("!=", Tag.NE),
            or = new Word("||", Tag.OR),
            and = new Word("&&", Tag.AND),
            not = new Word("!", Tag.NOT),
            assign = new Word("=", Tag.ASSIGN),
            sum = new Word("+", Tag.SUM),
            minus = new Word("-", Tag.MINUS),
            mult = new Word("*", Tag.MULT),
            div = new Word("/", Tag.DIV),
            ap = new Word("(", Tag.AP),
            fp = new Word(")", Tag.FP),
            vir = new Word(",", Tag.VIR),
            pvir = new Word(";", Tag.PVIR),
            eof = new Word("@", Tag.EOF);
}
