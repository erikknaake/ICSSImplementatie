package nl.han.ica.icss.parser.checker;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.selectors.TagSelector;

public class CheckConditionalIfFixtures {

    public static AST conditionalIf() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new VariableAssignment())
                .addChild(new VariableReference("UseLinkColor"))
                .addChild(new BoolLiteral(false))
        );


        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("p"))
                .addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild((new Declaration("width"))
                        .addChild(new VariableReference("ParWidth")))
                .addChild((new IfClause())
                        .addChild(new BoolLiteral("TRUE"))
                        .addChild((new Declaration("color")
                                .addChild(new ColorLiteral("#124532"))))
                        .addChild((new IfClause())
                                .addChild(new VariableReference("UseLinkColor"))
                                .addChild(new Declaration("bg-color").addChild(new VariableReference("LinkColor")))
                        )));
        return new AST(stylesheet);
    }

    public static AST conditionalIfViaVariableReference() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new VariableAssignment())
                .addChild(new VariableReference("UseLinkColor"))
                .addChild(new BoolLiteral(false))
        );

        stylesheet.addChild((new VariableAssignment())
                .addChild(new VariableReference("AdjustColor"))
                .addChild(new BoolLiteral(true))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("p"))
                .addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild((new Declaration("width"))
                        .addChild(new VariableReference("ParWidth")))
                .addChild((new IfClause())
                        .addChild(new VariableReference("AdjustColor"))
                        .addChild((new Declaration("color")
                                .addChild(new ColorLiteral("#124532"))))
                        .addChild((new IfClause())
                                .addChild(new VariableReference("UseLinkColor"))
                                .addChild(new Declaration("bg-color").addChild(new VariableReference("LinkColor")))
                        )));
        return new AST(stylesheet);
    }

    public static AST unconditionalIfViaVariableReference() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new VariableAssignment())
                .addChild(new VariableReference("AdjustColor"))
                .addChild(new ScalarLiteral("2"))
        );

        stylesheet.addChild((new VariableAssignment())
                .addChild(new VariableReference("UseLinkColor"))
                .addChild(new BoolLiteral(false))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("p"))
                .addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild((new Declaration("width"))
                        .addChild(new VariableReference("ParWidth")))
                .addChild((new IfClause())
                        .addChild(new VariableReference("AdjustColor"))
                        .addChild((new Declaration("color")
                                .addChild(new ColorLiteral("#124532"))))
                        .addChild((new IfClause())
                                .addChild(new VariableReference("UseLinkColor"))
                                .addChild(new Declaration("bg-color").addChild(new VariableReference("LinkColor")))
                        )));
        return new AST(stylesheet);
    }

    public static AST unconditionalIf() {
        Stylesheet stylesheet = new Stylesheet();
        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("p"))
                .addChild((new Declaration("background-color"))
                        .addChild(new ColorLiteral("#ffffff")))
                .addChild((new Declaration("width"))
                        .addChild(new VariableReference("ParWidth")))
                .addChild((new IfClause())
                        .addChild(new ScalarLiteral("2"))
                        .addChild((new Declaration("color")
                                .addChild(new ColorLiteral("#124532"))))
                        .addChild((new IfClause())
                                .addChild(new VariableReference("UseLinkColor"))
                                .addChild(new Declaration("bg-color").addChild(new VariableReference("LinkColor")))
                        )));
        return new AST(stylesheet);
    }
}
