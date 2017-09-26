/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.Lexer;

/**
 *
 * @author sheldon
 */
public class Tag {
    
    public final static int
    
            PROGRAM = 256,
            END = 257,
            INTEGER = 258,
            STRING = 259,
            IF = 260,
            THEN = 261,
            ELSE = 262,
            DO = 263,
            WHILE = 264,
            SCAN = 265,
            PRINT = 266,
            
            ASSIGN = 267,
            EQ = 268,
            NE = 269,
            LE = 270,
            GE = 271,
            
            NOT = 272,
            AND = 273,
            OR = 274,
            
            DIGIT = 275,
            NUM = 276,
            LITERAL = 277,
            ID = 278,
    
            SUM = 279,
            MINUS = 280,
            MULT = 281,
            DIV = 282,
            
            AP = 283,
            FP = 284,
            VIR = 285,
            PVIR = 286,
            
            EOF = 287;
}
