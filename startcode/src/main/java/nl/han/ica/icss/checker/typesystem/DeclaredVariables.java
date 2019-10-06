package nl.han.ica.icss.checker.typesystem;

import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;

public class DeclaredVariables {
    private static DeclaredVariables instance;

    private HashMap<String, ExpressionType> declaredVariables;

    private DeclaredVariables() {
        declaredVariables = new HashMap<>();
    }

    public void addVariable(String variableName, ExpressionType type) {
        declaredVariables.put(variableName, type);
    }

    /**
     * Returns the type of the declared variable
     * Returns null when the variable is not defined
     * @return Type of variable
     */
    public ExpressionType getVariableType(String variableName) {
        return declaredVariables.get(variableName);
    }

    public static DeclaredVariables getInstance() {
        if(instance == null)
            instance = new DeclaredVariables();
        return instance;
    }
}
