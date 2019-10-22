package nl.han.ica.icss.checker;

import nl.han.ica.icss.ASTWalker;
import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.typesystem.VariableDefiner;

import java.util.ArrayList;
import java.util.List;

public class Checker {
    private List<IChecker> checkers = new ArrayList<>();
    private VariableDefiner variableDefiner = VariableDefiner.getInstance();
    private ASTWalker walker;

    public Checker(List<IChecker> checkers) {
        this.checkers = checkers;
        initializeWalker();
    }

    public Checker() {
        initializeDefaultCheckers();
        initializeWalker();
    }

    private void initializeDefaultCheckers() {
        checkers.add(new UndefinedVariableChecker());
        checkers.add(new NoOperationsOnColorsChecker());
        checkers.add(new ConditionalIfChecker());
        checkers.add(new DeclarationTypeChecker());
        checkers.add(new OperandTypeChecker());
    }

    private void initializeWalker() {
        walker = new ASTWalker(
                (ASTNode node) -> variableDefiner.pushScope(),
                this::checkNode,
                (ASTNode node) -> variableDefiner.popScope());
    }

    public void check(AST ast) {
        variableDefiner.clear();
        walker.walk(ast);
    }

    private void checkNode(ASTNode node) {
        variableDefiner.tryDefineVariable(node);
        for (IChecker checker : checkers) {
            checker.check(node);
        }
    }
}
