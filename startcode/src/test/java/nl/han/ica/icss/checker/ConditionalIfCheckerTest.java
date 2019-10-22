package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.AST;
import org.junit.jupiter.api.Test;

public class ConditionalIfCheckerTest extends CheckerTest {
    @Override
    protected void addCheckers() {
        checkers.add(new ConditionalIfChecker());
    }

    @Test
    public void conditionalIf() {
        AST ast = CheckConditionalIfFixtures.conditionalIf();
        checker.checkNode(ast);
        assertNoErrors(ast.root);
    }

    @Test
    public void conditionalIfViaVariableReference() {
        AST ast = CheckConditionalIfFixtures.conditionalIfViaVariableReference();
        checker.checkNode(ast);
        assertNoErrors(ast.root);
    }

    @Test
    public void unconditionalIf() {
        AST ast = CheckConditionalIfFixtures.unconditionalIf();
        checker.checkNode(ast);
        assertHasErrors(ast.root);
    }

    @Test
    public void unconditionalIfViaVariableReference() {
        AST ast = CheckConditionalIfFixtures.unconditionalIfViaVariableReference();
        checker.checkNode(ast);
        assertHasErrors(ast.root);
    }
}
