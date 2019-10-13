package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.ASTNode;

public class StatementFactory {
    public static ASTNode make(ICSSParser.StatementContext statementContext) {
        if (statementContext.declaration() != null)
            return DeclarationFactory.make(statementContext.declaration());
        else if (statementContext.variable_assignment() != null)
            return VariableAssignmentFactory.make(statementContext.variable_assignment());
        else if (statementContext.stylerule() != null)
            return StyleRuleFactory.make(statementContext.stylerule());
        else
            throw new IllegalStateException("No valid statement found inside StatementContext");
    }

    public static ASTNode make(ICSSParser.Stylerule_statementContext statementContext) {
        if (statementContext.declaration() != null)
            return DeclarationFactory.make(statementContext.declaration());
        else if (statementContext.variable_assignment() != null)
            return VariableAssignmentFactory.make(statementContext.variable_assignment());
        else if (statementContext.if_statement() != null)
            return IfStatementFactory.make(statementContext.if_statement());
        else
            throw new IllegalStateException("No valid statement found inside StatementContext");
    }
}
