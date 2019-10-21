package nl.han.ica.icss.ast;

import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.ArrayList;

public abstract class Operation extends Expression {

    public Expression lhs;
    public Expression rhs;

    @Override
    public ArrayList<ASTNode> getChildren() {
        ArrayList<ASTNode> children = new ArrayList<>();
        if (lhs != null)
            children.add(lhs);
        if (rhs != null)
            children.add(rhs);
        return children;
    }

    @Override
    public ASTNode addChild(ASTNode child) {
        if (lhs == null) {
            lhs = (Expression) child;
        } else if (rhs == null) {
            rhs = (Expression) child;
        }
        return this;
    }

    @Override
    public ASTNode removeChild(ASTNode child) {
        if (lhs.equals(child)) {
            lhs = null;
        } else if (rhs.equals(child)) {
            rhs = null;
        }
        return this;
    }

    protected abstract Literal evalOperation(int lhs, int rhs);

    @Override
    public Literal eval() {
        return evalOperation(Integer.parseInt(lhs.eval().getValue()), Integer.parseInt(rhs.eval().getValue()));
    }

    @Override
    public ExpressionType getType() {
        ExpressionType lhsType = lhs.getType();
        if (lhsType != null && lhsType != ExpressionType.SCALAR && lhsType != ExpressionType.UNDEFINED) {
            return lhsType;
        }
        return rhs.getType();
    }
}
