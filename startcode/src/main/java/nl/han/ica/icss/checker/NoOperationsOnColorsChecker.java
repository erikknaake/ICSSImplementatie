package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Operation;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.types.ExpressionType;
import nl.han.ica.icss.checker.typesystem.TypeResolver;

public class NoOperationsOnColorsChecker implements IChecker {

    @Override
    public void check(ASTNode node) {
        if(node instanceof Operation) {
            Operation operation = (Operation) node;
            if(TypeResolver.resolve(operation.lhs) == ExpressionType.COLOR) {
                setErrorOnColorNode(operation);
            }
            if(TypeResolver.resolve(operation.rhs) == ExpressionType.COLOR) {
                setErrorOnColorNode(operation);
            }
        }
    }

    private void setErrorOnColorNode(Operation operation) {
        operation.setError("A color literal can not be used in an operation");
    }
}
