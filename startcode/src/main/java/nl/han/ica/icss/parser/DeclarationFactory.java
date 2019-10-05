package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.Declaration;

public class DeclarationFactory {
    public static Declaration make(ICSSParser.DeclarationContext declarationContext) {
        return makeDeclaration(declarationContext.expression(), declarationContext.property_name());
    }

    private static Declaration makeDeclaration(ICSSParser.ExpressionContext expressionContext, ICSSParser.Property_nameContext property_nameContext) {
        return new Declaration(ExpressionFactory.make(expressionContext), PropertyNameFactory.make(property_nameContext));
    }
}
