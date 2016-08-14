package ir.isar.isarapp.dao;

import com.avaje.ebean.Ebean;
import ir.isar.isarapp.entity.CodeValue;
import ir.isar.isarapp.util.ConversionUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Singleton;

/**
 *
 * @author User
 * @param <P>
 */
@Singleton
public abstract class CodeValueDao<P extends CodeValue> extends BaseDao<CodeValue> {

    private List<String> valueCache;
    private List<CodeValue> entityCache;
    private Map<String, CodeValue> mapCache;

    public void invalidateCache() {
        valueCache = null;
        entityCache = null;
        mapCache = null;
    }

    @Override
    public List<CodeValue> loadAll() {
        if (entityCache == null) {
            entityCache = super.loadAll();
        }
        return entityCache;
    }

    public List<String> loadAllValues() {
        if (valueCache == null) {
            valueCache = new ArrayList<>();
            for (CodeValue entity : loadAll()) {
                valueCache.add(entity.getValue());
            }
        }
        return valueCache;
    }

    public P loadByValue(String value) {
        if (value == null || value.isEmpty() || value.equals(ConversionUtils.NULL_STRING)) {
            return null;
        }
        if (mapCache == null) {
            mapCache = new HashMap<>();
            for (CodeValue cv : loadAll()) {
                mapCache.put(cv.getValue(), cv);
            }
        }
        return (P) mapCache.get(value);
    }

    public P loadOrAdd(String value) {
        if (value == null || value.isEmpty() || value.equals(ConversionUtils.NULL_STRING)) {
            return null;
        }
        P found = loadByValue(value);
        if (found != null) {
            return found;
        }
        return addValue(value);
    }

    public P addValue(String value) {
        P entity = addToDb(value);
        addToValueCache(value);
        addToEntityCache(entity);
        addToMapCache(value, entity);
        return entity;
    }

    protected P addToDb(String value) {
        try {
            P newInstance = (P) getEntityClass().getConstructor().newInstance();
            newInstance.setValue(value);
            Ebean.save(newInstance);
            return newInstance;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    protected void addToValueCache(String value) {
        if (valueCache == null) {
            valueCache = new ArrayList<>();
        }
        valueCache.add(value);
    }

    protected void addToEntityCache(P entity) {
        if (entityCache == null) {
            entityCache = new ArrayList<>();
        }
        entityCache.add(entity);
    }
    
    protected void addToMapCache(String value, P entity) {
        if (mapCache == null) {
            mapCache = new HashMap<>();
        }
        mapCache.put(value, entity);
    }
}
