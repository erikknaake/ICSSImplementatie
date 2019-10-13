package nl.han.ica.icss.ast.selectors;

import nl.han.ica.icss.ast.ASTNode;

public class SelectorCompositionOperator extends ASTNode {

    public String operator;

    public SelectorCompositionOperator(String operator) {
        this.operator = operator.trim();
    }

    public String getNodeLabel() {
        return "SelectorCompositionOperator(" + operator + ")";
    }

    public String toString() {
        return operator;
    }

    @Override
    public String getCSSString() {
        return " " + operator + " ";
    }
}
