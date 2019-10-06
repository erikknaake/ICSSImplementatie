package nl.han.ica.icss.checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.types.*;

public class Checker {

    private LinkedList<HashMap<String, ExpressionType>> variableTypes;
    private List<IChecker> checkers = new ArrayList<>();

    public Checker(List<IChecker> checkers) {
        this.checkers = checkers;
    }

    public Checker() {
        checkers.add(new UndefinedVariableChecker());
        checkers.add(new NoOperationsOnColorsChecker());
    }

    public void check(AST ast) {
        check(ast.root);
    }

    private void check(ASTNode node) {
        for(IChecker checker: checkers) {
            checker.check(node);
        }
        if(node.getChildren().size() != 0) {
            for(ASTNode astNode : node.getChildren()) {
                check(astNode);
            }
        }
    }
}
