/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.Lexer;

import java.util.Hashtable;

/**
 *
 * @author liber
 */
public class SymbolsTable{
	private final Hashtable tabela;
	private SymbolsTable prev;
	
	public SymbolsTable(SymbolsTable prev) {
		this.tabela = new Hashtable();
		this.prev = prev;
	}	
	public void put (Token w, int id) {
		tabela.put(w, id);
	}
	
	public int get (Token w) {
		int aux;
		for (SymbolsTable s = this; s != null; s = s.prev) {
			if (s.tabela.get(w) != null) {
				aux = (int) s.tabela.get(w);
				return aux;
			}
		}
		return 0;
	}
	
}
