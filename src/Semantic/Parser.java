package Semantic;

/**
 * Parser versao Analisador Semantico
 */
import Lexer.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Parser {

    public static Lexer lexer;
    public static Token token;
    public static Word word;
    public String tipo;
    public Parser parser;
    public boolean decl;//Ainda nao sei pq. P.s Aparentemente é utilizado no identifier
    public static List<Token> idList = new ArrayList<Token>();
    public static HashMap ts = lexer.getWords();

    protected Parser(Parser parser) {
        this.parser = parser;
        this.tipo = "void";
        this.decl = false;
    }

    protected void erro() {
        System.out.println("Erro! Verificação Abortada...");
        System.exit(0);
    }

    protected void eat(int tag) throws IOException {
        if (token.tag == tag) {
            token = lexer.scan();
        } else {
            System.err.println("\nErro na linha " + Lexer.line + ": Token " + token.toString() + " não esperado.");
            erro();
        }
    }

    public abstract void analise();

}
