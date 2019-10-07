package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.ExpressionType;
import nl.han.ica.icss.typesystem.TypeResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EvalExpressions implements Transform {

    private Map<String, Literal> variableValues;
    private int lastVariableName = 0;

    public EvalExpressions() {
        variableValues = new HashMap<>();
    }

    @Override
    public void apply(AST ast) {
        replaceExpressions(ast.root);
    }

    private void replaceExpressions(ASTNode node) {
        for (ASTNode child : node.getChildren()) {
            replaceExpressions(child);
            if (node instanceof VariableAssignment) {
                assignVariable((VariableAssignment) node);
            }
            if (child instanceof Expression) {
                if (child instanceof Operation) {
                    replaceExpressions(((Operation) child).lhs);
                    replaceExpressions(((Operation) child).rhs);
                }
                replaceExpression((Expression) child, node);
            }
        }
    }

    private void assignVariable(VariableAssignment variableAssignment) {
        if(!(variableAssignment.expression instanceof ColorLiteral) &&
            !(variableAssignment.expression instanceof BoolLiteral)) {
            variableValues.put(variableAssignment.name.name,
                    ValueFactory.make(TypeResolver.resolve(variableAssignment.expression),
                            getValue(variableAssignment.expression))
            );
        }
    }

    private void replaceExpression(Expression astNode, ASTNode parent) {
        if (astNode instanceof Operation) {
            Literal value = calculateOperation((Operation) astNode);
            ChildReplacer.replaceChild(parent, astNode, value);
        }
    }

    private Literal calculateOperation(Operation operation) {
        ExpressionType type = TypeResolver.resolve(operation);
        int leftHandSideValue = getValue(operation.lhs);
        int rightHandSideValue = getValue(operation.rhs);
        int calculatedValue = Integer.MIN_VALUE;
        if(operation instanceof MultiplyOperation) {
            calculatedValue = leftHandSideValue * rightHandSideValue;
        } else if(operation instanceof AddOperation) {
            calculatedValue = leftHandSideValue + rightHandSideValue;
        } else if(operation instanceof SubtractOperation) {
            calculatedValue = leftHandSideValue - rightHandSideValue;
        }
        if(calculatedValue != Integer.MIN_VALUE)
            return ValueFactory.make(type, calculatedValue);
        else
            throw new IllegalArgumentException("Cannot calculate operation: " + operation);
    }

    private int getValue(ASTNode node) {
        if (node instanceof VariableReference) {
            return getValue(variableValues.get(((VariableReference) node).name));
        } else if (node instanceof PercentageLiteral)
            return ((PercentageLiteral) node).value;
        else if (node instanceof PixelLiteral)
            return ((PixelLiteral) node).value;
        else if (node instanceof ScalarLiteral)
            return ((ScalarLiteral) node).value;
        else if(node instanceof Operation)
            return getValue(calculateOperation((Operation) node));
        else
            throw new IllegalArgumentException("Cannot find a valid node for: " + node);
}
}
