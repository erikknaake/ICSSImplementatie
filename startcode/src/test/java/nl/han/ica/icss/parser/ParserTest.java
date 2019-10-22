package nl.han.ica.icss.parser;

import nl.han.ica.icss.TestParser;
import nl.han.ica.icss.ast.AST;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {



	@Test
	void testParseLevel0() throws IOException {

		AST sut = TestParser.parseTestFile("level0.icss");
		AST exp = Fixtures.uncheckedLevel0();
		assertEquals(exp,sut);
	}
	@Test
	void testParseLevel1() throws IOException {

		AST sut = TestParser.parseTestFile("level1.icss");
		AST exp = Fixtures.uncheckedLevel1();
		assertEquals(exp,sut);
	}
	@Test
	void testParseLevel2() throws IOException {

		AST sut = TestParser.parseTestFile("level2.icss");
		AST exp = Fixtures.uncheckedLevel2();
		assertEquals(exp,sut);
	}
	@Test
	void testParseLevel3() throws IOException {

		AST sut = TestParser.parseTestFile("level3.icss");
		AST exp = Fixtures.uncheckedLevel3();
		assertEquals(exp,sut);
	}

	@Test
	void testParseMultiSelectors() throws IOException {

		AST sut = TestParser.parseTestFile("multiselector.icss");
		AST exp = Fixtures.multiselector();
		assertEquals(exp,sut);
	}

	@Test
	void testParseCompositeSelectors() throws IOException {

		AST sut = TestParser.parseTestFile("compositeselector.icss");
		AST exp = Fixtures.compositeselector();
		assertEquals(exp,sut);
	}

	@Test
	void testParseIfElse() throws IOException {

		AST sut = TestParser.parseTestFile("ifelse.icss");
		AST exp = Fixtures.ifelse();
		assertEquals(exp,sut);
	}

	@Test
	void testVariableAssignmentInsideStylerule() throws IOException {

		AST sut = TestParser.parseTestFile("assignment.icss");
		AST exp = Fixtures.variableAssignmentInsideStylerule();
		assertEquals(exp,sut);
	}

	@Test
	void testNoIfInsideStylesheet() {
		// Expect the parse to fail
		assertThrows(AssertionFailedError.class, () -> {
			TestParser.parseTestFile("ifInsideStylesheet.icss");
		});
	}
}
