package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.Selector;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;

public class SelectorFactory {
    public static Selector make(ICSSParser.SelectorContext selectorContext) {
        if (selectorContext.class_selector() != null)
            return makeClassSelector(selectorContext.class_selector());
        else if (selectorContext.id_selector() != null)
            return makeIdSelector(selectorContext.id_selector());
        else if (selectorContext.tag_selector() != null)
            return makeTagSelector(selectorContext.tag_selector());
        else if (selectorContext.selector_composition_operator() != null)
            return CompositeSelectorFactory.make(selectorContext);
        else
            throw new IllegalStateException("No selector found in SelectorContext");
    }

    private static ClassSelector makeClassSelector(ICSSParser.Class_selectorContext class_selectorContext) {
        return new ClassSelector(class_selectorContext.getText());
    }

    private static IdSelector makeIdSelector(ICSSParser.Id_selectorContext id_selectorContext) {
        return new IdSelector(id_selectorContext.getText());
    }

    private static TagSelector makeTagSelector(ICSSParser.Tag_selectorContext tag_selectorContext) {
        return new TagSelector(tag_selectorContext.getText());
    }
}
