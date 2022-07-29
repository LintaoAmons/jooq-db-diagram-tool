package top.oatnil;

import java.beans.IntrospectionException;
import java.util.stream.Collectors;
import org.jooq.Record;
import top.oatnil.core.JooqRecordClass;
import top.oatnil.core.TableColumnDefinitionWithSampleData;
import top.oatnil.core.TableColumns;

public class DbDiagramTool {
    private static final String TEMPLATE = "TABLE %s {\n %s }";

    // TODO 最终目标，用户输入 records 的包路径，扫描所有records进来，然后取出一条数据，生成table
    public String record_2_DiagramTable(Class<?> clazz, Record record) throws IntrospectionException {
        JooqRecordClass jooqRecordClass = JooqRecordClass.of(clazz);
        String tableName = jooqRecordClass.extractTableName();
        String columnsString = renderColumns(jooqRecordClass.extractColumns(), record);
        return String.format(TEMPLATE, tableName, columnsString);
    }

    private String renderColumns(TableColumns tableColumns, Record record) {
        return tableColumns.getColumnDefinitions().stream()
                .map(it -> renderColumn(new TableColumnDefinitionWithSampleData(it, null).injectData(record)))
                .collect(Collectors.joining());
    }

    private String renderColumn(TableColumnDefinitionWithSampleData column) {
        String template = "%s %s [note: \"sample data: %s\"]\n";
        return String.format(template,
                column.getTableColumnDefinition().getName(),
                column.getTableColumnDefinition().getDataType(),
                column.getSampleData());
    }

}
