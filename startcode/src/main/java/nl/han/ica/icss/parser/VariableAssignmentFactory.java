package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.VariableAssignment;

public class VariableAssignmentFactory {
    public static VariableAssignment make(ICSSParser.Variable_assignmentContext variable_assignmentContext) {
        return makeVariableAssignment(variable_assignmentContext.expression(), variable_assignmentContext.variable_reference());
    }


    private static VariableAssignment makeVariableAssignment(ICSSParser.ExpressionContext expressionContext, ICSSParser.Variable_referenceContext variable_referenceContext) {
        return new VariableAssignment(ExpressionFactory.make(expressionContext), VariableReferenceFactory.make(variable_referenceContext));
    }
}
