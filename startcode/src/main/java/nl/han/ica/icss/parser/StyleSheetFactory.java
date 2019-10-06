package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.Stylesheet;

public class StyleSheetFactory {
    public static Stylesheet make(ICSSParser.StylesheetContext ctx) {
        return new Stylesheet(BodyFactory.make(ctx.body()));
    }
}
