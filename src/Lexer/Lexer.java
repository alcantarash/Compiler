package Lexer;

import Symbols.SymbolsTable;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class Lexer {

    public static int line = 1;
    private char c = ' ';
    public SymbolsTable words = SymbolsTable.getSymbolsTable();

    private FileReader file;

    void reserve(Token w) {
      words.put(w);
   }

    public Lexer(String filename) {
        try {
            file = new FileReader(filename);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        reserve(new Token("program", Tag.PROGRAM));
        reserve(new Token("end", Tag.END));
        reserve(new Token("int", Tag.INTEGER));
        reserve(new Token("string", Tag.STRING));
        reserve(new Token("if", Tag.IF));
        reserve(new Token("then", Tag.THEN));
        reserve(new Token("else", Tag.ELSE));
        reserve(new Token("do", Tag.DO));
        reserve(new Token("while", Tag.WHILE));
        reserve(new Token("scan", Tag.SCAN));
        reserve(new Token("print", Tag.PRINT));
    }

    //Lê o próximo Caracter
    private void readch() throws IOException {
        c = (char) file.read();
    }

    private boolean readch(char ch) throws IOException {
        readch();
        if (c != ch) {
            return false;
        }
        c = ' ';
        return true;
    }

    //Trata o token Literal
    private Token getLiteral() throws IOException {
        StringBuilder sl = new StringBuilder();
        do {
            sl.append(c);
            readch();
            if (c == '\n') {
                System.out.println("\nErro na linha " + line + ". Não é permitido quebra de linhas em literais.");
                System.exit(0);
            }
        } while (c != '"');
        sl.append(c);
        readch();
        String s = sl.toString();
        Token w = new Token(s, Tag.LITERAL);
        return w;
    }

    //Imprime tabela de simbolos
    /*public void ImprimeSymbolTable() {

        Set<String> lexemas = words.keySet();
        lexemas.stream().filter((lexema) -> (lexema != null)).forEach((lexema) -> {
            System.out.println(lexema + ", ID = " + words.get(lexema).tag);
        });
    }*/

    public Token scan() throws IOException {

        char aux = ' ';
        OUTER:
        for (;; readch()) {
            switch (c) {
                case ' ':
                case '\t':
                case '\r':
                case '\b':
                    continue;
                // Desconsidera bloco de comentário
                case '/':
                    readch();
                    switch (c) {
                        case '*':
                            while (true) {
                                readch();
                                if (c == '*') {
                                    readch();
                                    if (c == '/') {
                                        break;
                                    }
                                } else if (c == '\uFFFF') {
                                    return Token.eof;
                                }
                            }
                            readch();
                            break;
                        // Desconsidera comentário de uma linha
                        case '/':
                            while ((c != '\n') && (c != '\uFFFF')) {
                                readch();
                            }
                            break;
                        //Operador de Divisão
                        default:
                            return Token.div;
                    }
                    break;
                case '\n':
                    line++;
                    break;
                default:
                    break OUTER;
            }
        }

        switch (c) {
            case '=':
                if (readch('=')) {
                    return Token.eq;
                } else {
                    return Token.assign;
                }
            case '>':
                if (readch('=')) {
                    return Token.le;
                } else {
                    return Token.gr;
                }
            case '<':
                if (readch('=')) {
                    return Token.ge;
                } else {
                    return Token.ls;
                }
            case '!':
                if (readch('=')) {
                    return Token.ne;
                } else {
                    return Token.not;
                }
            case '|':
                if (readch('|')) {
                    return Token.or;
                } else {
                    return new Token('|');
                }
            case '&':
                if (readch('&')) {
                    return Token.and;
                } else {
                    return new Token('&');
                }
            case '+':
                readch();
                return Token.sum;
            case '-':
                readch();
                return Token.minus;
            case '*':
                readch();
                return Token.mult;
            case '(':
                readch();
                return Token.ap;
            case ')':
                readch();
                return Token.fp;
            case ',':
                readch();
                return Token.vir;
            case ';':
                readch();
                return Token.pvir;
            case '"':
                return getLiteral();
        }
        //Digitos
        if (Character.isDigit(c)) {
            int value = 0;
            do {
                value = 10 * value + Character.digit(c, 10);
                readch();
                if (Character.isLetter(c)) {
                    System.out.println("Erro linha: " + line + " -> Digito inválido");
                    System.exit(0);
                }
            } while (Character.isDigit(c));
            return new Num(value);
        }

        //Identificadores
        if (Character.isLetter(c)) {
            StringBuffer sb = new StringBuffer();
            do {
                sb.append(c);
                readch();
            } while (Character.isLetterOrDigit(c));
            String s = sb.toString();
            Token w = (Token) words.get(s.toLowerCase());
            if (w != null) {
                return w; //palavra já existe na HashTable
            }
            w = new Token(s, Tag.ID);
            words.put(w);
            return w;
        }
        // Fim de arquivo
        if (c == '\uFFFF') {
            return Token.eof;
        }
        //Caracteres não pertencentes a linguagem
        if (!Character.isLetterOrDigit(c)) {
            System.out.println("Erro na linha " + line + ". Lexema '" + c + "' é inválido.");
            System.exit(0);
        }

        Token t = new Token(c);
        c = ' ';
        return t;
    }
}
