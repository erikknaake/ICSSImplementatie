package nl.han.ica.icss.ast;

import java.util.ArrayList;
import java.util.Objects;

public class IfClause extends ASTNode {


    public Expression conditionalExpression;
    public ArrayList<ASTNode> body = new ArrayList<>();

    public IfClause() { }

    public IfClause(Expression conditionalExpression, ArrayList<ASTNode> body) {

        this.conditionalExpression = conditionalExpression;
        this.body = body;
    }

    @Override
    public String getNodeLabel() {
        return "If_Clause";
    }
    @Override
    public ArrayList<ASTNode> getChildren() {
        ArrayList<ASTNode> children = new ArrayList<>();
        children.add(conditionalExpression);
        children.addAll(body);

        return children;
    }

    @Override
    public ASTNode addChild(ASTNode child) {
        if(child instanceof Expression)
            conditionalExpression  = (Expression) child;
        else
            body.add(child);

        return this;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        IfClause ifClause = (IfClause) o;
        return Objects.equals(conditionalExpression, ifClause.getConditionalExpression()) &&
                Objects.equals(body, ifClause.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conditionalExpression, body);
    }

    public Expression getConditionalExpression() {
        return conditionalExpression;
    }
}
