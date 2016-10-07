package ir.isar.isarapp.model;

//import ir.isar.isarapp.dao.TermStatusDao;
//import ir.isar.isarapp.entity.Term;
//import ir.isar.isarapp.util.AppContextUtils;
//import javafx.beans.property.SimpleDoubleProperty;
//import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author User
 */
public class TermModelCreate {
    public static  SimpleStringProperty termNumber = new SimpleStringProperty("0");

    public static SimpleStringProperty takenUnits = new SimpleStringProperty("0");
    
    public static SimpleStringProperty passedUnits = new SimpleStringProperty("0");
    
    public static SimpleStringProperty deletedUnits = new SimpleStringProperty("0");
    
    public static SimpleStringProperty failedUnits = new SimpleStringProperty("0");
    
    public static SimpleStringProperty unspecifiedUnits = new SimpleStringProperty("0");
    
    public static SimpleStringProperty zeroUnits = new SimpleStringProperty("0");
    
    public static SimpleStringProperty termAverage = new SimpleStringProperty("0.0");
    
  //  public static SimpleStringProperty totalAverage = new SimpleStringProperty("0.0");
    
    public static SimpleStringProperty termStatus = new SimpleStringProperty("نامشخص");
    
    private static boolean updateAlert = true;
    
//    public TermModelCreate() {
//        this.termNumber = new SimpleStringProperty();
//        this.takenUnits = new SimpleStringProperty();
//        this.passedUnits = new SimpleStringProperty();
//        this.deletedUnits = new SimpleStringProperty();
//        this.failedUnits = new SimpleStringProperty();
//        this.unspecifiedUnits = new SimpleStringProperty();
//        this.zeroUnits = new SimpleStringProperty();
//        this.termAverage = new SimpleStringProperty();
//        this.totalAverage = new SimpleStringProperty();
//        this.termStatus = new SimpleStringProperty();
//        this.id = null;
//    }

//    @Override
//    public Term createEntity() {
//        Term entity = new Term();
//        updateEntity(entity);
//        return entity;
//    }

//    @Override
//    public void updateEntity(Term entity) {
//        entity.setId(getId());
//        entity.setTermNumber(getTermNumber());
//        entity.setTakenUnits(getTakenUnits());
//        entity.setPassedUnits(getPassedUnits());
//        entity.setDeletedUnits(getDeletedUnits());
//        entity.setFailedUnits(getFailedUnits());
//        entity.setUnspecifiedUnits(getUnspecifiedUnits());
//        entity.setZeroUnits(getZeroUnits());
//        entity.setTermAverage(getTermAverage());
//        entity.setTotalAverage(getTotalAverage());
        
//        TermStatusDao termStatusDao = AppContextUtils.getBean(TermStatusDao.class);
//        entity.setTermStatus(termStatusDao.loadByValue(getTermStatus()));
 //   }

  //  @Override
  //  public void fromEntity(Term entity) {
//        setId(entity.getId());
//        setTermNumber(entity.getTermNumber());
//        setTakenUnits(entity.getTakenUnits());
//        setPassedUnits(entity.getPassedUnits());
//        setDeletedUnits(entity.getDeletedUnits());
//        setFailedUnits(entity.getFailedUnits());
//        setUnspecifiedUnits(entity.getUnspecifiedUnits());
//        setZeroUnits(entity.getZeroUnits());
//        setTermAverage(entity.getTermAverage());
//        setTotalAverage(entity.getTotalAverage());
//        setTermStatus(entity.getTermStatus().getValue());
//    }

//    @Override
//    public boolean hasError() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

//    @Override
//    public void clear() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public static String getTermNumber() {
        return termNumber.get();
    }

    public static void setTermNumber(String value) {
        termNumber.set(value);
    }

    public static String getTakenUnits() {
        return takenUnits.get();
    }

    public static void setTakenUnits(String value) {
        takenUnits.set(value);
    }

    public static String getPassedUnits() {
        return passedUnits.get();
    }

    public static void setPassedUnits(String value) {
        passedUnits.set(value);
    }

    public static String getDeletedUnits() {
        return deletedUnits.get();
    }

    public static void setDeletedUnits(String value) {
        deletedUnits.set(value);
    }

    public static String getFailedUnits() {
        return failedUnits.get();
    }

    public static void setFailedUnits(String value) {
        failedUnits.set(value);
    }

    public static String getUnspecifiedUnits() {
        return unspecifiedUnits.get();
    }

    public static void setUnspecifiedUnits(String value) {
        unspecifiedUnits.set(value);
    }

    public static String getZeroUnits() {
        return zeroUnits.get();
    }

    public static void setZeroUnits(String value) {
        zeroUnits.set(value);
    }

    public static String getTermAverage() {
        return termAverage.get();
    }

    public static void setTermAverage(String value) {
        termAverage.set(value);
    }

//    public static String getTotalAverage() {
//        return totalAverage.get();
//    }
//
//    public static void setTotalAverage(String value) {
//        totalAverage.set(value);
//    }

    public static String getTermStatus() {
        return termStatus.get();
    }

    public static void setTermStatus(String value) {
        termStatus.set(value);
    }
    
     public static boolean getUpdateAlert() {
        return updateAlert;
    }

    public static void setUpdateAlert(boolean value) {
        updateAlert=value;
    }
    
     public static void reset() {
        setTermNumber("0");
        setTakenUnits("0");
        setPassedUnits("0");
        setDeletedUnits("0");
        setFailedUnits("0");
        setUnspecifiedUnits("0");
        setZeroUnits("0");
        setTermAverage("0");
    //    setTotalAverage("0");
        setTermStatus(" ");
        setUpdateAlert(true);
    }
}
