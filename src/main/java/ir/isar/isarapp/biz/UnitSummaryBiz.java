package ir.isar.isarapp.biz;

import ir.isar.isarapp.dao.StudentDao;
import ir.isar.isarapp.dao.UnitSummaryDao;
import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.entity.Term;
import ir.isar.isarapp.entity.TermStatus;
import ir.isar.isarapp.entity.UnitSummary;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class UnitSummaryBiz {

    @Inject
    private UnitSummaryDao unitSummaryDao;

    @Inject
    private TermBiz termBiz;

    @Inject
    private StudentDao studentDao;

    private void calculateSummaryInfo(Student student, UnitSummary unitSummary) {
        int totalTerms = 0;
        int totalTaken = 0;
        int totalPassed = 0;
        int totalDeleted = 0;
        int totalFailed = 0;
        int totalUnspecified = 0;
        int totalZero = 0;
        double totalAverage = 0.0;
        int totalConditional = 0;

        for (Term term : termBiz.loadAllTerms(student)) {
            if ( term.getTakenUnits() != 0) {
                totalAverage = ((totalAverage * totalTaken) + (term.getTermAverage() * (term.getTakenUnits()-term.getUnspecifiedUnits()))) / (totalTaken + term.getTakenUnits()-term.getUnspecifiedUnits());
            }
            if (!term.getTermStatus().equals(TermStatus.RemovedWithoutCounting))
               totalTerms++;
            totalTaken += term.getTakenUnits();
            totalPassed += term.getPassedUnits();
            totalDeleted += term.getDeletedUnits();
            totalFailed += term.getFailedUnits();
            totalUnspecified += term.getUnspecifiedUnits();
            totalZero += term.getZeroUnits();
            if (term.getTermStatus().equals(TermStatus.Conditional)&&(term.getTermNumber()%10!=3)) {
                totalConditional++;
            }
        }
        totalAverage = Math.round(totalAverage * 100.0) / 100.0;

        unitSummary.setTotalTerms(totalTerms);
        unitSummary.setTotalTaken(totalTaken);
        unitSummary.setTotalPassed(totalPassed);
        unitSummary.setTotalDeleted(totalDeleted);
        unitSummary.setTotalFailed(totalFailed);
        unitSummary.setTotalUnspecified(totalUnspecified);
        unitSummary.setTotalZero(totalZero);
        unitSummary.setTotalAverage(totalAverage);
        unitSummary.setTotalConditional(totalConditional);
    }

    public void refreshSummaryInfo(Student student) {
        UnitSummary unitSummary = student.getUnitSummary();
        if (unitSummary == null) {
            saveUnitSummary(student);
        } else {
            calculateSummaryInfo(student, unitSummary);
            unitSummaryDao.update(unitSummary);
        }
    }

    public UnitSummary createNewSummaryInfo() {
        UnitSummary unitSummary = new UnitSummary();
        unitSummary.setTotalAverage(0.0);
        unitSummary.setTotalConditional(0);
        unitSummary.setTotalDeleted(0);
        unitSummary.setTotalFailed(0);
        unitSummary.setTotalPassed(0);
        unitSummary.setTotalTaken(0);
        unitSummary.setTotalTerms(0);
        unitSummary.setTotalUnspecified(0);
        unitSummary.setTotalZero(0);
        unitSummaryDao.save(unitSummary);
        return unitSummary;
    }

    private void saveUnitSummary(Student student) {
        UnitSummary unitSummary = new UnitSummary();
        calculateSummaryInfo(student, unitSummary);
        unitSummaryDao.save(unitSummary);
        student.setUnitSummary(unitSummary);
        studentDao.save(student);
    }

    void makeAllUnitSummaryInfo() {
        int firstRow = 0, maxRows = 100;
        List<Student> list;
        do {
            list = studentDao.loadAllStudents(firstRow, maxRows);
            for (Student student : list) {
                saveUnitSummary(student);
            }
            firstRow += maxRows;
        } while (!list.isEmpty());
    }

}
