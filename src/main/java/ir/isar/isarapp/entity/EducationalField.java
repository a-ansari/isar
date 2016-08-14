package ir.isar.isarapp.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author User
 */
@Entity
public class EducationalField extends CodeValue {

    @ManyToOne
    private EducationalGroup educationalGroup;

    public EducationalGroup getEducationalGroup() {
        return educationalGroup;
    }

    public void setEducationalGroup(EducationalGroup educationalGroup) {
        this.educationalGroup = educationalGroup;
    }
}
