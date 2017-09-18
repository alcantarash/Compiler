/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.Lexer;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
        words.put(w.lexeme,w);//Conferir
    }

    public Lexer(String filename) {
        try {
            file = new FileReader(filename);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        
        reserve (new Word("program", Tag.PROGRAM));
        reserve(new Word("end", Tag.END));
    }

}
