package ir.isar.isarapp.dao;

import ir.isar.isarapp.entity.GraduationStatus;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class GraduationStatusDao extends CodeValueDao<GraduationStatus> {

    @Override
    public Class getEntityClass() {
        return GraduationStatus.class;
    }

}
