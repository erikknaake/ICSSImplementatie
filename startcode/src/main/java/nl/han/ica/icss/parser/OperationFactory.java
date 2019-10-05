package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.Expression;
import nl.han.ica.icss.ast.Operation;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;

public class OperationFactory {

    public static Operation make(ICSSParser.ExpressionContext expressionContext, Expression lhs, Expression rhs) {
        Operation result;
        if(expressionContext.add_operation() != null)
            result =  makeAddOperation();
        else if(expressionContext.subtract_operation() != null)
            result =  makeSubtractOperation();
        else if(expressionContext.multiply_operation() != null)
            result = makeMultiplyOperation();
        else
            throw new IllegalStateException("No valid operation found in OperationContext");
        result.lhs = lhs;
        result.rhs = rhs;
        return result;
    }

    private static AddOperation makeAddOperation() {
        return new AddOperation();
    }

    private static SubtractOperation makeSubtractOperation() {
        return new SubtractOperation();
    }

    private static MultiplyOperation makeMultiplyOperation() {
        return new MultiplyOperation();
    }
}
