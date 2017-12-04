package Semantic;

import Lexer.*;
import java.util.List;

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
                isDecl = true;
                identList = new IdentList(this);
                identList.analise();//Verificar pq está fora do case
            setType(idList, type);
            idList.clear();
            break;
            default:
                System.out.println(token.tag);
                System.out.println("Erro sintático na linha " + Lexer.line + ":\n" + "A declaração de variaveis nao foi encontrada.");
                erro();
        }
    }
}
