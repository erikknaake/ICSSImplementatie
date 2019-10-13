package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.selectors.CompositeSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;

public class OptimiseIdAsDirectChild implements Transform {
    @Override
    public void apply(AST ast) {
        optimiseIdAsDirectChild(ast.root);
    }

    private void optimiseIdAsDirectChild(ASTNode node) {
        for (ASTNode child : node.getChildren()) {
            if (child instanceof CompositeSelector) {
                CompositeSelector compositeSelector = (CompositeSelector) child;
                if (compositeSelector.operator.operator.equals(">") && compositeSelector.rhs instanceof IdSelector) {
                    optimiseId(node, compositeSelector, (IdSelector) compositeSelector.rhs);
                } else {
                    optimiseIdAsDirectChild(child);
                }
            } else {
                optimiseIdAsDirectChild(child);
            }
        }
    }

    private void optimiseId(ASTNode parent, CompositeSelector child, IdSelector replacement) {
        NodeTransformer.replaceChild(parent, child, replacement);
    }
}