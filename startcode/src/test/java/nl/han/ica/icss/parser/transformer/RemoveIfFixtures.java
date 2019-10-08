package nl.han.ica.icss.parser.transformer;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;

public class RemoveIfFixtures {
    public static AST noIfToRemove() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new Declaration("width"))
                        .addChild(new PixelLiteral("520px")))
        );

        return new AST(stylesheet);
    }

    public static AST noIfToRemoveExpected() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new Declaration("width"))
                        .addChild(new PixelLiteral("520px")))
        );

        return new AST(stylesheet);
    }

    public static AST simpleIfToRemove() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new Declaration("width"))
                        .addChild(new PixelLiteral("520px")))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("p"))
                .addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild((new IfClause())
                        .addChild(new BoolLiteral("FALSE"))
                        .addChild((new Declaration("color")
                                .addChild(new ColorLiteral("#124532"))))
                ));

        return new AST(stylesheet);
    }

    public static AST simpleIfToRemoveExpected() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new Declaration("width"))
                        .addChild(new PixelLiteral("520px")))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("p"))
                .addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ffffff")))
        );

        return new AST(stylesheet);
    }

    public static AST simpleIfToKeep() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new Declaration("width"))
                        .addChild(new PixelLiteral("520px")))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("p"))
                .addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild((new IfClause())
                        .addChild(new BoolLiteral("TRUE"))
                        .addChild((new Declaration("color")
                                .addChild(new ColorLiteral("#124532"))))
                ));

        return new AST(stylesheet);
    }

    public static AST simpleIfToKeepExpected() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new Declaration("width"))
                        .addChild(new PixelLiteral("520px")))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("p"))
                .addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild((new Declaration("color")
                        .addChild(new ColorLiteral("#124532"))))
        );

        return new AST(stylesheet);
    }

    public static AST nestedIfKeepBoth() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new Declaration("width"))
                        .addChild(new PixelLiteral("520px")))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("p"))
                .addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild((new IfClause())
                        .addChild(new BoolLiteral("TRUE"))
                        .addChild((new Declaration("color")
                                .addChild(new ColorLiteral("#124532"))))
                .addChild((new IfClause())
                        .addChild(new BoolLiteral("TRUE"))
                        .addChild(new Declaration("bg-color").addChild(new ColorLiteral("#111111")))))
        );

        return new AST(stylesheet);
    }

    public static AST nestedIfKeepBothExpected() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new Declaration("width"))
                        .addChild(new PixelLiteral("520px")))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("p"))
                .addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild((new Declaration("color")
                        .addChild(new ColorLiteral("#124532"))))
                .addChild(new Declaration("bg-color")
                        .addChild(new ColorLiteral("#111111")))
        );

        return new AST(stylesheet);
    }

    public static AST nestedIfKeepOuter() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new Declaration("width"))
                        .addChild(new PixelLiteral("520px")))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("p"))
                .addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild((new IfClause())
                        .addChild(new BoolLiteral("TRUE"))
                        .addChild((new Declaration("color")
                                .addChild(new ColorLiteral("#124532"))))
                        .addChild((new IfClause())
                                .addChild(new BoolLiteral("FALSE"))
                                .addChild(new Declaration("bg-color").addChild(new ColorLiteral("#111111")))))
        );

        return new AST(stylesheet);
    }

    public static AST nestedIfKeepOuterExpected() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new Declaration("width"))
                        .addChild(new PixelLiteral("520px")))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("p"))
                .addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild((new Declaration("color")
                        .addChild(new ColorLiteral("#124532"))))
        );

        return new AST(stylesheet);
    }

    public static AST nestedIfDontKeepInnerWhenOuterGetsRemoved() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new Declaration("width"))
                        .addChild(new PixelLiteral("520px")))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("p"))
                .addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild((new IfClause())
                        .addChild(new BoolLiteral("FALSE"))
                        .addChild((new Declaration("color")
                                .addChild(new ColorLiteral("#124532"))))
                        .addChild((new IfClause())
                                .addChild(new BoolLiteral("TRUE"))
                                .addChild(new Declaration("bg-color").addChild(new ColorLiteral("#111111")))))
        );

        return new AST(stylesheet);
    }

    public static AST nestedIfDontKeepInnerWhenOuterGetsRemovedExpected() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new Declaration("width"))
                        .addChild(new PixelLiteral("520px")))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("p"))
                .addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ffffff")))
        );

        return new AST(stylesheet);
    }
}
