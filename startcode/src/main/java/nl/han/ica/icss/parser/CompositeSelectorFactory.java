package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.selectors.CompositeSelector;
import nl.han.ica.icss.ast.selectors.SelectorCompositionOperator;
import nl.han.ica.icss.ast.selectors.StateSelectorCompositionOperator;

public class CompositeSelectorFactory {
    public static CompositeSelector make(ICSSParser.SelectorContext selectorContext) {
        return new CompositeSelector(SelectorFactory.make(selectorContext.left),
                SelectorFactory.make(selectorContext.right),
                make(selectorContext.selector_composition_operator()));
    }

    private static SelectorCompositionOperator make(ICSSParser.Selector_composition_operatorContext selector_composition_operatorContext) {
        if (selector_composition_operatorContext.getText().startsWith(":")) {
            return new StateSelectorCompositionOperator(selector_composition_operatorContext.getText());
        } else {
            return new SelectorCompositionOperator(selector_composition_operatorContext.getText());
        }
    }
}
