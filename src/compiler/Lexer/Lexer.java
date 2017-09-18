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

    /*public Token scan(){
    
    }*/
}
