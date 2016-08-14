package ir.isar.isarapp.model;

import ir.isar.isarapp.dao.EducationalFieldDao;
import ir.isar.isarapp.entity.EducationalField;
import ir.isar.isarapp.entity.EducationalTrend;
import ir.isar.isarapp.util.AppContextUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author User
 */
public class EducationalTrendModel extends BaseModel<EducationalTrend> {

    private final StringProperty educationalGroup = new SimpleStringProperty();
    private final StringProperty educationalField = new SimpleStringProperty();
    private final StringProperty educationalTrend = new SimpleStringProperty();

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

    public String getEducationalTrend() {
        return educationalTrend.get();
    }

    public void setEducationalTrend(String value) {
        educationalTrend.set(value);
    }

    public StringProperty educationalTrendProperty() {
        return educationalTrend;
    }

    @Override
    public EducationalTrend createEntity() {
        EducationalTrend entity = new EducationalTrend();
        updateEntity(entity);
        return entity;
    }

    @Override
    public void updateEntity(EducationalTrend entity) {
//        entity.setId(getId());
        entity.setValue(getEducationalTrend());

        EducationalFieldDao educationalFieldDao = AppContextUtils.getBean(EducationalFieldDao.class);
        EducationalField field = educationalFieldDao.load(getEducationalField(), getEducationalGroup());
        entity.setEducationalField(field);
    }

    @Override
    public void fromEntity(EducationalTrend entity) {
        setId(entity.getId());
        setEducationalGroup(entity.getEducationalField().getEducationalGroup().getValue());
        setEducationalField(entity.getEducationalField().getValue());
        setEducationalTrend(entity.getValue());
    }

    @Override
    public boolean hasError() {
        return StringUtils.isAnyEmpty(getEducationalGroup(), getEducationalField(), getEducationalTrend());
    }

    @Override
    public void clear() {
        setId(null);
        setDirty(false);
        setEducationalGroup(null);
        setEducationalField(null);
        setEducationalTrend(null);
    }
}
