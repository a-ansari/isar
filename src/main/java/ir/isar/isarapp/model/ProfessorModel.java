package ir.isar.isarapp.model;

import ir.isar.isarapp.dao.EducationalFieldDao;
import ir.isar.isarapp.entity.EducationalField;
import ir.isar.isarapp.entity.Professor;
import ir.isar.isarapp.util.AppContextUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author User
 */
public class ProfessorModel extends BaseModel<Professor> {

    private final StringProperty professorName = new SimpleStringProperty();
    private final StringProperty educationalGroup = new SimpleStringProperty();
    private final StringProperty educationalField = new SimpleStringProperty();

    public String getProfessorName() {
        return professorName.get();
    }

    public void setProfessorName(String value) {
        professorName.set(value);
    }

    public StringProperty professorNameProperty() {
        return professorName;
    }

    public String getEducationalGroup() {
        return educationalGroup.get();
    }

    public void setEducationalGroup(String value) {
        educationalGroup.set(value);
    }

    public StringProperty educationalGroupProperty() {
        return educationalGroup;
    }

    public String getEducationalField() {
        return educationalField.get();
    }

    public void setEducationalField(String value) {
        educationalField.set(value);
    }

    public StringProperty educationalFieldProperty() {
        return educationalField;
    }

    @Override
    public Professor createEntity() {
        Professor entity = new Professor();
        updateEntity(entity);
        return entity;
    }

    @Override
    public void updateEntity(Professor entity) {
//        entity.setId(getId());
        entity.setValue(getProfessorName());

        EducationalFieldDao educationalFieldDao = AppContextUtils.getBean(EducationalFieldDao.class);
        EducationalField field = educationalFieldDao.load(getEducationalField(), getEducationalGroup());
        entity.setEducationalField(field);
    }

    @Override
    public void fromEntity(Professor entity) {
        setId(entity.getId());
        setProfessorName(entity.getValue());
        setEducationalGroup(entity.getEducationalField().getEducationalGroup().getValue());
        setEducationalField(entity.getEducationalField().getValue());
    }

    @Override
    public boolean hasError() {
        return StringUtils.isAnyEmpty(getProfessorName(), getEducationalGroup(), getEducationalField());
    }

    @Override
    public void clear() {
        setId(null);
        setDirty(false);
        setProfessorName(null);
        setEducationalGroup(null);
        setEducationalField(null);
    }
}
