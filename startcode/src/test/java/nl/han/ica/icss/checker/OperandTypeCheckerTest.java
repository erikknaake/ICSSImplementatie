package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.AST;
import org.junit.jupiter.api.Test;

public class OperandTypeCheckerTest extends CheckerTest {
    @Override
    protected void addCheckers() {
        checkers.add(new OperandTypeChecker());
    }

    @Test
    public void multiplyWithOneScalar() {
        AST ast = CheckOperandTypeFixtures.multiplyWithOneScalar();
        checker.check(ast);
        assertNoErrors(ast.root);
    }

    @Test
    public void multiplyWithoutScalars() {
        AST ast = CheckOperandTypeFixtures.multiplyWithoutScalar();
        checker.check(ast);
        assertHasErrors(ast.root);
    }

    @Test
    public void addWithEqualTypes() {
        AST ast = CheckOperandTypeFixtures.addWithEqualTypes();
        checker.check(ast);
        assertNoErrors(ast.root);
    }

    @Test
    public void addWithUnequalTypes() {
        AST ast = CheckOperandTypeFixtures.addWithUnequalTypes();
        checker.check(ast);
        assertHasErrors(ast.root);
    }
}
