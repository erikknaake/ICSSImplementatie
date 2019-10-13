package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.Selector;

import java.util.ArrayList;

public class SelectorsFactory {
    public static ArrayList<Selector> make(ICSSParser.SelectorsContext selectorsContext) {
        ArrayList<Selector> selectors = new ArrayList<>();
        for (ICSSParser.Seperated_selectorContext separated_selectorContext : selectorsContext.seperated_selector()) {
            selectors.add(SelectorFactory.make(separated_selectorContext.selector()));
        }
        if (selectorsContext.selector() != null) {
            selectors.add(SelectorFactory.make(selectorsContext.selector()));
        }
        return selectors;
    }
}
