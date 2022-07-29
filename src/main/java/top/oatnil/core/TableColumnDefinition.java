package top.oatnil.core;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import lombok.Value;

@Value
public class TableColumnDefinition {
    String name;
    String dataType;
    Method dataGetter;

    public static TableColumnDefinition from(PropertyDescriptor propertyDescriptor) {
        Method getter = propertyDescriptor.getReadMethod();
        String columnName = propertyDescriptor.getName();
        Class<?> propertyType = propertyDescriptor.getPropertyType();
        if (propertyType == null) {
            return new TableColumnDefinition(columnName, null, getter);
        }
        String simpleName = propertyType.getSimpleName();
        return new TableColumnDefinition(columnName, simpleName, getter);
    }
}
