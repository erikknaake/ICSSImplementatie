package nl.han.ica.icss.checker.typesystem;

import nl.han.ica.icss.ast.Expression;
import nl.han.ica.icss.ast.Operation;
import nl.han.ica.icss.ast.VariableReference;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.types.ExpressionType;

public class TypeResolver {
    public static ExpressionType resolve(Expression expression) {
        if (expression instanceof PixelLiteral)
            return ExpressionType.PIXEL;
        else if (expression instanceof PercentageLiteral)
            return ExpressionType.PERCENTAGE;
        else if (expression instanceof ColorLiteral)
            return ExpressionType.COLOR;
        else if (expression instanceof ScalarLiteral)
            return ExpressionType.SCALAR;
        else if (expression instanceof BoolLiteral)
            return ExpressionType.BOOL;
        else if(expression instanceof VariableReference)
            return DeclaredVariables.getInstance().getVariableType(((VariableReference) expression).name);
        else if(expression instanceof Operation) {
            // An operation is always the type of its operands, however variables may be multiplied by a scalar which doesnt tell the type,
            // so in the case the scalar is on the left hand side the right hand side must be checked for its type
            Operation operation = (Operation) expression;
            ExpressionType lhsType = resolve(operation.lhs);
            if(lhsType != ExpressionType.SCALAR)
                return lhsType;
            else
                return resolve(operation.rhs);
        }
        else
            return ExpressionType.UNDEFINED;
    }
}
