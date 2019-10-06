package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.IfClause;

public class IfClauseFactory {
    public static IfClause make(ICSSParser.If_clauseContext if_clauseContext) {
        return make(if_clauseContext.expression(), if_clauseContext.scope());
    }

    private static IfClause make(ICSSParser.ExpressionContext expressionContext, ICSSParser.ScopeContext scopeContext) {
        return new IfClause(ExpressionFactory.make(expressionContext), BodyFactory.make(scopeContext));
    }
}
