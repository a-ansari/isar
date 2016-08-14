package ir.isar.isarapp.dao;

import com.avaje.ebean.Ebean;
import ir.isar.isarapp.entity.Config;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class ConfigDao {

    public String getValue(String key) {
        return Ebean.find(Config.class).where().eq("key", key).findUnique().getValue();
    }
}
