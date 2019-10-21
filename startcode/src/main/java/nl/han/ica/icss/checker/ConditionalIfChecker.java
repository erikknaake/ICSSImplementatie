package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.IfClause;
import nl.han.ica.icss.ast.types.ExpressionType;

public class ConditionalIfChecker implements IChecker {
    @Override
    public void check(ASTNode node) {
        if (node instanceof IfClause) {
            IfClause ifClause = (IfClause) node;
            ExpressionType expressionType = ifClause.conditionalExpression.getType();
            if (expressionType != ExpressionType.BOOL) {
                ifClause.conditionalExpression.setError("Expected conditional expression but got " + expressionType + " expression");
            }
        }
    }
}
