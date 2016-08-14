package ir.isar.isarapp.util;

import ir.isar.isarapp.entity.Base;
import java.io.Serializable;
import java.lang.reflect.Field;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author User
 */
@Singleton
public class BeanCopierUtils {

    @Inject
    private ConversionUtils conversionUtils;
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    public void copy(Base source, Serializable dest) {
        for (Field srcField : source.getClass().getDeclaredFields()) {
            try {
                Field dstField = dest.getClass().getDeclaredField(srcField.getName());
                
                srcField.setAccessible(true);
                Object srcValue = srcField.get(source);
                srcField.setAccessible(false);
                
                dstField.setAccessible(true);
                dstField.set(dest, conversionUtils.toString(srcValue));
                dstField.setAccessible(false);
            } catch (NoSuchFieldException ex) {
                logger.warn("NoSuchFieldException in field: {}", srcField.getName());
            } catch (SecurityException | IllegalAccessException ex) {
                logger.warn("Exception in field: " + srcField.getName(), ex);
            } catch (IllegalArgumentException ex) {
                logger.warn("IllegalArgumentException: " + ex.getMessage());
            }
        }
    }
    
}
