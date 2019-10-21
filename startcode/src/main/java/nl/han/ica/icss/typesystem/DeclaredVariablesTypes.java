package nl.han.ica.icss.typesystem;

import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Keeps track of all variable types
 */
public class DeclaredVariablesTypes {
    private static DeclaredVariablesTypes instance;

    private LinkedList<Map<String, ExpressionType>> declaredVariables;

    private DeclaredVariablesTypes() {
        declaredVariables = new LinkedList<>();
        pushScope();
    }

    public void pushScope() {
        declaredVariables.addFirst(new HashMap<>());
    }

    public void popScope() {
        declaredVariables.removeFirst();
    }

    public void addVariable(String variableName, ExpressionType type) {
        declaredVariables.getFirst().put(variableName, type);
    }

    public void clear() {
        declaredVariables.clear();
        pushScope();
    }

    /**
     * Returns the type of the declared variable
     * Returns null when the variable is not defined
     *
     * @return Type of variable
     */
    public ExpressionType getVariableType(String variableName) {
        for (Map<String, ExpressionType> variables : declaredVariables) {
            ExpressionType type = variables.get(variableName);
            if (type != null)
                return type;
        }
        return null;
    }

    public static DeclaredVariablesTypes getInstance() {
        if (instance == null)
            instance = new DeclaredVariablesTypes();
        return instance;
    }
}
