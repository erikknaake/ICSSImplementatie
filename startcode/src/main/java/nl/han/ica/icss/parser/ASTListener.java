package nl.han.ica.icss.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
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
    public void exitSelector(ICSSParser.SelectorContext ctx) {
        currentContainer.push(SelectorFactory.make(ctx));
    }

	@Override
	public void exitExpression(ICSSParser.ExpressionContext ctx) {
		ExpressionFactory.make(ctx);
	}

	@Override
	public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
	    currentContainer.push(DeclarationFactory.make(ctx));
	}

	@Override
	public void exitStylerule(ICSSParser.StyleruleContext ctx) {

		ArrayList<ASTNode> body =  new ArrayList<>();
		// TODO: kijken of dit mooier kan
		Iterator<ASTNode> iterator = currentContainer.iterator();
		Selector selector = null;
		while(iterator.hasNext()) {
			ASTNode node = iterator.next();
			if(node instanceof Selector) {
				selector = (Selector) node;
				iterator.remove();
			}
			else if(selector != null) {
				body.add(node);
				iterator.remove();
			}
		}
		currentContainer.push(new Stylerule(selector, body));
	}

	@Override
	public void exitVariable_assignment(ICSSParser.Variable_assignmentContext ctx) {
	    currentContainer.push(VariableAssignmentFactory.make(ctx));
	}

	@Override
	public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
		ast.setRoot(new Stylesheet(new ArrayList<>(currentContainer)));
	}

}
