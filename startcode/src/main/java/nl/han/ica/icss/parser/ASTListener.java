package nl.han.ica.icss.parser;

import java.util.ArrayList;
import java.util.Stack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {
	
	//Accumulator attributes:
	private AST ast;

	//Use this to keep track of the parent nodes when recursively traversing the ast
	private Stack<ASTNode> currentContainer;

	public ASTListener() {
		ast = new AST();
		currentContainer = new Stack<>();
	}
    public AST getAST() {
        return ast;
    }

	@Override
	public void exitBoolean_literal(ICSSParser.Boolean_literalContext ctx) {
		currentContainer.push(new BoolLiteral(ctx.getChild(0).getText()));
	}

	@Override
	public void exitId(ICSSParser.IdContext ctx) {
		currentContainer.push(new IdSelector(ctx.getText()));
	}

	@Override
	public void exitClass(ICSSParser.ClassContext ctx) {
		currentContainer.push(new ClassSelector(ctx.getText()));
	}

	@Override
	public void exitTag(ICSSParser.TagContext ctx) {
		currentContainer.push(new TagSelector(ctx.getText()));
	}

    @Override
    public void exitProperty_name(ICSSParser.Property_nameContext ctx) {
        currentContainer.push(new PropertyName(ctx.getText()));
    }

    @Override
    public void exitPercentage_literal(ICSSParser.Percentage_literalContext ctx) {
        currentContainer.push(new PercentageLiteral(ctx.getText()));
    }

    @Override
    public void exitPixel_literal(ICSSParser.Pixel_literalContext ctx) {
        currentContainer.push(new PixelLiteral(ctx.getText()));
    }

    @Override
    public void exitColor(ICSSParser.ColorContext ctx) {
        currentContainer.push(new ColorLiteral(ctx.getText()));
    }

    @Override
    public void exitProperty_decleration(ICSSParser.Property_declerationContext ctx) {
        currentContainer.push(new Declaration((Expression) currentContainer.pop(), (PropertyName)currentContainer.pop()));
    }

    //	@Override
//	public void exitStylerule(ICSSParser.StyleruleContext ctx) {
//		currentContainer.push(
//				new Stylerule(
//						ctx.getChild(ctx.getChildCount() - 1),
//
//				)
//		);
//	}

	@Override
	public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
		ast.setRoot(new Stylesheet(new ArrayList<>(currentContainer)));
	}
}
