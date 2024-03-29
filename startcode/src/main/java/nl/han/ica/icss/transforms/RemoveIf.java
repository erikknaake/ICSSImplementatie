package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ASTWalker;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;

public class RemoveIf implements Transform {

    private ASTWalker walker;

    public RemoveIf() {
        walker = new ASTWalker(this::removeIfs);
    }

    @Override
    public void apply(AST ast) {
        walker.walk(ast);
    }

    private void removeIfs(ASTNode node) {
        for (ASTNode child : node.getChildren()) {
            if (child instanceof IfStatement) {
                transformIf(node, (IfStatement) child);
            } else {
                removeIfs(child);
            }
        }
    }

    private void transformIf(ASTNode parent, IfStatement child) {
        if (shouldRemove(child.ifClause)) {
            if (child.elseClause != null)
                NodeTransformer.replaceIfWithBody((Stylerule) parent, child, child.elseClause.body);
            NodeTransformer.removeChildFromParent(parent, child);
        } else {
            NodeTransformer.replaceIfWithBody((Stylerule) parent, child, child.ifClause.body);
            removeIfs(parent);
        }
    }

    private boolean shouldRemove(IfClause ifClause) {
        return !((BoolLiteral) ifClause.conditionalExpression).value;
    }
}
