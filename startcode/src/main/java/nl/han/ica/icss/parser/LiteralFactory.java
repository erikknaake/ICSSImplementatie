package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.Literal;
import nl.han.ica.icss.ast.literals.*;

public class LiteralFactory {
    public static Literal make(ICSSParser.LiteralContext literalContext) {
        if (literalContext.color_literal() != null)
            return makeColorLiteral(literalContext.color_literal());
        else if (literalContext.pixel_literal() != null)
            return makePixelLiteral(literalContext.pixel_literal());
        else if (literalContext.percentage_literal() != null)
            return makePercentageLiteral(literalContext.percentage_literal());
        else if (literalContext.scalar_literal() != null)
            return makeScalarLiteral(literalContext.scalar_literal());
        else if (literalContext.bool_literal() != null)
            return makeBooleanLiteral(literalContext.bool_literal());
        else
            throw new IllegalStateException("No literal found inside LiteralContext");
    }

    private static ColorLiteral makeColorLiteral(ICSSParser.Color_literalContext color) {
        return new ColorLiteral(color.getText());
    }

    private static PercentageLiteral makePercentageLiteral(ICSSParser.Percentage_literalContext percentage) {
        return new PercentageLiteral(percentage.getText());
    }

    private static PixelLiteral makePixelLiteral(ICSSParser.Pixel_literalContext pixel) {
        return new PixelLiteral(pixel.getText());
    }

    private static ScalarLiteral makeScalarLiteral(ICSSParser.Scalar_literalContext scalar) {
        return new ScalarLiteral(scalar.getText());
    }

    private static BoolLiteral makeBooleanLiteral(ICSSParser.Bool_literalContext bool) {
        return new BoolLiteral(bool.getText());
    }
}
