package ir.isar.isarapp.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author User
 */
public class OldTermModel implements Serializable{
    @ColumnOrder(order = 0)
    private String studentNumber;

    @ColumnOrder(order = 1)
    private String termNumber;

    @ColumnOrder(order = 2)
    private String takenUnits;

    @ColumnOrder(order = 3)
    private String passedUnits;

    @ColumnOrder(order = 4)
    private String failedUnits;

    @ColumnOrder(order = 5)
    private String deletedUnits;

    @ColumnOrder(order = 6)
    private String zeroUnits;

    @ColumnOrder(order = 7)
    private String termAverage;

    @ColumnOrder(order = 8)
    private String termAverageWithoutZero;

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getTermNumber() {
        return termNumber;
    }

    public void setTermNumber(String termNumber) {
        this.termNumber = termNumber;
    }

    public String getTakenUnits() {
        return takenUnits;
    }

    public void setTakenUnits(String takenUnits) {
        this.takenUnits = takenUnits;
    }

    public String getPassedUnits() {
        return passedUnits;
    }

    public void setPassedUnits(String passedUnits) {
        this.passedUnits = passedUnits;
    }

    public String getFailedUnits() {
        return failedUnits;
    }

    public void setFailedUnits(String failedUnits) {
        this.failedUnits = failedUnits;
    }

    public String getDeletedUnits() {
        return deletedUnits;
    }

    public void setDeletedUnits(String deletedUnits) {
        this.deletedUnits = deletedUnits;
    }

    public String getZeroUnits() {
        return zeroUnits;
    }

    public void setZeroUnits(String zeroUnits) {
        this.zeroUnits = zeroUnits;
    }

    public String getTermAverage() {
        return termAverage;
    }

    public void setTermAverage(String termAverage) {
        this.termAverage = termAverage;
    }

    public String getTermAverageWithoutZero() {
        return termAverageWithoutZero;
    }

    public void setTermAverageWithoutZero(String termAverageWithoutZero) {
        this.termAverageWithoutZero = termAverageWithoutZero;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
