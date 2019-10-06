package nl.han.ica.icss.parser.checker;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.checker.UndefinedVariableChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UndefinedVariableCheckerTest extends CheckerTest {
    @BeforeEach
    public void before() {
        checker = new UndefinedVariableChecker();
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
}
