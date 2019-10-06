package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.VariableAssignment;
import nl.han.ica.icss.ast.VariableReference;

import java.util.ArrayList;
import java.util.List;

public class UndefinedVariableChecker implements IChecker {
    private List<String> definedVariables;

    public UndefinedVariableChecker() {
        definedVariables = new ArrayList<>();
    }

    @Override
    public void check(ASTNode node) {
        if(node instanceof VariableAssignment) {
            declareVariable((VariableAssignment) node);
        } else if(node instanceof VariableReference) {
            if(!isVariableDefined((VariableReference) node)) {
                node.setError("Variable " + ((VariableReference) node).name + " is not defined");
            }
        }
    }

    private boolean isVariableDefined(VariableReference variableReference) {
        return definedVariables.contains(variableReference.name);
    }

    private void declareVariable(VariableAssignment variableAssignment) {
        defineVariable(variableAssignment.name);
    }

    private void defineVariable(VariableReference variableReference) {
        definedVariables.add(variableReference.name);
    }
}
