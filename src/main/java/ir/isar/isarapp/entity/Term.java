package ir.isar.isarapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author User
 */
@Entity
public class Term extends Base {
    @ManyToOne(optional = false)
    private Student student;

    @Column(nullable = false)
    private Integer termNumber;

    @Column(nullable = false)
    private Integer takenUnits;
    
    @Column(nullable = false)
    private Integer passedUnits;
    
    @Column(nullable = false)
    private Integer deletedUnits;
    
    @Column(nullable = false)
    private Integer failedUnits;
    
    @Column(nullable = false)
    private Integer unspecifiedUnits;
    
    @Column(nullable = false)
    private Integer zeroUnits;
    
    @Column(nullable = false)
    private Double termAverage;
    
    @Column(nullable = false)
    private Double totalAverage;
    
    @ManyToOne(optional = false)
    private TermStatus termStatus;
    
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getTermNumber() {
        return termNumber;
    }

    public void setTermNumber(Integer termNumber) {
        this.termNumber = termNumber;
    }

    public Integer getTakenUnits() {
        return takenUnits;
    }

    public void setTakenUnits(Integer takenUnits) {
        this.takenUnits = takenUnits;
    }

    public Integer getPassedUnits() {
        return passedUnits;
    }

    public void setPassedUnits(Integer passedUnits) {
        this.passedUnits = passedUnits;
    }

    public Integer getDeletedUnits() {
        return deletedUnits;
    }

    public void setDeletedUnits(Integer deletedUnits) {
        this.deletedUnits = deletedUnits;
    }

    public Integer getFailedUnits() {
        return failedUnits;
    }

    public void setFailedUnits(Integer failedUnits) {
        this.failedUnits = failedUnits;
    }

    public Integer getUnspecifiedUnits() {
        return unspecifiedUnits;
    }

    public void setUnspecifiedUnits(Integer unspecifiedUnits) {
        this.unspecifiedUnits = unspecifiedUnits;
    }

    public Integer getZeroUnits() {
        return zeroUnits;
    }

    public void setZeroUnits(Integer zeroUnits) {
        this.zeroUnits = zeroUnits;
    }

    public Double getTermAverage() {
        return termAverage;
    }

    public void setTermAverage(Double termAverage) {
        this.termAverage = termAverage;
    }

    public Double getTotalAverage() {
        return totalAverage;
    }

    public void setTotalAverage(Double totalAverage) {
        this.totalAverage = totalAverage;
    }

    public TermStatus getTermStatus() {
        return termStatus;
    }

    public void setTermStatus(TermStatus termStatus) {
        this.termStatus = termStatus;
    }

}
