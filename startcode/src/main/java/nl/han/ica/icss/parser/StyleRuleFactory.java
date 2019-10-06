package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.Stylerule;

public class StyleRuleFactory {
    public static Stylerule make(ICSSParser.StyleruleContext stylerule) {
        return new Stylerule(SelectorFactory.make(stylerule.selector()), BodyFactory.make(stylerule.scope()));
    }
}
