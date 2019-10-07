package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;

public class ChildReplacer {
    public static <T extends ASTNode> void replaceChild (ASTNode parent, T child, T replacement) {
        if(parent instanceof VariableAssignment) {
            replaceVariableAssignmentChild((VariableAssignment) parent, child, replacement);
        } else if(parent instanceof Declaration) {
            replaceDeclarationChild((Declaration) parent, child, replacement);
        }
    }

    private static <T extends ASTNode> void replaceVariableAssignmentChild(VariableAssignment parent, T child, T replacement) {
        if(parent.expression.equals(child)) {
            parent.expression = (Expression) replacement;
        } else if(parent.name.equals(child)) {
            parent.name = (VariableReference) replacement;
        }
    }

    private static <T extends ASTNode> void replaceDeclarationChild(Declaration parent, T child, T replacement) {
        if(parent.expression.equals(child)) {
            parent.expression = (Expression) replacement;
        } else if(parent.property.equals(child)) {
            parent.property = (PropertyName) replacement;
        }
    }
}
