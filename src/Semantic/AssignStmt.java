package Semantic;

public class AssignStmt extends Parser{
    
    Identifier identifier;
    SimpleExpr simpleExpr;

	public AssignStmt(Parser parser) {
		super(parser);
	}

    @Override
    public void analise() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
