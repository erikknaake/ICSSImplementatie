package nl.han.ica.icss.checker.typesystem;

import nl.han.ica.icss.ast.Expression;
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
        else
            return ExpressionType.UNDEFINED;
    }
}
