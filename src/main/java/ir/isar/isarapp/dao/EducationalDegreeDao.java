package ir.isar.isarapp.dao;

import ir.isar.isarapp.entity.EducationalDegree;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class EducationalDegreeDao extends CodeValueDao<EducationalDegree> {

    @Override
    public Class getEntityClass() {
        return EducationalDegree.class;
    }

}
