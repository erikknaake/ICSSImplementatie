package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;

import java.util.List;

public interface IChecker {
    void check(AST ast);
    void check(ASTNode node);
    void check(List<ASTNode> nodes);
    void checkChildren(ASTNode node);
}
