package nl.han.ica.icss.ast;

import java.util.ArrayList;

public class ElseClause extends ASTNode {
    public ArrayList<ASTNode> body = new ArrayList<>();

    public ElseClause() {
    }

    public ElseClause(ArrayList<ASTNode> body) {
        this.body = body;
    }

    @Override
    public String getNodeLabel() {
        return "Else_Clause";
    }

    @Override
    public ArrayList<ASTNode> getChildren() {
        ArrayList<ASTNode> children = new ArrayList<>();
        children.addAll(body);

        return children;
    }

    @Override
    public ASTNode addChild(ASTNode child) {
        body.add(child);

        return this;
    }
}
