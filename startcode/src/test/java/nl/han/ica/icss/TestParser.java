package nl.han.ica.icss;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.parser.ASTListener;
import nl.han.ica.icss.parser.ICSSLexer;
import nl.han.ica.icss.parser.ICSSParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.fail;

public class TestParser {
    public static AST parseTestFile(String resource) throws IOException {

        //Open test file to parse
        ClassLoader classLoader = TestParser.class.getClassLoader();

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
}
