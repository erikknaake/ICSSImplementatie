package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.AST;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

	AST parseTestFile(String resource) throws IOException {

		//Open test file to parse
		ClassLoader classLoader = this.getClass().getClassLoader();

    	InputStream inputStream = classLoader.getResourceAsStream(resource);
        CharStream charStream = CharStreams.fromStream(inputStream);
        ICSSLexer lexer = new ICSSLexer(charStream);

	    CommonTokenStream tokens = new CommonTokenStream(lexer);

        ICSSParser parser = new ICSSParser(tokens);
		parser.setErrorHandler(new BailErrorStrategy());

		//Setup collection of the parse error messages
		BaseErrorListener errorListener = new BaseErrorListener() {
			private String message;
			public void syntaxError(Recognizer<?,?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
				message = msg;
			}
			public String toString() {
				return message;
			}
		};
		parser.removeErrorListeners();
		parser.addErrorListener(errorListener);

		//Parse & extract AST
 		ASTListener listener = new ASTListener();
		try {
			ParseTree parseTree = parser.stylesheet();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(listener, parseTree);
		} catch(ParseCancellationException e) {
			fail(errorListener.toString());
		}

		return listener.getAST();
	}

	@Test
	void testParseLevel0() throws IOException {

		AST sut = parseTestFile("level0.icss");
		AST exp = Fixtures.uncheckedLevel0();
		assertEquals(exp,sut);
	}
	@Test
	void testParseLevel1() throws IOException {

		AST sut = parseTestFile("level1.icss");
		AST exp = Fixtures.uncheckedLevel1();
		assertEquals(exp,sut);
	}
	@Test
	void testParseLevel2() throws IOException {

		AST sut = parseTestFile("level2.icss");
		AST exp = Fixtures.uncheckedLevel2();
		assertEquals(exp,sut);
	}
	@Test
	void testParseLevel3() throws IOException {

		AST sut = parseTestFile("level3.icss");
		AST exp = Fixtures.uncheckedLevel3();
		assertEquals(exp,sut);
	}

	@Test
	void testParseMultiSelectors() throws IOException {

		AST sut = parseTestFile("multiselector.icss");
		AST exp = Fixtures.multiselector();
		assertEquals(exp,sut);
	}

	@Test
	void testParseCompositeSelectors() throws IOException {

		AST sut = parseTestFile("compositeselector.icss");
		AST exp = Fixtures.compositeselector();
		assertEquals(exp,sut);
	}

	@Test
	void testParseIfElse() throws IOException {

		AST sut = parseTestFile("ifelse.icss");
		AST exp = Fixtures.ifelse();
		assertEquals(exp,sut);
	}

	@Test
	void testVariableAssignmentInsideStylerule() throws IOException {

		AST sut = parseTestFile("assignment.icss");
		AST exp = Fixtures.variableAssignmentInsideStylerule();
		assertEquals(exp,sut);
	}
}
