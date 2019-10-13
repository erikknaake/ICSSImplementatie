package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.AST;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {

    //Accumulator attributes:
    private AST ast;

    public ASTListener() {
        ast = new AST();
    }

    public AST getAST() {
        return ast;
    }

    @Override
    public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
        ast = new AST();
        ast.setRoot(StyleSheetFactory.make(ctx));
    }
}

