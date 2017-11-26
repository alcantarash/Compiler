package Semantic;

import Lexer.*;
import java.io.IOException;

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

                stmtList = new StmtList(this);
                stmtList.analise();
                break;
            default:
                System.out.println("Erro sintatico na linha " + Lexer.line + ":\n" + "Comando (IF/ASSIGN/DO/READ/WRITE) encontrado.");
                erro();
        }
    }
}
