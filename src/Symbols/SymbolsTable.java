package Symbols;

import java.util.*;
import Lexer.*;

public class SymbolsTable {

    public static SymbolsTable table;
    public HashMap<String, Token> hashMap;

    public SymbolsTable() {
        hashMap = new HashMap<String, Token>();
    }

    public static SymbolsTable getSymbolsTable() {
        if (table == null) {
            table = new SymbolsTable();
            return table;
        }
        return table;
    }
    //(String s, int tag)

    public void put(Token token) {
        hashMap.put(token.lexeme, token);
    }

    public Token get(String lexeme) {
        Token token = hashMap.get(lexeme);
        return token;
    }
}
