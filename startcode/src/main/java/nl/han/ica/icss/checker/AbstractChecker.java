package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.ASTNode;

import java.util.List;

public abstract class AbstractChecker implements IChecker{

    @Override
    public void check(List<ASTNode> nodes) {
        for(ASTNode astNode : nodes) {
            check(astNode);
        }
    }

    @Override
    public void checkChildren(ASTNode node) {
        if(node.getChildren().size() != 0)
            check(node.getChildren());
    }
}
