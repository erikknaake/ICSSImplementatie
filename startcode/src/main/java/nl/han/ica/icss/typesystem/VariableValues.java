package nl.han.ica.icss.typesystem;

import nl.han.ica.icss.ast.Literal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Keeps track of all variables that have been assigned
 */
public class VariableValues {
    private LinkedList<Map<String, Literal>> variableValues;

    private static VariableValues instance;
    private DeclaredVariablesTypes declaredVariables;

    private VariableValues() {
        variableValues = new LinkedList<>();
        pushScope();
        declaredVariables = DeclaredVariablesTypes.getInstance();
    }

    public void pushScope() {
        variableValues.addFirst(new HashMap<>());
    }

    public void popScope() {
        variableValues.removeFirst();
    }

    public static VariableValues getInstance() {
        if (instance == null)
            instance = new VariableValues();
        return instance;
    }

    public void clear() {
        variableValues.clear();
        pushScope();
    }

    public void put(String key, Literal value) {
        declaredVariables.addVariable(key, value.getType());
        variableValues.getFirst().put(key, value);
    }

    public Literal get(String key) {
        for (Map<String, Literal> variables : variableValues) {
            Literal literal = variables.get(key);
            if (literal != null)
                return literal;
        }
        return null;
    }
}
