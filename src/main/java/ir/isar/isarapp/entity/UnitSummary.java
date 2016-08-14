package ir.isar.isarapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author User
 */
@Entity
public class UnitSummary extends Base {

    @Column(nullable = false)
    private Integer totalTerms;

    @Column(nullable = false)
    private Integer totalTaken;

    @Column(nullable = false)
    private Integer totalPassed;

    @Column(nullable = false)
    private Integer totalDeleted;

    @Column(nullable = false)
    private Integer totalFailed;

    @Column(nullable = false)
    private Integer totalUnspecified;

    @Column(nullable = false)
    private Integer totalZero;

    @Column(nullable = false)
    private Double totalAverage;

    @Column(nullable = false)
    private Integer totalConditional;

    public Integer getTotalTerms() {
        return totalTerms;
    }

    public void setTotalTerms(Integer totalTerms) {
        this.totalTerms = totalTerms;
    }

    public Integer getTotalTaken() {
        return totalTaken;
    }

    public void setTotalTaken(Integer totalTaken) {
        this.totalTaken = totalTaken;
    }

    public Integer getTotalPassed() {
        return totalPassed;
    }

    public void setTotalPassed(Integer totalPassed) {
        this.totalPassed = totalPassed;
    }

    public Integer getTotalDeleted() {
        return totalDeleted;
    }

    public void setTotalDeleted(Integer totalDeleted) {
        this.totalDeleted = totalDeleted;
    }

    public Integer getTotalFailed() {
        return totalFailed;
    }

    public void setTotalFailed(Integer totalFailed) {
        this.totalFailed = totalFailed;
    }

    public Integer getTotalUnspecified() {
        return totalUnspecified;
    }

    public void setTotalUnspecified(Integer totalUnspecified) {
        this.totalUnspecified = totalUnspecified;
    }

    public Integer getTotalZero() {
        return totalZero;
    }

    public void setTotalZero(Integer totalZero) {
        this.totalZero = totalZero;
    }

    public Double getTotalAverage() {
        return totalAverage;
    }

    public void setTotalAverage(Double totalAverage) {
        this.totalAverage = totalAverage;
    }

    public Integer getTotalConditional() {
        return totalConditional;
    }

    public void setTotalConditional(Integer totalConditional) {
        this.totalConditional = totalConditional;
    }

}
