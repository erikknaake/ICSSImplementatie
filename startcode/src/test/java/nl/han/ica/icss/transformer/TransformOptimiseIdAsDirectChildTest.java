package nl.han.ica.icss.transformer;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.transforms.OptimiseIdAsDirectChild;
import nl.han.ica.icss.transforms.Transform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformOptimiseIdAsDirectChildTest {
    private Transform transform;

    @BeforeEach
    public void beforeEach() {
        transform = new OptimiseIdAsDirectChild();
    }

    @Test
    public void nothingToRemove() {
        AST sut = OptimisedIdAsDirectChildFixtures.directIdChild();
        AST exp = OptimisedIdAsDirectChildFixtures.directIdChildExpected();
        transform.apply(sut);
        assertEquals(exp, sut);
    }
}
