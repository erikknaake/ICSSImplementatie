package nl.han.ica.icss.ast.selectors;

/**
 * Represents : and :: selector compositions like a:hover
 */
public class StateSelectorCompositionOperator extends SelectorCompositionOperator {
    public StateSelectorCompositionOperator(String operator) {
        super(operator);
    }

    public String getNodeLabel() {
        return "StateSelectorCompositionOperator(" + operator + ")";
    }

    @Override
    public String getCSSString() {
        return operator;
    }
}
