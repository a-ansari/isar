package ir.isar.isarapp.oldapp.convert;

import ir.isar.isarapp.dao.StudentDao;
import ir.isar.isarapp.dao.TermDao;
import ir.isar.isarapp.entity.Term;
import ir.isar.isarapp.model.OldTermModel;
import ir.isar.isarapp.util.ConversionUtils;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class TermConverter implements BaseModelConverter<OldTermModel, Term> {

    @Inject
    private ConversionUtils conversionUtils;
    @Inject
    private TermDao termDao;
    @Inject
    private StudentDao studentDao;

    @Override
    public OldTermModel createModel() {
        return new OldTermModel();
    }

    @Override
    public Term createEntity(OldTermModel model) {
        long studentNumber = conversionUtils.parseAsLong(model.getStudentNumber());
        int termNumber = conversionUtils.parseAsInteger(model.getTermNumber());

        Term term = termDao.loadByStudentAndTermNumber(studentNumber, termNumber);
        if (term == null) {
            term = new Term();
        }
        return term;
    }

    @Override
    public void convertModelToEntity(OldTermModel model, Term entity) {
        long studentNumber = conversionUtils.parseAsLong(model.getStudentNumber());
        entity.setStudent(studentDao.loadByStudentNumber(studentNumber));
        entity.setTermNumber(conversionUtils.parseAsInteger(model.getTermNumber()));
        entity.setTakenUnits(conversionUtils.parseAsIntegerFromDouble(model.getTakenUnits()));
        entity.setPassedUnits(conversionUtils.parseAsIntegerFromDouble(model.getPassedUnits()));
        entity.setFailedUnits(conversionUtils.parseAsIntegerFromDouble(model.getFailedUnits()));
        entity.setDeletedUnits(conversionUtils.parseAsIntegerFromDouble(model.getDeletedUnits()));
        entity.setZeroUnits(conversionUtils.parseAsIntegerFromDouble(model.getZeroUnits()));
        entity.setTermAverage(conversionUtils.parseAsDouble(model.getTermAverage()));

        entity.setTermStatus(termDao.guessTermStatus(entity)); // this line must be last
        entity.setTotalAverage(0.0);
        entity.setUnspecifiedUnits(0);
    }

    @Override
    public void saveEntity(Term entity) {
        termDao.save(entity);
    }

}
