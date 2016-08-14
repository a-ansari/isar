package ir.isar.isarapp.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author User
 */
@Entity
public class EducationalTrend extends CodeValue {

    @ManyToOne
    private EducationalField educationalField;

    public EducationalField getEducationalField() {
        return educationalField;
    }

    public void setEducationalField(EducationalField educationalField) {
        this.educationalField = educationalField;
    }

}
