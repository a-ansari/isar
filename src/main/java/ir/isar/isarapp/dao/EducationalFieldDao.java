package ir.isar.isarapp.dao;

import com.avaje.ebean.Ebean;
import ir.isar.isarapp.entity.EducationalField;
import ir.isar.isarapp.entity.EducationalGroup;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class EducationalFieldDao extends BaseDao<EducationalField> {

    @Inject
    private EducationalGroupDao educationalGroupDao;

    @Override
    public Class<EducationalField> getEntityClass() {
        return EducationalField.class;
    }

    public List<EducationalField> loadAll(String group) {
        EducationalGroup groupEntity = educationalGroupDao.loadByValue(group);
        return Ebean.find(getEntityClass()).where().eq("educationalGroup", groupEntity).findList();
    }

    public List<String> loadAllValues(String group) {
        List<String> result = new ArrayList<>();
        for (EducationalField field: loadAll(group)){
            result.add(field.getValue());
        }
        return result;
    }

    public EducationalField load(String field, String group) {
        EducationalGroup groupEntity = educationalGroupDao.loadByValue(group);
        return load(field, groupEntity);
    }

    public EducationalField load(String field, EducationalGroup groupEntity) {
        return Ebean.find(getEntityClass()).where().eq("educationalGroup", groupEntity).eq("value", field).findUnique();
    }

    public EducationalField loadOrAdd(String field, EducationalGroup groupEntity) {
        EducationalField existed = load(field, groupEntity);
        if (existed == null) {
            existed = new EducationalField();
            existed.setValue(field);
            existed.setEducationalGroup(groupEntity);
            Ebean.save(existed);
        }
        return existed;
    }
}
