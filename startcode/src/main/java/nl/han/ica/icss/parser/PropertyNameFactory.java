package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.PropertyName;

public class PropertyNameFactory {
    public static PropertyName make(ICSSParser.Property_nameContext property_nameContext) {
        return new PropertyName(property_nameContext.getText());
    }
}
