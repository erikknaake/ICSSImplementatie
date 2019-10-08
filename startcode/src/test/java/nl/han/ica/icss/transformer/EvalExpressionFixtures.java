package nl.han.ica.icss.transformer;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.selectors.TagSelector;

public class EvalExpressionFixtures {

    public static AST variableAssignment() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new VariableAssignment())
                .addChild(new VariableReference("LinkColor"))
                .addChild(new ColorLiteral("#ff0000"))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("a"))
                .addChild((new Declaration("color"))
                        .addChild(new VariableReference("LinkColor")))
        );

        return new AST(stylesheet);
    }

    public static AST variableAssignmentExpected() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("a"))
                .addChild((new Declaration("color"))
                        .addChild(new ColorLiteral("#ff0000")))
        );

        return new AST(stylesheet);
    }

    public static AST referringVariable() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new VariableAssignment())
                .addChild(new VariableReference("ParWidth"))
                .addChild(new PixelLiteral("200px"))
        );

        stylesheet.addChild((new VariableAssignment())
                .addChild(new VariableReference("FullWidth"))
                .addChild(new VariableReference("ParWidth"))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("a"))
                .addChild((new Declaration("width"))
                        .addChild(new VariableReference("FullWidth")))
        );

        return new AST(stylesheet);
    }

    public static AST referringVariableExpected() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("a"))
                .addChild((new Declaration("width"))
                        .addChild(new PixelLiteral("200px")))
        );

        return new AST(stylesheet);
    }

    public static AST calculatedProperty() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("a"))
                .addChild((new Declaration("width"))
                        .addChild((new MultiplyOperation())
                                .addChild(new ScalarLiteral("2") )
                                .addChild(new PercentageLiteral("10%"))
        )));

        return new AST(stylesheet);
    }

    public static AST calculatedPropertyExpected() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("a"))
                .addChild((new Declaration("width"))
                        .addChild(new PercentageLiteral("20%")))
        );

        return new AST(stylesheet);
    }

    public static AST calculatedVariable() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new VariableAssignment())
                .addChild(new VariableReference("FullWidth"))
                .addChild(new AddOperation()
                        .addChild((new SubtractOperation())
                            .addChild(new ScalarLiteral("3"))
                            .addChild(new ScalarLiteral("1"))
                        ).addChild(new PixelLiteral("3px"))
                ));

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("a"))
                .addChild((new Declaration("width"))
                        .addChild(new VariableReference("FullWidth"))
                )
        );

        return new AST(stylesheet);
    }

    public static AST calculatedVariableExpected() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("a"))
                .addChild((new Declaration("width"))
                        .addChild(new PixelLiteral("5px")))
        );

        return new AST(stylesheet);
    }

    public static AST multiStepCalculatedProperty() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new VariableAssignment())
                .addChild(new VariableReference("ParWidth"))
                .addChild(new PixelLiteral("200px"))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("a"))
                .addChild((new Declaration("width"))
                        .addChild((new AddOperation())
                                .addChild(new VariableReference("ParWidth"))
                                .addChild((new MultiplyOperation())
                                        .addChild(new ScalarLiteral("2") )
                                        .addChild(new PixelLiteral("10px"))))));

        return new AST(stylesheet);
    }

    public static AST multiStepCalculatedPropertyExpected() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new TagSelector("a"))
                .addChild((new Declaration("width"))
                        .addChild(new PixelLiteral("220px")))
        );

        return new AST(stylesheet);
    }
}
