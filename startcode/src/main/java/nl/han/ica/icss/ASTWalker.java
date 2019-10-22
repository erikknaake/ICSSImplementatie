package nl.han.ica.icss;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.typesystem.IScopeable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Walks an AST tree while calling methods on entering and exiting scopes and also calls methods when entering an ASTNode
 */
public class ASTWalker {

    private IWalkAction onEnterNode;
    private IScopeable scopeable;
    // Keeps track of parent nodes, so replacements can be made inside transformers
    private LinkedList<ASTNode> parents;
    // Keeps track of iterators, so child can be removed without causing concurrent modification exceptions
    private Stack<Iterator<ASTNode>> iterators;

    public ASTWalker(IWalkAction onEnterNode, IScopeable scopeable) {
        this.onEnterNode = onEnterNode;
        this.scopeable = scopeable;
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
            scopeable.pushScope();

        onEnterNode.step(node);

        walkChildren(node);

        if (isScope)
            scopeable.popScope();
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
