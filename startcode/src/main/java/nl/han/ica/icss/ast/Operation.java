package nl.han.ica.icss.ast;

import java.util.ArrayList;

public abstract class Operation extends Expression {

    public Expression lhs;
    public Expression rhs;

    @Override
    public ArrayList<ASTNode> getChildren() {
        ArrayList<ASTNode> children = new ArrayList<>();
        if(lhs != null)
            children.add(lhs);
        if(rhs != null)
            children.add(rhs);
        return children;
    }

    @Override
    public ASTNode addChild(ASTNode child) {
        if(lhs == null) {
            lhs = (Expression) child;
        } else if(rhs == null) {
            rhs = (Expression) child;
        }
        return this;
    }

    @Override
    public ASTNode removeChild(ASTNode child) {
        if(lhs.equals(child)) {
            lhs = null;
        } else if(rhs.equals(child)) {
            rhs = null;
        }
        return this;
    }
}
