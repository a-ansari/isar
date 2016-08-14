package ir.isar.isarapp.biz;

import com.google.inject.Inject;
import ir.isar.isarapp.dao.PaymentDao;
import ir.isar.isarapp.dao.StudentDao;
import ir.isar.isarapp.entity.Payment;
import ir.isar.isarapp.entity.PaymentType;
import ir.isar.isarapp.entity.Student;

import javax.inject.Singleton;
import java.util.List;

/**
 * Created by User
 */
@Singleton
public class PaymentBiz {
    @Inject
    private StudentBiz studentBiz;
    @Inject
    private StudentDao studentDao;
    @Inject
    private FacilityBiz facilityBiz;
    @Inject
    private PaymentDao paymentDao;

    public void doPaymentAction(PaymentType paymentType, Integer termNo) {
        List<Student> students = studentDao.loadAllStudyingStudents();
        for (Student student : students) {
            long amount = facilityBiz.calculatePaymentAmount(student.getStudentNumber(), paymentType);
            Payment payment = new Payment();
            payment.setStudent(student);
            payment.setTermNumber(termNo);
            payment.setPaymentType(paymentType);
            payment.setAmount(amount);
            paymentDao.save(payment);
        }
    }

    public List<Payment> loadAllPayments(Student student) {
        return paymentDao.loadAllPayments(student);
    }
}
