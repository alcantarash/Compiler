/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

/**
 *
 * @author sheldon
 */
public class Word extends Token {

    public String lexeme = "";

    public Word(String s, int tag) {
        super(tag);
        lexeme = s;
    }

    public String toString() {
        return lexeme;
    }
    
    public static final Word{
        
        eq = new Word("==", )
    }
}
