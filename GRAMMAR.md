program		::= program [decl-list] stmt-list end
decl-list	::= decl {decl}
decl 		::= type ident-list ";"
ident-list 	::= identifier {"," identifier}
type 		::= int | string

stmt-list	::= stmt {stmt}
stmt		::= assign-stmt ";"   |   if-stmt   |  while-stmt   | read-stmt";"   |   write-stmt";"
assign-stmt	::= identifier "="   simple_expr
if-stmt		::= if condition   then stmt-list   end | if condition   then   stmt-list else   stmt-list   end
condition 	::= expression

while-stmt	::= do   stmt-list stmt-sufix
stmt-sufix	::= while   condition end

read-stmt	::= scan   "(" identifier ")"
write-stmt	::= print  "(" writable ")"
writable	::= simple-expr   | literal

expression	::= simple-expr   |   simple-expr   relop   simple-expr
simple-expr	::= term   | simple-expr   addop   term
term		::= factor-a   |   term   mulop   factor-a
fator-a		::= factor   |   ! factor   |   "-"   factor
factor		::= identifier   | constant   |   "("   expression   ")"
relop		::= "=="  |  ">"  |  ">="  |  "<"  |  "<="  | "!="
addop 		::= "+"  |  "-"  |  "||"
mulop		::=  "*"  |  "/"  |  "&&"
constant	::= integer_const  | literal
integer_const	::= digit  {digit}
literal		::= "“" {caractere} "”"
identifier	::= letter {letter | digit}
letter		::= [A-Za-z]
digit		::= [0-9]
caractere	::= um dos caracteres ASCII, exceto “” e quebra de linha
