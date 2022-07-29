package top.oatnil.core;

import java.beans.IntrospectionException;
import lombok.Value;

@Value
public class JooqRecordClass {
    Class<?> clazz;

    public static JooqRecordClass of(Class<?> clazz) {
        return new JooqRecordClass(clazz);
    }

    public String extractTableName() {
        String simpleName = clazz.getSimpleName();
        return simpleName.substring(0, simpleName.length() - "Record".length());
    }

    public TableColumns extractColumns() throws IntrospectionException {
        return TableColumns.from(clazz);
    }

}
