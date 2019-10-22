package nl.han.ica.icss;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.checker.Checker;
import nl.han.ica.icss.checker.IChecker;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class CheckingTest {
    protected Checker checker;
    protected ArrayList<IChecker> checkers;
    protected abstract void addCheckers();

    @BeforeEach
    public final void beforeChecker() {
        checkers = new ArrayList<>();
        addCheckers();
        checker = new Checker(checkers);
    }

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
