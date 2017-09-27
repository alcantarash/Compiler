/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import compiler.Lexer.Lexer;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author sheldon
 */
public class Compiler {
    
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String EOF = "@";//Fim de Arquivo
        int opc;
        //Endereço Código Fonte
        String endt1 = "teste1.txt";
        String endt2 = "teste2.txt";
        String endt3 = "teste3.txt";
        String endt4 = "teste4.txt";
        String endt5 = "teste5.txt";
        String endt6 = "teste6.txt";
        String end = "";
        
        System.out.println("***Compilador - Libério/Larissa/Sheldon***");
        System.out.println("[1] Teste 1");
        System.out.println("[2] Teste 2");
        System.out.println("[3] Teste 3");
        System.out.println("[4] Teste 4");
        System.out.println("[5] Teste 5");        
        System.out.println("[6] Teste 6");        
        System.out.println("[7] Sair");
        System.out.println("Digite o npumero do teste que deseja compilar:");
        opc = in.nextInt();        

        switch(opc){
            case 1: end = endt1;break;
            case 2: end = endt2;break;
            case 3: end = endt3;break;
            case 4: end = endt4;break;
            case 5: end = endt5;break;
            case 6: end = endt6;break;
            case 7: System.out.println("Você escolheu Sair");break;
            default: System.out.println("Opção Invalida");break;
        }

        Lexer lexer = new Lexer(end);
        String s = lexer.scan().toString();
        System.out.println("\nSequência de Tokens:\n");

         while (!s.equals(EOF)){
            System.out.println(s);
            s = lexer.scan().toString();
        }
        System.out.println("\nTabela de símbolos:\n");
        lexer.ImprimeSymbolTable();

        System.out.println("\nCompilado com Sucesso, Sem erros Léxicos.");
    }
    
}
