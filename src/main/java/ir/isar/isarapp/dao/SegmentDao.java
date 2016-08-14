package ir.isar.isarapp.dao;

import ir.isar.isarapp.entity.Segment;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class SegmentDao extends CodeValueDao<Segment> {

    @Override
    public Class getEntityClass() {
        return Segment.class;
    }

}
