package nl.han.ica.icss.typesystem;

import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;

/**
 * Keeps track of all variable types
 */
public class DeclaredVariablesTypes {
    private static DeclaredVariablesTypes instance;

    private HashMap<String, ExpressionType> declaredVariables;

    private DeclaredVariablesTypes() {
        declaredVariables = new HashMap<>();
    }

    public void addVariable(String variableName, ExpressionType type) {
        declaredVariables.put(variableName, type);
    }

    public void clear() {
        declaredVariables.clear();
    }

    /**
     * Returns the type of the declared variable
     * Returns null when the variable is not defined
     * @return Type of variable
     */
    public ExpressionType getVariableType(String variableName) {
        return declaredVariables.get(variableName);
    }

    public static DeclaredVariablesTypes getInstance() {
        if(instance == null)
            instance = new DeclaredVariablesTypes();
        return instance;
    }
}
