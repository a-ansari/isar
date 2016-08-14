package ir.isar.isarapp.util;

import ir.isar.isarapp.model.ColumnOrder;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Singleton;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author User
 */
@Singleton
public class TabSeparatedParser {
    private final Map<Class, Field[]> fieldsCache;
    
    public TabSeparatedParser(){
        fieldsCache = new HashMap<>();
    }

    public CSVParser createParser(String fileName) throws IOException {
        FileReader reader = new FileReader(fileName);
        return CSVFormat.TDF.parse(reader);
    }

    public void parse(CSVRecord record, Serializable model) throws IllegalArgumentException, IllegalAccessException {
        int i = 0;
        Field[] fields = getOrderedFields(model.getClass());
        for (String token : record) {
            fields[i].setAccessible(true);
            fields[i].set(model, token);
            fields[i].setAccessible(false);
            i++;
        }
    }

    protected Field[] getOrderedFields(Class clazz) {
        Field[] orderedFields = fieldsCache.get(clazz);
        if (orderedFields != null) {
            return orderedFields;
        }

        Field[] allFields = clazz.getDeclaredFields();
        orderedFields = new Field[allFields.length];
        for (Field field : allFields) {
            ColumnOrder[] annotations = field.getAnnotationsByType(ColumnOrder.class);
            if (annotations == null || annotations.length == 0) {
                continue;
            }
            orderedFields[annotations[0].order()] = field;
        }
        
        fieldsCache.put(clazz, orderedFields);
        return orderedFields;
    }

}
