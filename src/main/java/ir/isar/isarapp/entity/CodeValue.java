package ir.isar.isarapp.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author User
 */
@MappedSuperclass
public abstract class CodeValue extends Base {

    @Column
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
