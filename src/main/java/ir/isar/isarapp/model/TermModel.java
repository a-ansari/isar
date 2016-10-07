package ir.isar.isarapp.model;

import ir.isar.isarapp.dao.TermStatusDao;
import ir.isar.isarapp.entity.Term;
import ir.isar.isarapp.util.AppContextUtils;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author User
 */
public class TermModel extends BaseModel<Term> {
    private final SimpleIntegerProperty termNumber;

    private final SimpleIntegerProperty takenUnits;
    
    private final SimpleIntegerProperty passedUnits;
    
    private final SimpleIntegerProperty deletedUnits;
    
    private final SimpleIntegerProperty failedUnits;
    
    private final SimpleIntegerProperty unspecifiedUnits;
    
    private final SimpleIntegerProperty zeroUnits;
    
    private final SimpleDoubleProperty termAverage;
    
    private final SimpleDoubleProperty totalAverage;
    
    private final SimpleStringProperty termStatus;

    public TermModel() {
        this.termNumber = new SimpleIntegerProperty();
        this.takenUnits = new SimpleIntegerProperty();
        this.passedUnits = new SimpleIntegerProperty();
        this.deletedUnits = new SimpleIntegerProperty();
        this.failedUnits = new SimpleIntegerProperty();
        this.unspecifiedUnits = new SimpleIntegerProperty();
        this.zeroUnits = new SimpleIntegerProperty();
        this.termAverage = new SimpleDoubleProperty();
        this.totalAverage = new SimpleDoubleProperty();
        this.termStatus = new SimpleStringProperty();
        this.id = null;
    }

    @Override
    public Term createEntity() {
        Term entity = new Term();
        updateEntity(entity);
        return entity;
    }

    @Override
    public void updateEntity(Term entity) {
//        entity.setId(getId());
        entity.setTermNumber(getTermNumber());
        entity.setTakenUnits(getTakenUnits());
        entity.setPassedUnits(getPassedUnits());
        entity.setDeletedUnits(getDeletedUnits());
        entity.setFailedUnits(getFailedUnits());
        entity.setUnspecifiedUnits(getUnspecifiedUnits());
        entity.setZeroUnits(getZeroUnits());
        entity.setTermAverage(getTermAverage());
        entity.setTotalAverage(getTotalAverage());
        
        TermStatusDao termStatusDao = AppContextUtils.getBean(TermStatusDao.class);
        entity.setTermStatus(termStatusDao.loadByValue(getTermStatus()));
    }

    @Override
    public void fromEntity(Term entity) {
        setId(entity.getId());
        setTermNumber(entity.getTermNumber());
        setTakenUnits(entity.getTakenUnits());
        setPassedUnits(entity.getPassedUnits());
        setDeletedUnits(entity.getDeletedUnits());
        setFailedUnits(entity.getFailedUnits());
        setUnspecifiedUnits(entity.getUnspecifiedUnits());
        setZeroUnits(entity.getZeroUnits());
        setTermAverage(entity.getTermAverage());
        setTotalAverage(entity.getTotalAverage());
        setTermStatus(entity.getTermStatus().getValue());
    }

    @Override
    public boolean hasError() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getTermNumber() {
        return termNumber.get();
    }

    public void setTermNumber(Integer value) {
        this.termNumber.set(value);
    }

    public Integer getTakenUnits() {
        return takenUnits.get();
    }

    public void setTakenUnits(Integer value) {
        this.takenUnits.set(value);
    }

    public Integer getPassedUnits() {
        return passedUnits.get();
    }

    public void setPassedUnits(Integer value) {
        this.passedUnits.set(value);
    }

    public Integer getDeletedUnits() {
        return deletedUnits.get();
    }

    public void setDeletedUnits(Integer value) {
        this.deletedUnits.set(value);
    }

    public Integer getFailedUnits() {
        return failedUnits.get();
    }

    public void setFailedUnits(Integer value) {
        this.failedUnits.set(value);
    }

    public Integer getUnspecifiedUnits() {
        return unspecifiedUnits.get();
    }

    public void setUnspecifiedUnits(Integer value) {
        this.unspecifiedUnits.set(value);
    }

    public Integer getZeroUnits() {
        return zeroUnits.get();
    }

    public void setZeroUnits(Integer value) {
        this.zeroUnits.set(value);
    }

    public Double getTermAverage() {
        return termAverage.get();
    }

    public void setTermAverage(Double value) {
        this.termAverage.set(value);
    }

    public Double getTotalAverage() {
        return totalAverage.get();
    }

    public void setTotalAverage(Double value) {
        this.totalAverage.set(value);
    }

    public String getTermStatus() {
        return termStatus.get();
    }

    public void setTermStatus(String value) {
        this.termStatus.set(value);
    }
    
}
