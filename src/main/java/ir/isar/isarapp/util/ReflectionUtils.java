package ir.isar.isarapp.util;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author User
 */
@Singleton
public class ReflectionUtils {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void writeValueToModel(Serializable model, String fieldName, Object value) {
        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            for (Method method : model.getClass().getDeclaredMethods()) {
                if (method.getName().equals(methodName)) {
                    method.invoke(model, value);
                    return;
                }
            }
            throw new RuntimeException("Method not found: " + methodName);
        } catch (IllegalAccessException | InvocationTargetException | SecurityException ex) {
            logger.warn("Exception in " + methodName, ex);
        }
    }

    public Object readValueFromModel(Serializable model, String fieldName) {
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            for (Method method : model.getClass().getDeclaredMethods()) {
                if (method.getName().equals(methodName)) {
                    return method.invoke(model);
                }
            }
            throw new RuntimeException("Method not found: " + methodName);
        } catch (IllegalAccessException | InvocationTargetException | SecurityException ex) {
            logger.warn("Exception in " + methodName, ex);
            return null;
        }
    }
}
