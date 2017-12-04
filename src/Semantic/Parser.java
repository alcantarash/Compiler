package Semantic;

/**
 * Parser versao Analisador Semantico
 */
import Lexer.Lexer;
import Lexer.Token;
import Lexer.Word;
import Symbols.SymbolsTable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Parser {

    public static Lexer lexer;
    public static Token token;
    public String tipo;
    public Parser parser;
    public boolean decl;//Ainda nao sei pq. P.s Aparentemente é utilizado no identifier e outras partes
    public boolean isDecl = false;
    public static SymbolsTable symbolsTable = SymbolsTable.getSymbolsTable();
    public static List<Token> idList = new ArrayList<Token>();

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
