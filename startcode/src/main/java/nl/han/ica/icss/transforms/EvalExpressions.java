package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ASTWalker;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.typesystem.VariableValues;


public class EvalExpressions implements Transform {

    private VariableValues variableValues;
    private ASTWalker walker;

    public EvalExpressions() {
        variableValues = VariableValues.getInstance();
        variableValues.clear();

        walker = new ASTWalker(
                this::replaceExpressions,
                variableValues);
    }

    @Override
    public void apply(AST ast) {
        walker.walk(ast);
    }

    private void replaceExpressions(ASTNode node) {
        if (node instanceof VariableAssignment) {
            // Prevent concurrent modification exception
            walker.remove();
            AssignVariableAndRemoveVariableDeclaration(walker.getParent(), (VariableAssignment) node);
        }
        if (node instanceof Expression) {
            replaceExpressionWithLiteral((Expression) node, walker.getParent());
        }
    }

    private void AssignVariableAndRemoveVariableDeclaration(ASTNode node, VariableAssignment variableAssignment) {
        assignVariable(variableAssignment);
        removeVariableAssignment(node, variableAssignment);
    }

    private void removeVariableAssignment(ASTNode parent, VariableAssignment child) {
        parent.removeChild(child);
    }

    private void assignVariable(VariableAssignment variableAssignment) {
        variableValues.put(variableAssignment.name.name,
                variableAssignment.expression.eval());
    }

    private void replaceExpressionWithLiteral(Expression child, ASTNode parent) {
        Literal value = child.eval();
        if (child instanceof Operation || (child instanceof VariableReference && !(parent instanceof VariableAssignment))) {
            NodeTransformer.replaceChild(parent, child, value);
        }
    }

}
