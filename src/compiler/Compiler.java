/*
 * Compilador - Analisador Léxico
 **Integrantes:
 **Libério Afonso - 201522040544
 **Sheldon Goulart - 201012040496
 **Larissa Bicalho - 201512040304
 */
package compiler;

import Lexer.Lexer;
import Syntactic.Parser;
import java.io.IOException;
import java.util.Scanner;
import Semantic.*;

public class Compiler {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String EOF = "@";//Fim de Arquivo
        int opc;
        //Endereço Código Fonte
        String endt1 = "test/teste1.txt";
        String endt2 = "test/teste2.txt";
        String endt3 = "test/teste3.txt";
        String endt4 = "test/teste4.txt";
        String endt5 = "test/teste5.txt";
        String endt6 = "test/teste6.txt";
        String end = "";
        do {
            //Menu de Opções
            System.out.println("***Compilador - Libério/Larissa/Sheldon***");
            System.out.println("[1] Teste 1");
            System.out.println("[2] Teste 2");
            System.out.println("[3] Teste 3");
            System.out.println("[4] Teste 4");
            System.out.println("[5] Teste 5");
            System.out.println("[6] Teste 6");
            System.out.println("Digite o nu'mero do teste que deseja compilar:");
            opc = in.nextInt();

            switch (opc) {
                case 1:
                    end = endt1;
                    break;
                case 2:
                    end = endt2;
                    break;
                case 3:
                    end = endt3;
                    break;
                case 4:
                    end = endt4;
                    break;
                case 5:
                    end = endt5;
                    break;
                case 6:
                    end = endt6;
                    break;
                default:
                    System.out.println("Opção Invalida");
                    break;
            }
        } while (opc < 1 || opc > 6);

        /*Lexer lexer = new Lexer(end);
        String s = lexer.scan().toString();
        System.out.println("\nSequência de Tokens:\n");
        
        //Imprime Tokens
        while (!s.equals(EOF)){
            System.out.println(s);
            s = lexer.scan().toString();
        }
        System.out.println("\nTabela de símbolos:\n");
        lexer.ImprimeSymbolTable();

        System.out.println("\nCompilado com Sucesso, Sem erros Léxicos.");*/
        Lexer lex = new Lexer(end);
        Parser syn = new Parser(lex);
        syn.program();
        //Analisador Semântico
        Program program = new Program(null);
        program.analise();

    }

}
