package nl.han.ica.icss.ast;

import java.util.ArrayList;

public class IfStatement extends ASTNode {
    public IfClause ifClause;
    public ElseClause elseClause;

    public IfStatement() {
    }

    public IfStatement(IfClause ifClause, ElseClause elseClause) {
        this.ifClause = ifClause;
        this.elseClause = elseClause;
    }

    @Override
    public String getNodeLabel() {
        return "If_Statement";
    }

    @Override
    public ArrayList<ASTNode> getChildren() {
        ArrayList<ASTNode> children = new ArrayList<>();
        if (ifClause != null)
            children.add(ifClause);
        if (elseClause != null)
            children.add(elseClause);

        return children;
    }

    @Override
    public ASTNode addChild(ASTNode child) {
        if (child instanceof IfClause)
            ifClause = (IfClause) child;
        else if (child instanceof ElseClause)
            elseClause = (ElseClause) child;

        return this;
    }
}
