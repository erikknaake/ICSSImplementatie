package nl.han.ica.icss.typesystem;

import nl.han.ica.icss.ast.Literal;

import java.util.HashMap;
import java.util.Map;

public class VariableValues {
    private Map<String, Literal> variableValues;

    private static VariableValues instance;

    private VariableValues() {
        variableValues = new HashMap<>();
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
        variableValues.put(key, value);
    }

    public Literal get(String key) {
        return variableValues.get(key);
    }
}
