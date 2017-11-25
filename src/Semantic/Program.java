/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Semantic;

import Lexer.*;
import Syntactic.*;
import java.io.IOException;

public class Program extends Parser {

    Identifier identifier;
    DeclList declList;
    StmtList stmtList;

    public Program(Parser parser) throws IOException {
        super(parser);
    }

}
