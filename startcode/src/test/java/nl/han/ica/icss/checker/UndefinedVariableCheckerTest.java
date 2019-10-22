package nl.han.ica.icss.checker;

import nl.han.ica.icss.CheckingTest;
import nl.han.ica.icss.ast.AST;
import org.junit.jupiter.api.Test;

public class UndefinedVariableCheckerTest extends CheckingTest {
    @Override
    public void addCheckers() {
        checkers.add(new UndefinedVariableChecker());
    }

    @Test
    public void definedVariable() {
        AST ast = CheckUndefinedVariablesFixtures.definedVariable();
        checker.check(ast);
        assertNoErrors(ast.root);
    }

    @Test
    public void undefinedVariable() {
        AST ast = CheckUndefinedVariablesFixtures.undefinedVariable();
        checker.check(ast);
        assertHasErrors(ast.root);
    }

    @Test
    public void variableInsideScope() {
        AST ast = CheckUndefinedVariablesFixtures.variableInsideScope();
        checker.check(ast);
        assertNoErrors(ast.root);
    }

    @Test
    public void variableInWrongScope() {
        AST ast = CheckUndefinedVariablesFixtures.variableInsideWrongScope();
        checker.check(ast);
        assertHasErrors(ast.root);
    }

    @Test
    public void variableInsideIfScope() {
        AST ast = CheckUndefinedVariablesFixtures.variableInsideIfScope();
        checker.check(ast);
        assertNoErrors(ast.root);
    }

    @Test
    public void variableInWrongIfScope() {
        AST ast = CheckUndefinedVariablesFixtures.variableInsideWrongIfScope();
        checker.check(ast);
        assertHasErrors(ast.root);
    }
}
