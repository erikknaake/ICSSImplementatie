package nl.han.ica.icss.checker;

import nl.han.ica.icss.CheckingTest;
import nl.han.ica.icss.ast.AST;
import org.junit.jupiter.api.Test;

public class NoOperationsOnColorsCheckerTest extends CheckingTest {

    @Override
    public void addCheckers() {
        checkers.add(new NoOperationsOnColorsChecker());
    }

    @Test
    public void NoColorOperation() {
        AST ast = CheckOperationsFixtures.noColorOperation();
        checker.check(ast);
        assertNoErrors(ast.root);
    }

    @Test
    public void ColorOperation() {
        AST ast = CheckOperationsFixtures.colorOperation();
        checker.check(ast);
        assertHasErrors(ast.root);
    }
}
