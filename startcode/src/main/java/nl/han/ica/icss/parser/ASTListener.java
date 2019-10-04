package nl.han.ica.icss.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
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
	public void exitBool_literal(ICSSParser.Bool_literalContext ctx) {
		currentContainer.push(new BoolLiteral(ctx.getText()));
	}

	@Override
	public void exitColor_literal(ICSSParser.Color_literalContext ctx) {
		currentContainer.push(new ColorLiteral(ctx.getText()));
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
	public void exitScalar_literal(ICSSParser.Scalar_literalContext ctx) {
		currentContainer.push(new ScalarLiteral(ctx.getText()));
	}

	@Override
	public void exitId_selector(ICSSParser.Id_selectorContext ctx) {
		currentContainer.push(new IdSelector(ctx.getText()));
	}

	@Override
	public void exitClass_selector(ICSSParser.Class_selectorContext ctx) {
		currentContainer.push(new ClassSelector(ctx.getText()));
	}

	@Override
	public void exitTag_selector(ICSSParser.Tag_selectorContext ctx) {
		currentContainer.push(new TagSelector(ctx.getText()));
	}

	@Override
	public void exitProperty_name(ICSSParser.Property_nameContext ctx) {
		currentContainer.push(new PropertyName(ctx.getText()));
	}

	@Override
	public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
		currentContainer.push(new Declaration((Expression) currentContainer.pop(), (PropertyName) currentContainer.pop()));
//		Declaration declaration = new Declaration(ctx.getChild(0).getText());
//		declaration.addChild(currentContainer.pop());
//		currentContainer.push(declaration);
	}

	@Override
	public void exitStylerule(ICSSParser.StyleruleContext ctx) {
		ArrayList<ASTNode> body =  new ArrayList<>();

		Iterator<ASTNode> iterator = currentContainer.iterator();
		Selector selector = null;
		while(iterator.hasNext()) {
			ASTNode node = iterator.next();
			if(node instanceof Selector)
				selector = (Selector) node;
			else if(selector != null) {
				body.add(node);
				iterator.remove();
			}
		}
		currentContainer.pop();
		currentContainer.push(new Stylerule(selector, body));
	}

	@Override
	public void exitVariable_reference(ICSSParser.Variable_referenceContext ctx) {
		currentContainer.push(new VariableReference(ctx.getText()));
	}

	@Override
	public void exitVariable_assignment(ICSSParser.Variable_assignmentContext ctx) {
		currentContainer.push(new VariableAssignment((Expression) currentContainer.pop(), (VariableReference) currentContainer.pop()));
	}

	@Override
	public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
		ast.setRoot(new Stylesheet(new ArrayList<>(currentContainer)));
	}
}
