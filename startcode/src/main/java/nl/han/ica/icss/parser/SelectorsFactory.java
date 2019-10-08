package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.Selector;

import java.util.ArrayList;

public class SelectorsFactory {
    public static ArrayList<Selector> make(ICSSParser.SelectorsContext selectorsContext) {
        ArrayList<Selector> selectors = new ArrayList<>();
        if(selectorsContext.selectors() != null) {
            selectors.addAll(make(selectorsContext.selectors()));
        }
        if(selectorsContext.selector() != null) {
            selectors.add(SelectorFactory.make(selectorsContext.selector()));
        }
        else {
            throw new IllegalStateException("No selectors found in SelectorsContext");
        }
        return selectors;
    }
}
