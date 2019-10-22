package nl.han.ica.icss;

import nl.han.ica.icss.ast.ASTNode;

@FunctionalInterface
public interface IWalkAction {
    void step(ASTNode node);
}
