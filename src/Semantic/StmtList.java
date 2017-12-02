package Semantic;

import Lexer.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StmtList extends Parser {

    public Stmt stmt;
    public StmtList stmtList;

    public StmtList(Parser parser) throws IOException {
        super(parser);
    }

    @Override
    public void analise() {
        switch (token.tag) {
            case Tag.ID:
            case Tag.DO:
            case Tag.IF:
            case Tag.SCAN:
            case Tag.PRINT:
                stmt = new Stmt(this);
                stmt.analise();
                 {
                    try {
                        stmtList = new StmtList(this);
                    } catch (IOException ex) {
                        Logger.getLogger(StmtList.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                stmtList.analise();
                break;
            default:
                System.out.println("Erro sintatico na linha " + Lexer.line + ":\n" + "Comando (IF/ASSIGN/DO/READ/WRITE) encontrado.");
                erro();
        }
    }
}
