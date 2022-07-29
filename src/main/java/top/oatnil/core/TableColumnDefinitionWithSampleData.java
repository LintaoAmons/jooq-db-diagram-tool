package top.oatnil.core;

import java.lang.reflect.InvocationTargetException;
import lombok.Value;
import lombok.With;

@Value
@With
public class TableColumnDefinitionWithSampleData {
    TableColumnDefinition tableColumnDefinition;
    String sampleData;

    public TableColumnDefinitionWithSampleData injectData(Object dataSource) {
        String sampleData = extractSampleDataFrom(dataSource);
        return this.withSampleData(sampleData);
    }

    private String extractSampleDataFrom(Object dataSource) {
        if (dataSource == null) {
            return "[Error] Can't found sample data";
        }

        try {
            return String.valueOf(this.getTableColumnDefinition().getDataGetter().invoke(dataSource));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "[Error] Can't found sample data";
        }
    }
}
