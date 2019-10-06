package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Operation;
import nl.han.ica.icss.ast.literals.ColorLiteral;

public class NoOperationsOnColorsChecker implements IChecker {

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
    }

    private void setErrorOnColorNode(ColorLiteral colorLiteral) {
        colorLiteral.setError("A color literal can not be used in an operation");
    }
}
