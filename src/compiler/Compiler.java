/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import compiler.Lexer.Lexer;
import java.io.IOException;

/**
 *
 * @author sheldon
 */
public class Compiler {

    
    public static void main(String[] args) throws IOException {
        String EOF = "@";//Fim de Arquivo
        //Endereço Código Fonte
        String enderecoCF = "C:\\Users\\liber\\OneDrive\\Documentos\\NetBeansProjects\\Compiler-master\\teste.txt";
        // TODO code application logic here        
        Lexer lexer = new Lexer(enderecoCF);
        String s = lexer.scan().toString();
        System.out.println("\nSequência de Tokens:\n");
        
         while (!s.equals(EOF)){
            System.out.println(s);
            s = lexer.scan().toString();
        }
        System.out.println("\nTabela de símbolos:\n");
        lexer.ImprimeSymbolTable();
        
        System.out.println("\nNÃO FORAM ENCONTRADOS ERROS LÉXICOS.");
    }
    
}
