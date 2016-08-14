package ir.isar.isarapp.dao;

import ir.isar.isarapp.entity.EducationalType;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class EducationalTypeDao extends CodeValueDao<EducationalType> {

    @Override
    public Class getEntityClass() {
        return EducationalType.class;
    }

}
