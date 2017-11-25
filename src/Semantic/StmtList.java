/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semantic;

import Lexer.Lexer;
import Syntactic.Parser;
import java.io.IOException;

public class StmtList extends Parser{
    
    public StmtList(Parser parser) throws IOException {
        super(parser);
    }
    
}
