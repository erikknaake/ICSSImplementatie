package nl.han.ica.icss.parser.checker;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.checker.DeclarationTypeChecker;
import org.junit.jupiter.api.Test;

public class DeclarationTypeCheckerTest extends CheckerTest {
    @Override
    protected void addCheckers() {
        checkers.add(new DeclarationTypeChecker());
    }

    @Test
    public void wrongType() {
        AST ast = CheckDeclarationTypeFixtures.wrongType();
        checker.check(ast);
        assertNoErrors(ast.root);
    }

    @Test
    public void correctType() {
        AST ast = CheckDeclarationTypeFixtures.correctType();
        checker.check(ast);
        assertNoErrors(ast.root);
    }
}