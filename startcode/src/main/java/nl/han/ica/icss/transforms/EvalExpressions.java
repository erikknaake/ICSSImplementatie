package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.typesystem.VariableValues;

import java.util.Iterator;


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
        // Use of iterator to prevent ConcurrentModification when removing a variable assignment
        Iterator<ASTNode> iterator = node.getChildren().iterator();
        while (iterator.hasNext()) {
            ASTNode child = iterator.next();
            replaceExpressions(child);
            if (child instanceof VariableAssignment) {
                iterator.remove();
                AssignVariableAndRemoveVariableDeclaration(node, (VariableAssignment) child);
            }
            if (child instanceof Expression) {
                replaceExpression(node, child);
            }
        }
    }

    private void AssignVariableAndRemoveVariableDeclaration(ASTNode node, VariableAssignment variableAssignment) {
        assignVariable(variableAssignment);
        removeVariableAssignment(node, variableAssignment);
    }

    private void replaceExpression(ASTNode node, ASTNode child) {
        if (child instanceof Operation) {
            replaceOperation((Operation) child);
        }
        replaceExpressionWithLiteral((Expression) child, node);
    }

    private void replaceOperation(Operation operation) {
        replaceExpressions(operation.lhs);
        replaceExpressions(operation.rhs);
    }

    private void removeVariableAssignment(ASTNode parent, VariableAssignment child) {
        parent.removeChild(child);
    }

    private void assignVariable(VariableAssignment variableAssignment) {
        variableValues.put(variableAssignment.name.name,
                variableAssignment.expression.eval());
    }

    private void replaceExpressionWithLiteral(Expression astNode, ASTNode parent) {
        if (astNode instanceof Operation) {
            Literal value = astNode.eval();
            NodeTransformer.replaceChild(parent, astNode, value);
        } else if (astNode instanceof VariableReference && !(parent instanceof VariableAssignment)) {
            Literal value = astNode.eval();
            NodeTransformer.replaceChild(parent, astNode, value);
        }
    }

}
