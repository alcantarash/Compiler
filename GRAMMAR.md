program		::= program [decl-list] stmt-list end <br />
decl-list	::= decl {decl} <br />
decl 		::= type ident-list ";" <br />
ident-list 	::= identifier {"," identifier} <br />
type 		::= int | string <br />

stmt-list	::= stmt {stmt} <br />
stmt		::= assign-stmt ";"   |   if-stmt   |  while-stmt   | read-stmt";"   |   write-stmt";" <br />

assign-stmt	::= identifier "="   simple_expr <br />
if-stmt 	::= if condition then stmt-list end if-stmt' /**/<br /> 
if-stmt' 	::= @ | else stmt-list end /**/<br />
condition 	::= expression <br />

while-stmt	::= do   stmt-list stmt-sufix <br />
stmt-sufix	::= while   condition end <br />

read-stmt	::= scan   "(" identifier ")" <br />
write-stmt	::= print  "(" writable ")" <br />
writable	::= simple-expr   | literal <br />

expression 	::= simple-expr expression' /**/ <br />
expression'	::= @ | relop simple-expr /**/ <br />
simple-expr	::= term simple-expr'  addop  term/**/ <br />
simple-expr'	::= @ | addop term simple-expr'/**/<br />
term		::= factor-a term' <br />
term'	 	::= @ | mulop factor-a term'<br />
fator-a		::= factor   |   ! factor   |   "-"   factor <br />
factor		::= identifier   | constant   |   "("   expression   ")" <br />
relop		::= "=="  |  ">"  |  ">="  |  "<"  |  "<="  | "!=" <br />
addop 		::= "+"  |  "-"  |  "||" <br />
mulop		::=  "*"  |  "/"  |  "&&" <br />
constant	::= integer_const  | literal <br />
integer_const	::= digit  {digit} <br />
literal		::= "“" {caractere} "”" <br />
identifier	::= letter {letter | digit} <br />
letter		::= [A-Za-z] <br />
digit		::= [0-9] <br />
caractere	::= um dos caracteres ASCII, exceto “” e quebra de linha <br />

OBS -> lambda == @ <br />
Gramática com fatoração e recursão a esquerda
