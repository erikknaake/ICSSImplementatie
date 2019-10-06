package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.VariableReference;
import nl.han.ica.icss.checker.typesystem.VariableDefiner;

public class UndefinedVariableChecker implements IChecker {

    @Override
    public void check(ASTNode node) {
       if(node instanceof VariableReference) {
            if(!VariableDefiner.getInstance().isVariableDefined((VariableReference) node)) {
                node.setError("Variable " + ((VariableReference) node).name + " is not defined");
            }
        }
    }
}
