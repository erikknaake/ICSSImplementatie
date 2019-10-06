package nl.han.ica.icss.checker.typesystem;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.VariableAssignment;
import nl.han.ica.icss.ast.VariableReference;
import nl.han.ica.icss.ast.types.ExpressionType;

public class VariableDefiner {
    private DeclaredVariables declaredVariables;
    private static VariableDefiner instance;

    private VariableDefiner() {
        declaredVariables = DeclaredVariables.getInstance();
    }

    public static VariableDefiner getInstance() {
        if(instance == null)
            instance = new VariableDefiner();
        return instance;
    }

    public void tryDefineVariable(ASTNode node) {
        if(node instanceof VariableAssignment) {
            declareVariable((VariableAssignment) node);
        }
    }

    public boolean isVariableDefined(VariableReference variableReference) {
        return declaredVariables.getVariableType(variableReference.name) != null;
    }

    public ExpressionType getVariableType(String variableName) {
        return declaredVariables.getVariableType(variableName);
    }

    private void declareVariable(VariableAssignment variableAssignment) {
        defineVariable(variableAssignment.name, TypeResolver.resolve(variableAssignment.expression));
    }

    private void defineVariable(VariableReference variableReference, ExpressionType type) {
        declaredVariables.addVariable(variableReference.name, type);
    }
}
