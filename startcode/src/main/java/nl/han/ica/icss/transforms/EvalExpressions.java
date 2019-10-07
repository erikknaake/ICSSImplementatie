package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.typesystem.TypeResolver;

import java.util.HashMap;
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
        for(int i = 0; i < node.getChildren().size(); i++) {
            ASTNode child = node.getChildren().get(i);
            if(child instanceof Expression) {
                replaceExpression((Expression) child, node);
            }
            replaceExpressions(child);
        }
    }

    public void replaceExpression(Expression astNode, ASTNode parent) {
        if (astNode instanceof Operation) {
            Literal value = calculateOperation((Operation) astNode);
            parent.getChildren().set(parent.getChildren().indexOf(astNode), value);
        }
    }

    private Literal calculateOperation(Operation operation) {
        Expression leftHandSide = operation.lhs;
        Expression rightHandSide = operation.rhs;
        if(operation.lhs instanceof Operation)
            leftHandSide = calculateOperation((Operation) leftHandSide);
        if(operation.rhs instanceof Operation)
            rightHandSide = calculateOperation((Operation) rightHandSide);

        Integer lhsValue = getValue(leftHandSide);
        Integer rhsValue = getValue(rightHandSide);
        int resultValue = Integer.MIN_VALUE;
        if (operation instanceof MultiplyOperation) {
            resultValue = lhsValue * rhsValue;
        } else if (operation instanceof AddOperation) {
            resultValue = lhsValue + rhsValue;
        } else if(operation instanceof SubtractOperation) {
            resultValue = lhsValue - rhsValue;
        }
        if(resultValue == Integer.MIN_VALUE)
            throw new IllegalStateException("Could not find a valid operation for: " + operation);
        Literal replaceLiteral = ValueFactory.make(TypeResolver.resolve(operation), resultValue);
        variableValues.put(newName(), replaceLiteral); // Register the new variable so it can be used by other expressions
        return replaceLiteral;
    }

    private String newName() {
        // Intentionally used lowercase, since this cannot be parsed as a variable
        // it's safe to assume it is not used
        return "internal_literal:" + lastVariableName++;
    }

    private Integer getValue(ASTNode node) {
        if (node instanceof PixelLiteral)
            return ((PixelLiteral) node).value;
        else if (node instanceof PercentageLiteral)
            return ((PercentageLiteral) node).value;
        else if (node instanceof ScalarLiteral)
            return ((ScalarLiteral) node).value;
        else if(node instanceof VariableAssignment) {
            System.out.println("Var assign: " + node);
            Expression expression = ((VariableAssignment) node).expression;
            int value = getValue(expression);
            String variableName = ((VariableAssignment) node).name.name;
            variableValues.put(variableName, (Literal) expression);
            return value;
        } else if (node instanceof VariableReference) {
            System.out.println("Var ref: " + node);
            String variableName = ((VariableReference) node).name;
            Literal result = variableValues.get(variableName);
            return getValue(result);
        } else
            throw new IllegalArgumentException("Could not find value for: " + node);
    }
}
