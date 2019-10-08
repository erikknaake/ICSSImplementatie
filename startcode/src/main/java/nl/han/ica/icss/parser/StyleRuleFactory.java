package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.Stylerule;

public class StyleRuleFactory {
    public static Stylerule make(ICSSParser.StyleruleContext stylerule) {
        return new Stylerule(SelectorsFactory.make(stylerule.selectors()), BodyFactory.make(stylerule.scope()));
    }
}
