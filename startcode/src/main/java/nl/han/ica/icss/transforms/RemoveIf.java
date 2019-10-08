package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.IfClause;
import nl.han.ica.icss.ast.Stylerule;
import nl.han.ica.icss.ast.literals.BoolLiteral;

public class RemoveIf implements Transform {

    @Override
    public void apply(AST ast) {
        removeIfs(ast.root);
    }

    private void removeIfs(ASTNode node) {
        for(ASTNode child : node.getChildren()) {
            if(child instanceof IfClause) {
                transformIf(node, (IfClause) child);
            } else {
                removeIfs(child);
            }
        }
    }

    private void transformIf(ASTNode node, IfClause child) {
        if(shouldRemove(child)) {
            NodeTransformer.removeChildFromParent(node, child);
        } else {
            NodeTransformer.replaceIfWithBody((Stylerule) node, child, child.body);
            removeIfs(node);
        }
    }

    private boolean shouldRemove(IfClause ifClause) {
        return !((BoolLiteral) ifClause.conditionalExpression).value;
    }
}
