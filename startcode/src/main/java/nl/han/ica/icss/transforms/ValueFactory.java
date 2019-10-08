package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.Literal;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.types.ExpressionType;

public class ValueFactory {
    public static Literal make(ExpressionType type, String value) {
        switch (type) {
            case SCALAR:
                return new ScalarLiteral(value);
            case PERCENTAGE:
                return new PercentageLiteral(value + "%");
            case PIXEL:
                return new PixelLiteral(value + "px");
            case BOOL:
                return new BoolLiteral(value);
            default:
                throw new IllegalArgumentException("A literal value cannot be constructed for an " + type + " datatype");
        }
    }
}
