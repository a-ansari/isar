package ir.isar.isarapp.biz;

import ir.isar.isarapp.filter.BaseFilter;
import ir.isar.isarapp.filter.ComboFilter;
import ir.isar.isarapp.filter.EnumFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author User
 */
@Singleton
public class FilterBiz {

    private List<BaseFilter> filters;
    private final ResourceBundle messages = ResourceBundle.getBundle(getClass().getName());
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<BaseFilter> listAllFilters() {
        if (filters == null) {
            filters = new ArrayList<>();
            String prefix = "isar.FilterBiz.filter";
            int filterCount = Integer.parseInt(messages.getString(prefix + "Count"));

            for (int i = 0; i < filterCount; i++) {
                String type = messages.getString(prefix + i + ".type");
                String title = messages.getString(prefix + i + ".title");
                String field = messages.getString(prefix + i + ".field");
                String termBased = null;
                try {
                    termBased = messages.getString(prefix + i + ".termBased");
                } catch (MissingResourceException ex) {
                }

                BaseFilter filter;
                try {
                    filter = (BaseFilter) Class.forName("ir.isar.isarapp.filter." + type).getConstructor(String.class, String.class).newInstance(title, field);
                    if (filter instanceof ComboFilter) {
                        String className = messages.getString(prefix + i + ".class");
                        Class clazz = Class.forName("ir.isar.isarapp.entity." + className);
                        ((ComboFilter) filter).setEntityClass(clazz);
                    }
                    if (filter instanceof EnumFilter) {
                        String enumClass = messages.getString(prefix + i + ".class");
                        ((EnumFilter) filter).setEnumClass(enumClass);
                    }
                    if (termBased != null) {
                        filter.setTermBased(true);
                    }
                } catch (Exception ex) {
                    logger.error("exception:" + type, ex);
                    continue;
                }
                filters.add(filter);
            }
        }
        return filters;
    }
}
