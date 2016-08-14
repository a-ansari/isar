package ir.isar.isarapp.dao;

import com.avaje.ebean.Ebean;
import ir.isar.isarapp.entity.Payment;
import ir.isar.isarapp.entity.Student;

import javax.inject.Singleton;
import java.util.List;

/**
 * @author User
 */
@Singleton
public class PaymentDao extends BaseDao<Payment> {

    @Override
    public Class<Payment> getEntityClass() {
        return Payment.class;
    }

    public List<Payment> loadAllPayments(Student student) {
        return Ebean.find(Payment.class)
                .where()
                .eq("student", student)
                .orderBy("termNumber")
                .findList();
    }

}
