package nl.han.ica.icss;

import nl.han.ica.icss.ast.*;

import java.util.LinkedList;

public class ASTWalker {

    private IWalkAction onEnterScope, onExitNode, onExitScope;
    private LinkedList<ASTNode> parents;


    public ASTWalker(IWalkAction onEnterScope, IWalkAction onExitNode, IWalkAction onExitScope) {
        this.onEnterScope = onEnterScope;
        this.onExitNode = onExitNode;
        this.onExitScope = onExitScope;
        parents = new LinkedList<>();
    }

    public void walk(AST ast) {
        walk(ast.root);
    }

    public void walk(ASTNode node) {
        parents.addLast(node);
        boolean isScope = node instanceof Stylerule || node instanceof IfClause || node instanceof ElseClause;
        if(isScope)
            onEnterScope.step(node);
        for(ASTNode child : node.getChildren()) {
            walk(child);
        }
        onExitNode.step(node);

        if(isScope)
            onExitScope.step(node);
        parents.removeLast();
    }

    public ASTNode getParent() {
        return parents.get(parents.size() - 2);
    }
}
