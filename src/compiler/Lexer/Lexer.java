/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.Lexer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

/**
 *
 * @author sheldon
 */
public class Lexer {

    public static int line = 1;
    private char c = ' ';
    private Hashtable words = new Hashtable();
    private FileReader file;

    void reserve(Word w) {
        words.put(w.lexeme, w);//Conferir
    }

    public Lexer(String filename) {
        try {
            file = new FileReader(filename);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        reserve(new Word("program", Tag.PROGRAM));
        reserve(new Word("end", Tag.END));
        reserve(new Word("integer", Tag.INTEGER));
        reserve(new Word("string", Tag.STRING));
        reserve(new Word("if", Tag.IF));
        reserve(new Word("then", Tag.THEN));
        reserve(new Word("else", Tag.ELSE));
        reserve(new Word("do", Tag.DO));
        reserve(new Word("while", Tag.WHILE));
        reserve(new Word("scan", Tag.SCAN));
        reserve(new Word("print", Tag.PRINT));
    }

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

    public Token scan() throws IOException {

        for (;; readch()) {
            if (c == ' ' || c == '\t' || c == '\r' || c == '\b') {
                continue;
            } else if (c == '\n') {
                line++;
            } else {
                break;
            }
        }
        //Conferir se está correto
        switch (c) {
            case '=':
                if (readch('=')) {
                    return Word.eq;
                } else {
                    return Word.assign;
                }

            case '>':
                if (readch('=')) {
                    return Word.le;
                } else {
                    return new Token('>');
                }
            case '<':
                if (readch('=')) {
                    return Word.ge;
                } else {
                    return new Token('<');
                }
            case '!':
                if (readch('=')) {
                    return Word.ne;
                } else {
                    return Word.not;
                }
            case '|':
                if (readch('|')) {
                    return Word.or;
                } else {
                    return new Token('|');
                }
            case '&':
                if (readch('&')) {
                    return Word.and;
                } else {
                    return new Token('&');
                }
        }

        if (Character.isDigit(c)) {
            int value = 0;
            do {
                value = 10 * value + Character.digit(c, 10);
                readch();
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
            Word w = (Word) words.get(s);
            if (w != null) {
                return w; //palavra já existe na HashTable
            }
            w = new Word(s, Tag.ID);
            words.put(s, w);
            return w;
        }

        Token t = new Token(c);
        c = ' ';
        return t;
    }
}
