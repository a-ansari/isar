package ir.isar.isarapp.dao;

import ir.isar.isarapp.entity.UnitSummary;

/**
 *
 * @author User
 */
public class UnitSummaryDao extends BaseDao<UnitSummary> {

    @Override
    public Class<UnitSummary> getEntityClass() {
        return UnitSummary.class;
    }

}
