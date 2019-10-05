package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.VariableReference;

public class VariableReferenceFactory {
    public static VariableReference make(ICSSParser.Variable_referenceContext variable_referenceContext) {
        return new VariableReference(variable_referenceContext.getText());
    }
}
