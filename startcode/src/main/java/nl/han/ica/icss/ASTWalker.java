package nl.han.ica.icss;

import nl.han.ica.icss.ast.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Walks an AST tree while calling methods on entering and exiting scopes and also calls methods when entering an ASTNode
 */
public class ASTWalker {

    private IWalkAction onEnterScope, onEnterNode, onExitScope;
    // Keeps track of parent nodes, so replacements can be made inside transformers
    private LinkedList<ASTNode> parents;
    // Keeps track of iterators, so child can be removed without causing concurrent modification exceptions
    private Stack<Iterator<ASTNode>> iterators;

    public ASTWalker(IWalkAction onEnterScope, IWalkAction onEnterNode, IWalkAction onExitScope) {
        this.onEnterScope = onEnterScope;
        this.onEnterNode = onEnterNode;
        this.onExitScope = onExitScope;
        parents = new LinkedList<>();
        iterators = new Stack<>();
    }

    public void walk(AST ast) {
        walk(ast.root);
    }

    public void walk(ASTNode node) {
        parents.addLast(node);
        boolean isScope = node instanceof Stylerule || node instanceof IfClause || node instanceof ElseClause;
        if (isScope)
            onEnterScope.step(node);

        onEnterNode.step(node);

        walkChildren(node);

        if (isScope)
            onExitScope.step(node);
        parents.removeLast();
    }

    private void walkChildren(ASTNode node) {
        Iterator<ASTNode> currentIterator = node.getChildren().iterator();
        iterators.push(currentIterator);
        while (currentIterator.hasNext()) {
            walk(currentIterator.next());
        }
        iterators.pop();
    }

    public ASTNode getParent() {
        return parents.get(parents.size() - 2);
    }

    public void remove() {
        iterators.peek().remove();
    }
}
