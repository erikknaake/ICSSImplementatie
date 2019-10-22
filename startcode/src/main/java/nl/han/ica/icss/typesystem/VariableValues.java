package nl.han.ica.icss.typesystem;

import nl.han.ica.icss.ast.Literal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Keeps track of all variables that have been assigned
 */
public class VariableValues {
    private IScopedMap<String, Literal> variableValues;

    private static VariableValues instance;
    private DeclaredVariablesTypes declaredVariables;

    private VariableValues() {
        variableValues = new ScopedMap<>();
        declaredVariables = DeclaredVariablesTypes.getInstance();
    }

    public void pushScope() {
        variableValues.pushScope();
    }

    public void popScope() {
        variableValues.popScope();
    }

    public static VariableValues getInstance() {
        if (instance == null)
            instance = new VariableValues();
        return instance;
    }

    public void clear() {
        variableValues.clear();
    }

    public void put(String key, Literal value) {
        declaredVariables.addVariable(key, value.getType());
        variableValues.put(key, value);
    }

    public Literal get(String key) {
        return variableValues.get(key);
    }
}
