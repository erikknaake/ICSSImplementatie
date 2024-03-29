package nl.han.ica.icss.checker;

import nl.han.ica.icss.CheckingTest;
import nl.han.ica.icss.ast.AST;
import org.junit.jupiter.api.Test;

public class ConditionalIfCheckerTest extends CheckingTest {
    @Override
    protected void addCheckers() {
        checkers.add(new ConditionalIfChecker());
    }

    @Test
    public void conditionalIf() {
        AST ast = CheckConditionalIfFixtures.conditionalIf();
        checker.check(ast);
        assertNoErrors(ast.root);
    }

    @Test
    public void conditionalIfViaVariableReference() {
        AST ast = CheckConditionalIfFixtures.conditionalIfViaVariableReference();
        checker.check(ast);
        assertNoErrors(ast.root);
    }

    @Test
    public void unconditionalIf() {
        AST ast = CheckConditionalIfFixtures.unconditionalIf();
        checker.check(ast);
        assertHasErrors(ast.root);
    }

    @Test
    public void unconditionalIfViaVariableReference() {
        AST ast = CheckConditionalIfFixtures.unconditionalIfViaVariableReference();
        checker.check(ast);
        assertHasErrors(ast.root);
    }
}
