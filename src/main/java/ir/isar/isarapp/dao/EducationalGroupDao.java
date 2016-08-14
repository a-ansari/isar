package ir.isar.isarapp.dao;

import ir.isar.isarapp.entity.EducationalGroup;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class EducationalGroupDao extends CodeValueDao<EducationalGroup> {

    @Override
    public Class getEntityClass() {
        return EducationalGroup.class;
    }

}
