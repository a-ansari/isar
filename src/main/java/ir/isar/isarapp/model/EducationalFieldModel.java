package ir.isar.isarapp.model;

import ir.isar.isarapp.dao.EducationalGroupDao;
import ir.isar.isarapp.entity.EducationalField;
import ir.isar.isarapp.entity.EducationalGroup;
import ir.isar.isarapp.util.AppContextUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author User
 */
public class EducationalFieldModel extends BaseModel<EducationalField> {

    private final StringProperty educationalGroup = new SimpleStringProperty();
    private final StringProperty educationalField = new SimpleStringProperty();

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
    public EducationalField createEntity() {
        EducationalField entity = new EducationalField();
        updateEntity(entity);
        return entity;
    }

    @Override
    public void updateEntity(EducationalField entity) {
//        entity.setId(getId());
        entity.setValue(getEducationalField());

        EducationalGroupDao educationalGroupDao = AppContextUtils.getBean(EducationalGroupDao.class);
        EducationalGroup group = educationalGroupDao.loadByValue(getEducationalGroup());
        entity.setEducationalGroup(group);
    }

    @Override
    public void fromEntity(EducationalField entity) {
        setId(entity.getId());
        setEducationalGroup(entity.getEducationalGroup().getValue());
        setEducationalField(entity.getValue());
    }

    @Override
    public boolean hasError() {
        return StringUtils.isAnyEmpty(getEducationalGroup(), getEducationalField());
    }

    @Override
    public void clear() {
        setId(null);
        setDirty(false);
        setEducationalGroup(null);
        setEducationalField(null);
    }
}
