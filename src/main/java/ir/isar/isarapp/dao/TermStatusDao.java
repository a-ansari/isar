package ir.isar.isarapp.dao;

import ir.isar.isarapp.entity.TermStatus;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class TermStatusDao extends CodeValueDao<TermStatus> {

    @Override
    public Class getEntityClass() {
        return TermStatus.class;
    }

}
