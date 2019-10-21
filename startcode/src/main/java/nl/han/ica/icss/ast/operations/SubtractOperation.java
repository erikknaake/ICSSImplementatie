package nl.han.ica.icss.ast.operations;

import nl.han.ica.icss.ast.Literal;
import nl.han.ica.icss.ast.Operation;
import nl.han.ica.icss.transforms.ValueFactory;

public class SubtractOperation extends Operation {

    @Override
    public String getNodeLabel() {
        return "Subtract";
    }

    @Override
    public Literal evalOperation(int lhs, int rhs) {
        return ValueFactory.make(getType(), String.valueOf(lhs - rhs));
    }
}
