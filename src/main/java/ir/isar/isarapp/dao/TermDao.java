package ir.isar.isarapp.dao;

import com.avaje.ebean.Ebean;
import ir.isar.isarapp.entity.EducationalDegree;
import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.entity.Term;
import ir.isar.isarapp.entity.TermStatus;
import ir.isar.isarapp.excp.DataValidationException;
import java.util.List;
import java.util.ResourceBundle;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class TermDao extends BaseDao<Term> {

    private final ResourceBundle messages = ResourceBundle.getBundle(getClass().getName());

    @Override
    public Class<Term> getEntityClass() {
        return Term.class;
    }

    public Term loadByStudentAndTermNumber(Long studentNumber, Integer termNumber) {
        return Ebean.find(Term.class)
                .fetch("student", "studentNumber")
                .where()
                .eq("student.studentNumber", studentNumber)
                .eq("termNumber", termNumber)
                .findUnique();
    }

    public TermStatus guessTermStatus(Term term) {
        try {
            EducationalDegree educationalDegree = term.getStudent().getEducationalDegree();
            double average = term.getTermAverage();
            if (average == 0) {
                return TermStatus.Unknown;
            }
            if (educationalDegree.equals(EducationalDegree.Karshenasi)) {
                return (average < 12) ? TermStatus.Conditional : TermStatus.Normal;
            }
            if (educationalDegree.equals(EducationalDegree.Arshad)) {
                return (average < 14) ? TermStatus.Conditional : TermStatus.Normal;
            }
            if (educationalDegree.equals(EducationalDegree.Doctora)) {
                return (average < 16) ? TermStatus.Conditional : TermStatus.Normal;
            }
            return TermStatus.Unknown;
        } catch (NullPointerException ex) {
            return TermStatus.Unknown;
        }
    }

    public List<Term> loadAllTerms(Student student) {
        return Ebean.find(Term.class)
                .where()
                .eq("student", student)
                .orderBy("termNumber")
                .findList();
    }

    public Term loadLastTerm(Student student) {
        return Ebean.find(Term.class)
                .where()
                .eq("student", student)
                .orderBy()
                .desc("termNumber")
                .setMaxRows(1)
                .findUnique();
    }

    public void validate(Term entity) throws DataValidationException {
        if (entity.getTermStatus() == null) {
            throw new DataValidationException(messages.getString("isar.TermDao.TermStatusRequired"));
        }
        if (entity.getTakenUnits() != entity.getPassedUnits() + entity.getFailedUnits() + entity.getUnspecifiedUnits() + entity.getZeroUnits()) {
            throw new DataValidationException(messages.getString("isar.TermDao.InvalidTakenUnits"));
        }
    }

    public void deleteTermsForStudent(Student student) {
        Ebean.delete(loadAllTerms(student));
    }

}
