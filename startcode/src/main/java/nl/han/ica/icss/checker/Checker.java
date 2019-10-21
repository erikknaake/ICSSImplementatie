package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.typesystem.VariableDefiner;

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
        boolean isScope = node instanceof Stylerule || node instanceof IfClause || node instanceof ElseClause;
        if (isScope)
            variableDefiner.pushScope();

        variableDefiner.tryDefineVariable(node);
        for (IChecker checker : checkers) {
            checker.check(node);
        }
        if (node.getChildren().size() != 0) {
            for (ASTNode astNode : node.getChildren()) {
                check(astNode);
            }
        }
        if (isScope)
            variableDefiner.popScope();
    }
}
