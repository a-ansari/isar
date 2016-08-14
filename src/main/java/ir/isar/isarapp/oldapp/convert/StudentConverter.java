package ir.isar.isarapp.oldapp.convert;

import ir.isar.isarapp.dao.StudentDao;
import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.model.OldStudentModel;
import ir.isar.isarapp.oldapp.OldToNewStudentConverter;
import ir.isar.isarapp.oldapp.SharifOnlyConverter;
import ir.isar.isarapp.util.ConversionUtils;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class StudentConverter implements BaseModelConverter<OldStudentModel, Student> {

    @Inject
    private ConversionUtils conversionUtils;
    @Inject
    private StudentDao studentDao;
    @Inject
    private OldToNewStudentConverter oldToNewStudentConverter;
    @Inject
    private SharifOnlyConverter sharifOnlyConverter;

    @Override
    public OldStudentModel createModel() {
        return new OldStudentModel();
    }

    @Override
    public Student createEntity(OldStudentModel model) {
        long studentNumber = conversionUtils.parseAsLong(model.getStudentNumber());
        Student student = studentDao.loadByStudentNumber(studentNumber);
        if (student == null) {
            student = new Student();
        }
        return student;
    }

    @Override
    public void convertModelToEntity(OldStudentModel model, Student entity) {
        oldToNewStudentConverter.convert(model, entity);
        sharifOnlyConverter.convert(model, entity);
    }

    @Override
    public void saveEntity(Student entity) {
        studentDao.save(entity);
    }

}
