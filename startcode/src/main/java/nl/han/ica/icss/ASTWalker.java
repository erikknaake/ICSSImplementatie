package nl.han.ica.icss;

import nl.han.ica.icss.ast.*;

import java.util.Iterator;
import java.util.LinkedList;

public class ASTWalker {

    private IWalkAction onEnterScope, onExitNode, onExitScope;
    private LinkedList<ASTNode> parents;
    private LinkedList<Iterator<ASTNode>> iterators;

    public ASTWalker(IWalkAction onEnterScope, IWalkAction onExitNode, IWalkAction onExitScope) {
        this.onEnterScope = onEnterScope;
        this.onExitNode = onExitNode;
        this.onExitScope = onExitScope;
        parents = new LinkedList<>();
        iterators = new LinkedList<>();
    }

    public void walk(AST ast) {
        walk(ast.root);
    }

    public void walk(ASTNode node) {
        parents.addLast(node);
        boolean isScope = node instanceof Stylerule || node instanceof IfClause || node instanceof ElseClause;
        if(isScope)
            onEnterScope.step(node);

        onExitNode.step(node);
        Iterator<ASTNode> currentIterator = node.getChildren().iterator();
        iterators.addLast(currentIterator);
        while(currentIterator.hasNext()) {
            walk(currentIterator.next());
        }
        iterators.removeLast();

        if(isScope)
            onExitScope.step(node);
        parents.removeLast();
    }

    public ASTNode getParent() {
        return parents.get(parents.size() - 2);
    }

    public void remove() {
        iterators.getLast().remove();
    }
}
