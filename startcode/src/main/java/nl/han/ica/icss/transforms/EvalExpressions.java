package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.ExpressionType;
import nl.han.ica.icss.typesystem.TypeResolver;
import nl.han.ica.icss.typesystem.VariableValues;


public class EvalExpressions implements Transform {

    private VariableValues variableValues;

    public EvalExpressions() {
        variableValues = VariableValues.getInstance();
        variableValues.clear();
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
        if(!(variableAssignment.expression instanceof ColorLiteral)) {
            variableValues.put(variableAssignment.name.name,
                    ValueFactory.make(TypeResolver.resolve(variableAssignment.expression),
                            getValue(variableAssignment.expression))
            );
        }
    }

    private void replaceExpression(Expression astNode, ASTNode parent) {
        if (astNode instanceof Operation) {
            Literal value = calculateOperation((Operation) astNode);
            NodeTransformer.replaceChild(parent, astNode, value);
        }
        else if(astNode instanceof VariableReference &&
                !(parent instanceof VariableAssignment)) {
            Literal value = getVariableValue((VariableReference) astNode);
            NodeTransformer.replaceChild(parent, astNode, value);
        }
    }

    private Literal calculateOperation(Operation operation) {
        ExpressionType type = TypeResolver.resolve(operation);

        int leftHandSideValue = Integer.parseInt(getValue(operation.lhs));
        int rightHandSideValue = Integer.parseInt(getValue(operation.rhs));
        int calculatedValue = Integer.MIN_VALUE;
        if(operation instanceof MultiplyOperation) {
            calculatedValue = leftHandSideValue * rightHandSideValue;
        } else if(operation instanceof AddOperation) {
            calculatedValue = leftHandSideValue + rightHandSideValue;
        } else if(operation instanceof SubtractOperation) {
            calculatedValue = leftHandSideValue - rightHandSideValue;
        }
        if(calculatedValue != Integer.MIN_VALUE) {
            return ValueFactory.make(type, String.valueOf(calculatedValue));
        }
        else
            throw new IllegalArgumentException("Cannot calculate operation: " + operation);
    }

    // Returns string so it also can save the boolean variables
    private String getValue(ASTNode node) {
        if (node instanceof VariableReference) {
            return getValue(getVariableValue((VariableReference) node));
        } else if (node instanceof PercentageLiteral)
            return String.valueOf(((PercentageLiteral) node).value);
        else if (node instanceof PixelLiteral)
            return String.valueOf(((PixelLiteral) node).value);
        else if (node instanceof ScalarLiteral)
            return String.valueOf(((ScalarLiteral) node).value);
        else if (node instanceof BoolLiteral)
            return ((BoolLiteral) node).value ? "TRUE" : "FALSE";
        else if(node instanceof Operation)
            return getValue(calculateOperation((Operation) node));
        else
            throw new IllegalArgumentException("Cannot find a valid node for: " + node);
}

    private Literal getVariableValue(VariableReference node) {
        return variableValues.get(node.name);
    }
}
