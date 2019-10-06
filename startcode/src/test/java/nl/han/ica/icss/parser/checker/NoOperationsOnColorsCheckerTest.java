package nl.han.ica.icss.parser.checker;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.checker.NoOperationsOnColorsChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NoOperationsOnColorsCheckerTest extends CheckerTest{

    @BeforeEach
    public void before() {
        checker = new NoOperationsOnColorsChecker();
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
