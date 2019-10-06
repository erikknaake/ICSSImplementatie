package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Operation;
import nl.han.ica.icss.ast.literals.ColorLiteral;

public class NoOperationsOnColorsChecker extends AbstractChecker {
    @Override
    public void check(AST ast) {
        check(ast.root);
    }

    @Override
    public void check(ASTNode node) {
        if(node instanceof Operation) {
            Operation operation = (Operation) node;
            if(operation.lhs instanceof ColorLiteral) {
                setErrorOnColorNode((ColorLiteral) operation.lhs);
            }
            if(operation.rhs instanceof ColorLiteral) {
                setErrorOnColorNode((ColorLiteral) operation.lhs);
            }
        }
        checkChildren(node);
    }

    private void setErrorOnColorNode(ColorLiteral colorLiteral) {
        colorLiteral.setError("A color literal can not be used in an operation");
    }
}
