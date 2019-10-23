package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ASTWalker;
import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.selectors.CompositeSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;

public class OptimiseIdAsDirectChild implements Transform {
    private ASTWalker walker;

    public OptimiseIdAsDirectChild() {
        walker = new ASTWalker(this::optimiseIdAsDirectChild);
    }

    @Override
    public void apply(AST ast) {
        walker.walk(ast);
    }

    private void optimiseIdAsDirectChild(ASTNode node) {
        if (node instanceof CompositeSelector) {
            CompositeSelector compositeSelector = (CompositeSelector) node;
            if (compositeSelector.operator.operator.equals(">") && compositeSelector.rhs instanceof IdSelector) {
                optimiseId(walker.getParent(), compositeSelector, (IdSelector) compositeSelector.rhs);
            }
        }
    }

    private void optimiseId(ASTNode parent, CompositeSelector child, IdSelector replacement) {
        NodeTransformer.replaceChild(parent, child, replacement);
    }
}