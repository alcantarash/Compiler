/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semantic;
/**
 *
 * @author liber
 */
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
