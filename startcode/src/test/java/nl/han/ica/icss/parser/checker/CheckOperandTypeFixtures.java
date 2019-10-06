package nl.han.ica.icss.parser.checker;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.selectors.IdSelector;

public class CheckOperandTypeFixtures {
    public static AST multiplyWithOneScalar() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new VariableAssignment())
                .addChild(new VariableReference("ParWidth"))
                .addChild(new PixelLiteral("500px"))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new Declaration("width"))
                        .addChild((new AddOperation())
                                .addChild(new VariableReference("ParWidth"))
                                .addChild((new MultiplyOperation())
                                        .addChild(new ScalarLiteral("2") )
                                        .addChild(new PixelLiteral("10px"))

                                ))));

        return new AST(stylesheet);
    }

    public static AST multiplyWithoutScalar() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new VariableAssignment())
                .addChild(new VariableReference("ParWidth"))
                .addChild(new PixelLiteral("500px"))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new Declaration("width"))
                        .addChild((new AddOperation())
                                .addChild(new VariableReference("ParWidth"))
                                .addChild((new MultiplyOperation())
                                        .addChild(new PixelLiteral("2px") )
                                        .addChild(new PixelLiteral("10px"))

                                ))));

        return new AST(stylesheet);
    }

    public static AST addWithEqualTypes() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new VariableAssignment())
                .addChild(new VariableReference("ParWidth"))
                .addChild(new PixelLiteral("500px"))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new Declaration("width"))
                        .addChild((new AddOperation())
                                .addChild(new VariableReference("ParWidth"))
                                .addChild((new MultiplyOperation())
                                        .addChild(new ScalarLiteral("2") )
                                        .addChild(new PixelLiteral("10px"))

                                ))));

        return new AST(stylesheet);
    }

    public static AST addWithUnequalTypes() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new VariableAssignment())
                .addChild(new VariableReference("ParWidth"))
                .addChild(new PercentageLiteral("500%"))
        );

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new Declaration("width"))
                        .addChild((new AddOperation())
                                .addChild(new VariableReference("ParWidth"))
                                .addChild((new MultiplyOperation())
                                        .addChild(new ScalarLiteral("2") )
                                        .addChild(new PixelLiteral("10px"))

                                ))));

        return new AST(stylesheet);
    }
}
