package ir.isar.isarapp.model;

import java.io.Serializable;

/**
 *
 * @author User
 */
public abstract class BaseModel<T> implements Serializable {

    protected Long id;
    protected boolean dirty = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public abstract T createEntity();

    public abstract void updateEntity(T entity);

    public abstract void fromEntity(T entity);

    public abstract boolean hasError();

    public abstract void clear();
}
