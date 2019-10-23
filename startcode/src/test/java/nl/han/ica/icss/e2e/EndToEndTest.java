package nl.han.ica.icss.e2e;

import nl.han.ica.icss.CheckingTest;
import nl.han.ica.icss.TestParser;
import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.checker.*;
import nl.han.ica.icss.generator.Generator;
import nl.han.ica.icss.transforms.EvalExpressions;
import nl.han.ica.icss.transforms.OptimiseIdAsDirectChild;
import nl.han.ica.icss.transforms.RemoveIf;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EndToEndTest extends CheckingTest {

    private void transform(AST ast) {
        (new EvalExpressions()).apply(ast);
        (new RemoveIf()).apply(ast);
        (new OptimiseIdAsDirectChild()).apply(ast);
    }

    private void check(AST ast) {
        checker.check(ast);
    }

    private void doPipeLine(String file, String expectation)  throws IOException {
        AST sut = TestParser.parseTestFile(file);
        check(sut);
        assertNoErrors(sut.root);
        transform(sut);
        assertEquals(expectation, new Generator().generate(sut));
    }

    @Override
    protected void addCheckers() {
        checkers.add(new UndefinedVariableChecker());
        checkers.add(new NoOperationsOnColorsChecker());
        checkers.add(new ConditionalIfChecker());
        checkers.add(new DeclarationTypeChecker());
        checkers.add(new OperandTypeChecker());
    }

    @Test
    void testParseLevel0() throws IOException {
        doPipeLine("level0.icss", EndToEndFixtures.level0());
    }

    @Test
    void testParseLevel1() throws IOException {
        doPipeLine("level1.icss", EndToEndFixtures.level1());
    }

    @Test
    void testParseLevel2() throws IOException {
        doPipeLine("level2.icss", EndToEndFixtures.level2());
    }

    @Test
    void testParseLevel3() throws IOException {
        doPipeLine("level3.icss", EndToEndFixtures.level3());
    }

    @Test
    void testMultiSelector() throws IOException {
        doPipeLine("multiselector.icss", EndToEndFixtures.multiSelector());
    }

    @Test
    void testCompositeSelector() throws IOException {
        doPipeLine("compositeselector.icss", EndToEndFixtures.compositeSelector());
    }

    @Test
    void testIfElse() throws IOException {
        doPipeLine("ifelse.icss", EndToEndFixtures.ifElse());
    }

    @Test
    void testDirectIdChild() throws IOException {
        doPipeLine("directidchild.icss", EndToEndFixtures.directIdChild());
    }

    @Test
    void testHardTest() throws IOException {
        doPipeLine("hard_test.icss", EndToEndFixtures.hardTest());
    }
}
