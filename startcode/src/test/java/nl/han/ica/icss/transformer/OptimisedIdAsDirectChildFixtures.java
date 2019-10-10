package nl.han.ica.icss.transformer;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.Declaration;
import nl.han.ica.icss.ast.Stylerule;
import nl.han.ica.icss.ast.Stylesheet;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.selectors.*;

public class OptimisedIdAsDirectChildFixtures {
    public static AST directIdChild() {
        Stylesheet stylesheet = new Stylesheet();
		/*
        html > #id {
            color: #123456;
        }
		*/
        stylesheet.addChild((new Stylerule())
                .addChild(new CompositeSelector(
                                new TagSelector("html"),
                                new IdSelector("#id"),
                                new SelectorCompositionOperator(">")
                        )
                )
                .addChild((new Declaration("color"))
                        .addChild(new ColorLiteral("#123456")))
        );
		/*
        html > .class {
            color: #123456;
        }
		*/
        stylesheet.addChild((new Stylerule())
                .addChild(new CompositeSelector(
                                new TagSelector("html"),
                                new ClassSelector(".class"),
                                new SelectorCompositionOperator(">")
                        )
                )
                .addChild((new Declaration("color"))
                        .addChild(new ColorLiteral("#123456")))
        );
        return new AST(stylesheet);
    }

    public static AST directIdChildExpected() {
        Stylesheet stylesheet = new Stylesheet();
		/*
        #id {
            color: #123456;
        }
		*/
        stylesheet.addChild((new Stylerule())
                .addChild(new IdSelector("#id"))
                .addChild((new Declaration("color"))
                        .addChild(new ColorLiteral("#123456")))
        );
		/*
        html > .class {
            color: #123456;
        }
		*/
        stylesheet.addChild((new Stylerule())
                .addChild(new CompositeSelector(
                                new TagSelector("html"),
                                new ClassSelector(".class"),
                                new SelectorCompositionOperator(">")
                        )
                )
                .addChild((new Declaration("color"))
                        .addChild(new ColorLiteral("#123456")))
        );
        return new AST(stylesheet);
    }
}
