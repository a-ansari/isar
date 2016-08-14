package ir.isar.isarapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author User
 */
@Entity
public class Payment extends Base {

    @ManyToOne(optional = false)
    private Student student;

    @Column(nullable = false)
    private Integer termNumber;

    @Column(nullable = false)
    private Long amount;

    @ManyToOne(optional = false)
    private PaymentType paymentType;

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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
