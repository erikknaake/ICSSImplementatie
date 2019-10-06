package nl.han.ica.icss.parser.checker;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.checker.IChecker;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class CheckerTest {
    protected IChecker checker;

    protected void assertNoErrors(ASTNode node) {
        if(node.hasError())
            fail();
        else if(node.getChildren().size() != 0) {
            for(ASTNode astNode : node.getChildren())
                assertNoErrors(astNode);
        }
    }

    protected void assertHasErrors(ASTNode node) {
        if(!hasErrors(node))
            fail();
    }

    private boolean hasErrors(ASTNode node) {
        if(node.hasError())
            return true;
        if(node.getChildren().size() != 0) {
            for(ASTNode astNode : node.getChildren()) {
                if(hasErrors(astNode))
                    return true;
            }
        }
        return false;
    }
}
