package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.AST;
import org.junit.jupiter.api.Test;

public class DeclarationTypeCheckerTest extends CheckerTest {
    @Override
    protected void addCheckers() {
        checkers.add(new DeclarationTypeChecker());
    }

    @Test
    public void wrongType() {
        AST ast = CheckDeclarationTypeFixtures.wrongType();
        checker.checkNode(ast);
        assertNoErrors(ast.root);
    }

    @Test
    public void correctType() {
        AST ast = CheckDeclarationTypeFixtures.correctType();
        checker.checkNode(ast);
        assertNoErrors(ast.root);
    }
}
