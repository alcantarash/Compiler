package Semantic;

import Lexer.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Decl extends Parser {

    public Type type;
    public IdentList identList;

    public Decl(Parser parser) {
        super(parser);
    }

    public void setType(List<Token> idList, Type type) {

        for (Token w : idList) {
            w.type = type.tipo;
            w.decl = true;
        }
    }

    @Override
    public void analise() {
        switch (token.tag) {
            case Tag.INTEGER:
            case Tag.STRING:
                type = new Type(this);
                type.analise();
            case Tag.ID:
                identList = new IdentList(this);
                identList.analise();//Verificar pq está fora do case
                setType(idList, type);
                idList.clear();
            default:
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "A declaração de variaveis nao foi encontrada.");
                erro();
        }
    }
}
