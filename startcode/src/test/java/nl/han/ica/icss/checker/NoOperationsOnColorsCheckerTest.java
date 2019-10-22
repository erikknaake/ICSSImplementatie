package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.AST;
import org.junit.jupiter.api.Test;

public class NoOperationsOnColorsCheckerTest extends CheckerTest{

    @Override
    public void addCheckers() {
        checkers.add(new NoOperationsOnColorsChecker());
    }

    @Test
    public void NoColorOperation() {
        AST ast = CheckOperationsFixtures.noColorOperation();
        checker.checkNode(ast);
        assertNoErrors(ast.root);
    }

    @Test
    public void ColorOperation() {
        AST ast = CheckOperationsFixtures.colorOperation();
        checker.checkNode(ast);
        assertHasErrors(ast.root);
    }
}
