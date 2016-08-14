package ir.isar.isarapp.biz;

import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.entity.Term;
import ir.isar.isarapp.entity.UnitSummary;
import ir.isar.isarapp.filter.result.ResultColumn;
import ir.isar.isarapp.model.StudentFullModel;
import ir.isar.isarapp.util.ConversionUtils;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author User
 */
@Singleton
public class StudentFullBiz {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private UnitSummaryBiz unitSummaryBiz;

    @Inject
    private TermBiz termBiz;

    @Inject
    private ConversionUtils conversionUtils;

    public StudentFullModel convertToFullModel(Student student, List<ResultColumn> columns) {
        UnitSummary unitSummary = student.getUnitSummary();
        if (unitSummary == null) {
            logger.warn("Student without summary info: {}", student.getStudentNumber());
            unitSummaryBiz.refreshSummaryInfo(student);
            unitSummary = student.getUnitSummary();
        }

        Term lastTerm = termBiz.loadLastTerm(student);
        if (lastTerm == null) {
            lastTerm = new Term();
        }

        StudentFullModel model = new StudentFullModel();
        model.fromEntity(student, unitSummary, lastTerm, columns, conversionUtils);
        return model;
    }

    public List<StudentFullModel> convertToFullModel(List<Student> studentList) {
        List<StudentFullModel> result = new ArrayList<>();
        for (Student student : studentList) {
            result.add(convertToFullModel(student, null));
        }
        return result;
    }
}
