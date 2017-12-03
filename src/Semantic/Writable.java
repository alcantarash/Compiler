package Semantic;

public class Writable extends Parser{
     SimpleExpr simpleExpr;

	public Writable(Parser head) {
		super(head);
	}

	@Override
	public void analise () {
        simpleExpr = new SimpleExpr(this);
        simpleExpr.analise();
	}
}
