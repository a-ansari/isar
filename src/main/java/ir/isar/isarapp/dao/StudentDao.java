package ir.isar.isarapp.dao;

import com.avaje.ebean.Ebean;
import ir.isar.isarapp.entity.GraduationStatus;
import ir.isar.isarapp.entity.Student;
import java.util.List;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class StudentDao extends BaseDao<Student> {
    
    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }
    
    public Student loadByStudentNumber(Long studentNumber) {
        return Ebean.find(Student.class).where().eq("studentNumber", studentNumber).findUnique();
    }
    
    public List<Student> loadAllStudyingStudents() {
        return Ebean.find(Student.class)
                .where()
                .eq("graduationStatus", GraduationStatus.Studying)
                .orderBy("studentNumber")
                .findList();
    }

    public List<Student> loadAllStudents(int firstRow, int maxRows) {
        return Ebean.find(Student.class)
                .orderBy("studentNumber")
                .setFirstRow(firstRow)
                .setMaxRows(maxRows)
                .findList();
    }
}
