package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.ASTNode;

public class StatementFactory {
    public static ASTNode make(ICSSParser.StatementContext statementContext) {
        if(statementContext.declaration() != null)
            return DeclarationFactory.make(statementContext.declaration());
        else if(statementContext.variable_assignment() != null)
            return VariableAssignmentFactory.make(statementContext.variable_assignment());
        else if(statementContext.stylerule() != null)
            return StyleRuleFactory.make(statementContext.stylerule());
        else if(statementContext.if_clause() != null)
            return IfClauseFactory.make(statementContext.if_clause());
        else
            throw new IllegalStateException("No valid statement found inside StatementContext");
    }
}
