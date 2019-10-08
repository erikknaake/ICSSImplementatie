package nl.han.ica.icss.typesystem;

import nl.han.ica.icss.ast.Literal;

import java.util.HashMap;
import java.util.Map;

/**
 * Keeps track of all variables that have been assigned
 */
public class VariableValues {
    private Map<String, Literal> variableValues;

    private static VariableValues instance;
    private DeclaredVariables declaredVariables;

    private VariableValues() {
        variableValues = new HashMap<>();
        declaredVariables = DeclaredVariables.getInstance();
    }

    public static VariableValues getInstance() {
        if(instance == null)
            instance = new VariableValues();
        return instance;
    }

    public void clear() {
        variableValues.clear();
    }

    public void put(String key, Literal value) {
        declaredVariables.addVariable(key, TypeResolver.resolve(value));
        variableValues.put(key, value);
    }

    public Literal get(String key) {
        return variableValues.get(key);
    }
}