package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Stylerule;

import java.util.ArrayList;

public class NodeTransformer {

    public static void replaceIfWithBody(Stylerule parent, ASTNode child, ArrayList<ASTNode> replacements) {
        parent.removeChild(child);
        replacements.forEach(parent::addChild);
    }

    public static void replaceChild(ASTNode parent, ASTNode child, ASTNode replacement) {
        parent.removeChild(child);
        parent.addChild(replacement);
    }

    public static void removeChildFromParent(ASTNode parent, ASTNode child) {
        parent.removeChild(child);
    }
}
