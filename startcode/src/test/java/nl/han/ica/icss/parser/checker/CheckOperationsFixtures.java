package nl.han.ica.icss.parser.checker;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.selectors.IdSelector;

public class CheckOperationsFixtures {
    public static AST noColorOperation() {
        Stylesheet stylesheet = new Stylesheet();

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

    public static AST colorOperation() {
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

}
