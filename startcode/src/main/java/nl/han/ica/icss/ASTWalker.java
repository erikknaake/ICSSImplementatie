package nl.han.ica.icss;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.typesystem.IScopeable;

import java.util.Iterator;
import java.util.Stack;

/**
 * Walks an AST tree while calling methods on entering and exiting scopes and also calls methods when entering an ASTNode
 */
public class ASTWalker {

    private IWalkAction onEnterNode;
    private IScopeable scopeable;
    // Keeps track of parent nodes, so replacements can be made inside transformers
    private Stack<ASTNode> parents;
    // Keeps track of iterators, so child can be removed without causing concurrent modification exceptions
    private Stack<Iterator<ASTNode>> iterators;

    public ASTWalker(IWalkAction onEnterNode) {
        this.onEnterNode = onEnterNode;
        initializeContainers();
    }

    public ASTWalker(IWalkAction onEnterNode, IScopeable scopeable) {
        this.onEnterNode = onEnterNode;
        this.scopeable = scopeable;
        initializeContainers();
    }

    private void initializeContainers() {
        parents = new Stack<>();
        iterators = new Stack<>();
    }

    public void walk(AST ast) {
        walk(ast.root);
    }

    public void walk(ASTNode node) {
        boolean keepTrackOfScope = (node instanceof Stylerule || node instanceof IfClause || node instanceof ElseClause) && scopeable != null;
        if (keepTrackOfScope)
            scopeable.pushScope();

        onEnterNode.step(node);

        walkChildren(node);

        if (keepTrackOfScope)
            scopeable.popScope();
    }

    private void walkChildren(ASTNode node) {
        parents.push(node);
        Iterator<ASTNode> currentIterator = node.getChildren().iterator();
        iterators.push(currentIterator);
        while (currentIterator.hasNext()) {
            walk(currentIterator.next());
        }
        iterators.pop();
        parents.pop();
    }

    public ASTNode getParent() {
        return parents.peek();
    }

    public void remove() {
        iterators.peek().remove();
    }
}
