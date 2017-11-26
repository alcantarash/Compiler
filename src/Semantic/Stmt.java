package Semantic;

import Lexer.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Stmt extends Parser {

    public AssignStmt assignStmt;
    public IfStmt ifStmt;
    public WhileStmt whileStmt;
    public ReadStmt readStmt;
    public WriteStmt writeStmt;

    public Stmt(Parser parser) {
        super(parser);
    }

    @Override
    public void analise() {
        switch (token.tag) {
            case Tag.ID:
                assignStmt = new AssignStmt(this);
                assignStmt.analise();
                try {
                    eat(Tag.PVIR);
                } catch (IOException ex) {
                    Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case Tag.IF:
                ifStmt = new IfStmt(this);
                ifStmt.analise();
                break;
            case Tag.DO:
                whileStmt = new WhileStmt(this);
                whileStmt.analise();
                break;
            case Tag.SCAN:
                readStmt = new ReadStmt(this);
                readStmt.analise();
                try {
                    eat(Tag.PVIR);
                } catch (IOException ex) {
                    Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case Tag.PRINT:
                writeStmt = new WriteStmt(this);
                writeStmt.analise();
                try {
                    eat(Tag.PVIR);
                } catch (IOException ex) {
                    Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "Comando (IF/ASSIGN/DO/SCAN/PRINT) encontrado.");
                erro();
        }
    }
}
