package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.ASTNode;

import java.util.ArrayList;

public class BodyFactory {
    public static ArrayList<ASTNode> make(ICSSParser.ScopeContext scopeContext) {
        return make(scopeContext.stylerule_body());
    }

    public static ArrayList<ASTNode> make(ICSSParser.BodyContext bodyContext) {
        ArrayList<ASTNode> body = new ArrayList<>();
        for(ICSSParser.StatementContext statementContext : bodyContext.statement()) {
            body.add(StatementFactory.make(statementContext));
        }
        return body;
    }

    public static ArrayList<ASTNode> make(ICSSParser.Stylerule_bodyContext stylerule_bodyContext) {
        ArrayList<ASTNode> body = new ArrayList<>();
        for(ICSSParser.Stylerule_statementContext statementContext : stylerule_bodyContext.stylerule_statement()) {
            body.add(StatementFactory.make(statementContext));
        }
        return body;
    }
}
