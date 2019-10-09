package nl.han.ica.icss.ast.selectors;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Selector;

import java.util.ArrayList;

public class CompositeSelector extends Selector {
    private SelectorCompositionOperator operator;
    private Selector lhs, rhs;

    public CompositeSelector() {}

    public CompositeSelector(Selector lhs, Selector rhs, SelectorCompositionOperator operator) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.operator = operator;
    }

    public String getNodeLabel() {
        return "CompositeSelector";
    }
    public String toString() {
        return lhs + " " + operator + " " + rhs;
    }

    @Override
    public String getCSSString() {
        return lhs.getCSSString() + operator.getCSSString() + rhs.getCSSString();
    }

    @Override
    public ArrayList<ASTNode> getChildren() {
        ArrayList<ASTNode> children = new ArrayList<>();
        if(lhs != null)
            children.add(lhs);
        if(rhs != null)
            children.add(rhs);
        return children;
    }

    @Override
    public ASTNode addChild(ASTNode child) {
        if(child instanceof SelectorCompositionOperator)
            operator = (SelectorCompositionOperator) child;
        else if(lhs == null) {
            lhs = (Selector) child;
        } else if(rhs == null) {
            rhs = (Selector) child;
        }
        return this;
    }
}
