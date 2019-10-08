package nl.han.ica.icss.parser.transformer;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.transforms.RemoveIf;
import nl.han.ica.icss.transforms.Transform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformRemoveIfTest {
    private Transform transform;

    @BeforeEach
    public void beforeEach() {
        transform = new RemoveIf();
    }

    @Test
    public void nothingToRemove() {
        AST sut = RemoveIfFixtures.noIfToRemove();
        AST exp = RemoveIfFixtures.noIfToRemoveExpected();
        transform.apply(sut);
        assertEquals(exp, sut);
    }

    @Test
    public void simpleIfToRemove() {
        AST sut = RemoveIfFixtures.simpleIfToRemove();
        AST exp = RemoveIfFixtures.simpleIfToRemoveExpected();
        transform.apply(sut);
        assertEquals(exp, sut);
    }

    @Test
    public void simpleIfToKeep() {
        AST sut = RemoveIfFixtures.simpleIfToKeep();
        AST exp = RemoveIfFixtures.simpleIfToKeepExpected();
        transform.apply(sut);
        assertEquals(exp, sut);
    }

    @Test
    public void nestedIfKeepBoth() {
        AST sut = RemoveIfFixtures.nestedIfKeepBoth();
        AST exp = RemoveIfFixtures.nestedIfKeepBothExpected();
        transform.apply(sut);
        assertEquals(exp, sut);
    }

    @Test
    public void nestedIfKeepOuter() {
        AST sut = RemoveIfFixtures.nestedIfKeepOuter();
        AST exp = RemoveIfFixtures.nestedIfKeepOuterExpected();
        transform.apply(sut);
        assertEquals(exp, sut);
    }

    @Test
    public void nestedIfDontKeepInnerWhenOuterGetsRemoved() {
        AST sut = RemoveIfFixtures.nestedIfDontKeepInnerWhenOuterGetsRemoved();
        AST exp = RemoveIfFixtures.nestedIfDontKeepInnerWhenOuterGetsRemovedExpected();
        transform.apply(sut);
        assertEquals(exp, sut);
    }
}
