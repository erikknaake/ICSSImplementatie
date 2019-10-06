package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.checker.typesystem.VariableDefiner;

import java.util.ArrayList;
import java.util.List;

public class Checker {
    private List<IChecker> checkers = new ArrayList<>();
    private VariableDefiner variableDefiner = VariableDefiner.getInstance();

    public Checker(List<IChecker> checkers) {
        this.checkers = checkers;
    }

    public Checker() {
        checkers.add(new UndefinedVariableChecker());
        checkers.add(new NoOperationsOnColorsChecker());
        checkers.add(new ConditionalIfChecker());
        checkers.add(new DeclarationTypeChecker());
        checkers.add(new OperandTypeChecker());
    }

    public void check(AST ast) {
        variableDefiner.clear();
        check(ast.root);
    }

    private void check(ASTNode node) {
        variableDefiner.tryDefineVariable(node);
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
