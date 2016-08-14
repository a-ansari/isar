package ir.isar.isarapp.dao;

import com.avaje.ebean.Ebean;
import ir.isar.isarapp.entity.Base;
import java.util.List;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author User
 * @param <T>
 */
@Singleton
public abstract class BaseDao<T extends Base> {

    protected final Logger logger;

    public BaseDao() {
        logger = LoggerFactory.getLogger(getClass());
    }

    public abstract Class<T> getEntityClass();

    public List<T> loadAll() {
        return Ebean.find(getEntityClass()).orderBy("id").findList();
    }

    public T loadById(Long id) {
        return Ebean.find(getEntityClass(), id);
    }

    public void save(T entity) {
        Ebean.save(entity);
    }

    public void update(T entity) {
        Ebean.update(entity);
    }

    public void delete(T entity) {
        Ebean.delete(entity);
    }

    public void deleteById(Long id) {
        Ebean.delete(getEntityClass(), id);
    }
}
