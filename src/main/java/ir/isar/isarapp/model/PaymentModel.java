package ir.isar.isarapp.model;

import ir.isar.isarapp.entity.Payment;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Abolfazl on 12/12/2015.
 */
public class PaymentModel {
    private final SimpleIntegerProperty termNumber;
    private final SimpleStringProperty paymentType;
    private final SimpleLongProperty amount;

    public PaymentModel() {
        this.termNumber = new SimpleIntegerProperty();
        this.paymentType = new SimpleStringProperty();
        this.amount = new SimpleLongProperty();
    }

    public void fromEntity(Payment payment) {
        setTermNumber(payment.getTermNumber());
        setPaymentType(payment.getPaymentType().getValue());
        setAmount(payment.getAmount());
    }

    public int getTermNumber() {
        return termNumber.get();
    }

    public SimpleIntegerProperty termNumberProperty() {
        return termNumber;
    }

    public void setTermNumber(int termNumber) {
        this.termNumber.set(termNumber);
    }

    public String getPaymentType() {
        return paymentType.get();
    }

    public SimpleStringProperty paymentTypeProperty() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType.set(paymentType);
    }

    public long getAmount() {
        return amount.get();
    }

    public SimpleLongProperty amountProperty() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount.set(amount);
    }
}
