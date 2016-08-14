package ir.isar.isarapp.dao;

import com.avaje.ebean.Ebean;
import ir.isar.isarapp.entity.EducationalField;
import ir.isar.isarapp.entity.Professor;
import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.util.ConversionUtils;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class ProfessorDao extends BaseDao<Professor> {

    @Inject
    private EducationalFieldDao educationalFieldDao;

    @Inject
    private StudentDao studentDao;

    @Override
    public Class<Professor> getEntityClass() {
        return Professor.class;
    }

    public List<Professor> loadAll(String field, String group) {
        EducationalField fieldEntity = educationalFieldDao.load(field, group);
        return Ebean.find(getEntityClass()).where().eq("educationalField", fieldEntity).findList();
    }

    public List<String> loadAllValues(String field, String group) {
        List<String> result = new ArrayList<>();
        for (Professor prof : loadAll(field, group)) {
            result.add(prof.getValue());
        }
        return result;
    }

    public Professor load(String professor, EducationalField fieldEntity) {
        return Ebean.find(getEntityClass()).where().eq("educationalField", fieldEntity).eq("value", professor).findUnique();
    }

    public Professor load(String professor, String field, String group) {
        EducationalField fieldEntity = educationalFieldDao.load(field, group);
        return load(professor, fieldEntity);
    }

    public Professor loadOrAdd(String professor, EducationalField fieldEntity) {
        if (professor == null || professor.isEmpty() || professor.equals(ConversionUtils.NULL_STRING)) {
            return null;
        }
        Professor existed = load(professor, fieldEntity);
        if (existed == null) {
            existed = new Professor();
            existed.setValue(professor);
            existed.setEducationalField(fieldEntity);
            Ebean.save(existed);
        }
        return existed;
    }

    @Override
    public void deleteById(Long id) {
        Professor professor = loadById(id);
        List<Student> studentList = Ebean.find(Student.class).where()
                .eq("professor", professor)
                .findList();
        for (Student student : studentList) {
            student.setProfessor(null);
            studentDao.save(student);
        }
        super.deleteById(id);
    }
}
