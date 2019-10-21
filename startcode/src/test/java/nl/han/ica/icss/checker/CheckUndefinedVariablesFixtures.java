package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.selectors.IdSelector;

public class CheckUndefinedVariablesFixtures {
    public static AST undefinedVariable() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new Declaration("width"))
                        .addChild((new AddOperation())
                                .addChild(new VariableReference("ParWidth"))
                                .addChild((new MultiplyOperation())
                                        .addChild(new ColorLiteral("#222222") )
                                        .addChild(new PixelLiteral("10px"))

                                ))));
        return new AST(stylesheet);
    }

    public static AST definedVariable() {
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
                                        .addChild(new ColorLiteral("#222222") )
                                        .addChild(new PixelLiteral("10px"))

                                ))));
        return new AST(stylesheet);
    }

    public static AST variableInsideScope() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new VariableAssignment())
                        .addChild(new VariableReference("ParWidth"))
                        .addChild(new PixelLiteral("500px"))
                )
                .addChild((new Declaration("width"))
                        .addChild((new AddOperation())
                                .addChild(new VariableReference("ParWidth"))
                                .addChild((new MultiplyOperation())
                                        .addChild(new ColorLiteral("#222222") )
                                        .addChild(new PixelLiteral("10px"))

                                ))));
        return new AST(stylesheet);
    }


    public static AST variableInsideWrongScope() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                        .addChild(new IdSelector("#id"))
                        .addChild((new VariableAssignment())
                                .addChild(new VariableReference("ParWidth"))
                                .addChild(new PixelLiteral("500px"))
                        ));


        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new Declaration("width"))
                        .addChild((new AddOperation())
                                .addChild(new VariableReference("ParWidth"))
                                .addChild((new MultiplyOperation())
                                        .addChild(new ColorLiteral("#222222"))
                                        .addChild(new PixelLiteral("10px"))

                                ))));
        return new AST(stylesheet);
    }

    public static AST variableInsideIfScope() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new IfStatement())
                        .addChild((new IfClause())
                                .addChild(new BoolLiteral("TRUE"))
                                .addChild((new VariableAssignment())
                                        .addChild(new VariableReference("ParWidth"))
                                        .addChild(new PixelLiteral("500px"))
                                )
                                .addChild((new Declaration("width"))
                                        .addChild((new AddOperation())
                                                .addChild(new VariableReference("ParWidth"))
                                                .addChild((new MultiplyOperation())
                                                        .addChild(new ColorLiteral("#222222") )
                                                        .addChild(new PixelLiteral("10px"))
                                                )
                                        )
                                )
                        )
                )
        );
        return new AST(stylesheet);
    }


    public static AST variableInsideWrongIfScope() {
        Stylesheet stylesheet = new Stylesheet();

        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#menu"))
                .addChild((new IfStatement())
                        .addChild((new IfClause())
                                .addChild(new BoolLiteral("TRUE"))
                                .addChild((new VariableAssignment())
                                        .addChild(new VariableReference("ParWidth"))
                                        .addChild(new PixelLiteral("500px"))
                                )
                        )
                )
                .addChild((new Declaration("width"))
                        .addChild((new AddOperation())
                                .addChild(new VariableReference("ParWidth"))
                                .addChild((new MultiplyOperation())
                                        .addChild(new ColorLiteral("#222222") )
                                        .addChild(new PixelLiteral("10px"))
                                )
                        )
                )
        );
        return new AST(stylesheet);
    }
}
