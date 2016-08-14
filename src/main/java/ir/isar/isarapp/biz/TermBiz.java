package ir.isar.isarapp.biz;

import ir.isar.isarapp.dao.TermDao;
import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.entity.Term;
import ir.isar.isarapp.excp.DataValidationException;
import ir.isar.isarapp.model.TermModel;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class TermBiz {

    @Inject
    private TermDao termDao;

    public List<Term> loadAllTerms(Student student) {
        return termDao.loadAllTerms(student);
    }

    public Term loadLastTerm(Student student) {
        return termDao.loadLastTerm(student);
    }

    public Term loadByStudentAndTermNumber(Long studentNumber, Integer termNumber) {
        return termDao.loadByStudentAndTermNumber(studentNumber, termNumber);
    }

    public void saveTermInfo(TermModel model, Student student) throws DataValidationException {
        Term entity;
        if (model.getId() == null) {
            entity = new Term();
            entity.setStudent(student);
        } else {
            entity = termDao.loadById(model.getId());
        }
        model.updateEntity(entity);
        termDao.validate(entity);
        termDao.save(entity);
        model.setId(entity.getId());
    }

    public void delete(Long id) {
        termDao.deleteById(id);
    }

}
