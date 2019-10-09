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

PLUS: '+';
MUL: '*';
MIN: '-';

//General identifiers
LOWER_IDENT: [a-z] [a-z0-9\-]*;
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_]*;

//
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COLON: ':';
ASSIGNMENT_OPERATOR: ':=';

COMMA: ',';
SIBLINGS: '>';
IMMEDIATE_PARENT: '~';

//All whitespace is skipped
WS: [ \t\r\n]+ -> skip;

//--- PARSER: ---

stylesheet: body EOF;

body: statement*;
statement: variable_assignment
    | stylerule
    | declaration
    | if_clause;


bool_literal: TRUE
    | FALSE;
color_literal: COLOR;
percentage_literal: PERCENTAGE;
pixel_literal: PIXELSIZE;
scalar_literal: SCALAR;

literal: bool_literal
    | color_literal
    | percentage_literal
    | pixel_literal
    | scalar_literal;

multiply_operation: MUL;
add_operation: PLUS;
subtract_operation: MIN;

expression: left=expression multiply_operation right=expression
    | left=expression add_operation right=expression
    | left=expression subtract_operation right=expression
    | literal
    | variable_reference;


selector_composition_operator: SIBLINGS
    | PLUS
    | IMMEDIATE_PARENT
    | COLON COLON
    | COLON;

class_selector: CLASS_IDENT;
id_selector: ID_IDENT;
tag_selector: LOWER_IDENT;

selectors: selector
    | seperated_selector* selector;

seperated_selector: selector COMMA;

selector: class_selector
    | id_selector
    | tag_selector
    | left=selector selector_composition_operator right=selector;

property_name: LOWER_IDENT;
declaration: property_name COLON expression SEMICOLON;

scope: OPEN_BRACE body CLOSE_BRACE;

if_clause: IF BOX_BRACKET_OPEN expression BOX_BRACKET_CLOSE scope;

stylerule: selectors scope;

variable_assignment: variable_reference ASSIGNMENT_OPERATOR expression SEMICOLON;
variable_reference: CAPITAL_IDENT;