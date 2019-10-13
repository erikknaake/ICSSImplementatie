package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Declaration;
import nl.han.ica.icss.ast.Expression;
import nl.han.ica.icss.ast.PropertyName;
import nl.han.ica.icss.ast.types.ExpressionType;
import nl.han.ica.icss.typesystem.TypeResolver;

import java.util.HashMap;
import java.util.Map;

public class DeclarationTypeChecker implements IChecker {
    public static final ExpressionType[] SIZE_TYPES = {
            ExpressionType.PIXEL,
            ExpressionType.PERCENTAGE
    };
    public static final ExpressionType[] COLOR_TYPE = {
            ExpressionType.COLOR
    };

    private static Map<String, ExpressionType[]> allowedDeclarationTypes;

    public DeclarationTypeChecker() {
        if (allowedDeclarationTypes == null)
            initializeAllowedDeclarationTypes();
    }

    private void initializeAllowedDeclarationTypes() {
        allowedDeclarationTypes = new HashMap<>();
        allowedDeclarationTypes.put("color", COLOR_TYPE);
        allowedDeclarationTypes.put("background-color", COLOR_TYPE);
        allowedDeclarationTypes.put("width", SIZE_TYPES);
        allowedDeclarationTypes.put("height", SIZE_TYPES);
    }

    @Override
    public void check(ASTNode node) {
        if (node instanceof Declaration) {
            Declaration declaration = (Declaration) node;
            if (!isDeclarationTypeAllowed(declaration.property, declaration.expression)) {
                declaration.expression.setError("Expression type not allowed here, check the style attribute");
            }
        }
    }

    private boolean isDeclarationTypeAllowed(PropertyName propertyName, Expression expression) {
        return isDeclarationTypeAllowed(propertyName.name, TypeResolver.resolve(expression));
    }

    private boolean isDeclarationTypeAllowed(String declarationAttribute, ExpressionType expressionType) {
        ExpressionType[] expressionTypes = allowedDeclarationTypes.get(declarationAttribute);
        if (expressionTypes != null) {
            for (ExpressionType allowedType : expressionTypes) {
                if (allowedType == expressionType)
                    return true;
            }
        }
        return false;
    }
}
