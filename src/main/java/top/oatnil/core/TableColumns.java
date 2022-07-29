package top.oatnil.core;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Value;

@Value
public class TableColumns {
    List<TableColumnDefinition> columnDefinitions;

    public static TableColumns from(Class<?> clazz) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        List<String> excludeProperties = Arrays.asList("class", "value", "table");
        return new TableColumns(
                Arrays.stream(beanInfo.getPropertyDescriptors())
                        .map(TableColumnDefinition::from)
                        .filter(it -> !excludeProperties.contains(it.getName()))
                        .collect(Collectors.toList())
        );
    }
}
