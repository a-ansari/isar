package ir.isar.isarapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author User
 */
@Entity
public class Config extends Base {
    @Column
    private String key;
    
    @Column
    private String value;

    public static final String UNIVERSITY_NAME = "university.name";
    public static final String UNIVERSITY_TYPE = "university.type";
    public static final String UNIVERSITY_TYPE_PUBLIC = "university.type.public";
    public static final String UNIVERSITY_TYPE_PRIVATE = "university.type.private";
            
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
