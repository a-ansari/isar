package ir.isar.isarapp.util;

import com.avaje.ebean.Ebean;
import ir.isar.isarapp.entity.CodeValue;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ResourceBundle;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class CodeValueUtils {

    public void initStaticValues(Class<? extends CodeValue> clazz) {
        ResourceBundle messages = ResourceBundle.getBundle(clazz.getName());
        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) && field.getType().isAssignableFrom(clazz)) {
                String value = messages.getString("isar." + clazz.getSimpleName() + "." + field.getName());
                if (value == null) {
                    throw new IllegalArgumentException("value not found: " + field.getName());
                }
                CodeValue entity = Ebean.find(clazz).where().eq("value", value).findUnique();
                if (entity == null) {
                    throw new IllegalArgumentException("entity not found: " + value);
                }

                try {
                    field.set(null, entity);
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    throw new IllegalArgumentException("field not accessible: " + value);
                }
            }
        }
    }
}
