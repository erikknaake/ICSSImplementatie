package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Operation;
import nl.han.ica.icss.ast.types.ExpressionType;

public class NoOperationsOnColorsChecker implements IChecker {

    @Override
    public void check(ASTNode node) {
        if (node instanceof Operation) {
            Operation operation = (Operation) node;
            if (operation.lhs.getType() == ExpressionType.COLOR) {
                setErrorOnColorNode(operation);
            }
            if (operation.rhs.getType() == ExpressionType.COLOR) {
                setErrorOnColorNode(operation);
            }
        }
    }

    private void setErrorOnColorNode(Operation operation) {
        operation.setError("A color literal can not be used in an operation");
    }
}
