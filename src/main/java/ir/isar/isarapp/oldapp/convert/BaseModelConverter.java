package ir.isar.isarapp.oldapp.convert;

import ir.isar.isarapp.entity.Base;
import java.io.Serializable;

/**
 *
 * @author User
 */
public interface BaseModelConverter<M extends Serializable, E extends Base> {
    public M createModel();
    public E createEntity(M model);
    public void convertModelToEntity(M model, E entity);
    public void saveEntity(E entity);
}
