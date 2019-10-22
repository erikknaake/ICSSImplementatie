package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.AST;
import org.junit.jupiter.api.Test;

public class UndefinedVariableCheckerTest extends CheckerTest {
    @Override
    public void addCheckers() {
        checkers.add(new UndefinedVariableChecker());
    }

    @Test
    public void definedVariable() {
        AST ast = CheckUndefinedVariablesFixtures.definedVariable();
        checker.checkNode(ast);
        assertNoErrors(ast.root);
    }

    @Test
    public void undefinedVariable() {
        AST ast = CheckUndefinedVariablesFixtures.undefinedVariable();
        checker.checkNode(ast);
        assertHasErrors(ast.root);
    }

    @Test
    public void variableInsideScope() {
        AST ast = CheckUndefinedVariablesFixtures.variableInsideScope();
        checker.checkNode(ast);
        assertNoErrors(ast.root);
    }

    @Test
    public void variableInWrongScope() {
        AST ast = CheckUndefinedVariablesFixtures.variableInsideWrongScope();
        checker.checkNode(ast);
        assertHasErrors(ast.root);
    }

    @Test
    public void variableInsideIfScope() {
        AST ast = CheckUndefinedVariablesFixtures.variableInsideIfScope();
        checker.checkNode(ast);
        assertNoErrors(ast.root);
    }

    @Test
    public void variableInWrongIfScope() {
        AST ast = CheckUndefinedVariablesFixtures.variableInsideWrongIfScope();
        checker.checkNode(ast);
        assertHasErrors(ast.root);
    }
}
