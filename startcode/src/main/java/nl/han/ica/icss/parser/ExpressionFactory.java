package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.Expression;

public class ExpressionFactory {

    public static Expression make(ICSSParser.ExpressionContext expressionContext) {
        if (expressionContext.getChildCount() == 1) {
            if (expressionContext.literal() != null) {
                return LiteralFactory.make(expressionContext.literal());
            } else if (expressionContext.variable_reference() != null) {
                return VariableReferenceFactory.make(expressionContext.variable_reference());
            }
        } else if (expressionContext.getChildCount() == 3)
            return OperationFactory.make(
                    expressionContext,
                    make(expressionContext.left),
                    make(expressionContext.right));
        else
            throw new IllegalStateException("No valid expression found in ExpressionContext");
        return null;
    }
}
