grammar ICSS;


//--- LEXER: ---
// IF support:
IF: 'if';
BOX_BRACKET_OPEN: '[';
BOX_BRACKET_CLOSE: ']';


//Literals
TRUE: 'TRUE';
FALSE: 'FALSE';
PIXELSIZE: [0-9]+ 'px';
PERCENTAGE: [0-9]+ '%';
SCALAR: [0-9]+;

//Color value takes precedence over id idents
COLOR: '#' [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f];

//Specific identifiers for id's and css classes
ID_IDENT: '#' [a-z0-9\-]+;
CLASS_IDENT: '.' [a-z0-9\-]+;

//General identifiers
LOWER_IDENT: [a-z0-9\-]+;
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_]*;

//All whitespace is skipped
WS: [ \t\r\n]+ -> skip;

//
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COLON: ':';
PLUS: '+';
MIN: '-';
MUL: '*';
ASSIGNMENT_OPERATOR: ':=';

//--- PARSER: ---

stylesheet: decleration* EOF;

decleration: stylerule
    | variable_declaration;

style_block: OPEN_BRACE statements CLOSE_BRACE;

stylerule: selector style_block;

statements: statement*;

statement: property_name COLON expression SEMICOLON #property_decleration
    | variable_declaration #variable_dec
    | if_clause #if;

property_name: LOWER_IDENT;

expression: literal
    | variable
    | expression PLUS expression
    | expression MIN expression
    | expression MUL expression;

variable_declaration: variable ASSIGNMENT_OPERATOR expression SEMICOLON;

if_clause: IF BOX_BRACKET_OPEN expression BOX_BRACKET_CLOSE style_block+;

variable: CAPITAL_IDENT;

boolean_literal: TRUE
    | FALSE;

size_literal: PIXELSIZE #pixel_literal
    | PERCENTAGE #percentage_literal;

literal: COLOR #color
    | boolean_literal #boolean
    | size_literal #size
    | SCALAR #scalar;

selector: ID_IDENT #id
    | CLASS_IDENT #class
    | LOWER_IDENT #tag;
