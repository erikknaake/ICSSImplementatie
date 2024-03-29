package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Operation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.types.ExpressionType;

public class OperandTypeChecker implements IChecker {
    @Override
    public void check(ASTNode node) {
        if (node instanceof Operation) {
            Operation operation = (Operation) node;
            ExpressionType lhsType = operation.lhs.getType();
            ExpressionType rhsType = operation.rhs.getType();
            if (operation instanceof MultiplyOperation) {
                if (lhsType != ExpressionType.SCALAR && rhsType != ExpressionType.SCALAR) {
                    operation.setError("At least one operand of an multiplication must be a scalar");
                }
            } else {
                if (lhsType != rhsType) {
                    operation.setError("Operands must be of equal type (got " + lhsType + " and " + rhsType + ")");
                }
            }
        }
    }
}
