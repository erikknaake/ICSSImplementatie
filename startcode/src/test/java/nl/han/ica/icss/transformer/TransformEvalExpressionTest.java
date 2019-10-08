package nl.han.ica.icss.transformer;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.transforms.EvalExpressions;
import nl.han.ica.icss.transforms.Transform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformEvalExpressionTest {
    private Transform transform;

    @BeforeEach
    public void beforeEach() {
        transform = new EvalExpressions();
    }

    @Test
    public void variablesShouldBeRemovedAndReplacedWithLiterals() {
        AST sut = EvalExpressionFixtures.variableAssignment();
        AST exp = EvalExpressionFixtures.variableAssignmentExpected();
        transform.apply(sut);
        assertEquals(exp, sut);
    }

    @Test
    public void variablesCanReferToOtherVariables() {
        AST sut = EvalExpressionFixtures.referringVariable();
        AST exp = EvalExpressionFixtures.referringVariableExpected();
        transform.apply(sut);
        assertEquals(exp,sut);
    }

    @Test
    public void propertiesShouldBeCalculated() {
        AST sut = EvalExpressionFixtures.calculatedProperty();
        AST exp = EvalExpressionFixtures.calculatedPropertyExpected();
        transform.apply(sut);
        assertEquals(exp,sut);
    }

    @Test
    public void variablesShouldBeCalculated() {
        AST sut = EvalExpressionFixtures.calculatedVariable();
        AST exp = EvalExpressionFixtures.calculatedVariableExpected();
        transform.apply(sut);
        assertEquals(exp,sut);
    }

    @Test
    public void multiStepCalculationPropertiesShouldBeCalculated() {
        AST sut = EvalExpressionFixtures.multiStepCalculatedProperty();
        AST exp = EvalExpressionFixtures.multiStepCalculatedPropertyExpected();
        transform.apply(sut);
        assertEquals(exp,sut);
    }
}
