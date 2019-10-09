package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.ElseClause;
import nl.han.ica.icss.ast.IfClause;
import nl.han.ica.icss.ast.IfStatement;

public class IfStatementFactory {
    public static IfStatement make(ICSSParser.If_statementContext if_statementContext) {
        return new IfStatement(makeIfClause(if_statementContext.if_clause()), makeElseClause(if_statementContext.else_clause()));
    }

    private static IfClause makeIfClause(ICSSParser.If_clauseContext if_clauseContext) {
        return IfClauseFactory.make(if_clauseContext);
    }

    private static ElseClause makeElseClause(ICSSParser.Else_clauseContext else_clauseContext) {
        if (else_clauseContext != null) {
            return new ElseClause(BodyFactory.make(else_clauseContext.scope().body()));
        } else
            return null;
    }
}
