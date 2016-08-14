package ir.isar.isarapp.dao;

import com.avaje.ebean.Ebean;
import ir.isar.isarapp.entity.EducationalField;
import ir.isar.isarapp.entity.EducationalTrend;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class EducationalTrendDao extends BaseDao<EducationalTrend> {

    @Inject
    private EducationalFieldDao educationalFieldDao;

    @Override
    public Class<EducationalTrend> getEntityClass() {
        return EducationalTrend.class;
    }

    public List<EducationalTrend> loadAll(String field, String group) {
        EducationalField fieldEntity = educationalFieldDao.load(field, group);
        return Ebean.find(getEntityClass()).where().eq("educationalField", fieldEntity).findList();
    }

    public List<String> loadAllValues(String field, String group) {
        List<String> result = new ArrayList<>();
        for (EducationalTrend trend : loadAll(field, group)) {
            result.add(trend.getValue());
        }
        return result;
    }

    public EducationalTrend load(String trend, EducationalField fieldEntity) {
        return Ebean.find(getEntityClass()).where().eq("educationalField", fieldEntity).eq("value", trend).findUnique();
    }

    public EducationalTrend load(String trend, String field, String group) {
        EducationalField fieldEntity = educationalFieldDao.load(field, group);
        return load(trend, fieldEntity);
    }

    public EducationalTrend loadOrAdd(String trend, EducationalField fieldEntity) {
        EducationalTrend existed = load(trend, fieldEntity);
        if (existed == null) {
            existed = new EducationalTrend();
            existed.setValue(trend);
            existed.setEducationalField(fieldEntity);
            Ebean.save(existed);
        }
        return existed;
    }
}
