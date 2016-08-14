package ir.isar.isarapp.biz;

import ir.isar.isarapp.dao.EducationalDegreeDao;
import ir.isar.isarapp.dao.EducationalFieldDao;
import ir.isar.isarapp.dao.EducationalGroupDao;
import ir.isar.isarapp.dao.EducationalTrendDao;
import ir.isar.isarapp.dao.EducationalTypeDao;
import ir.isar.isarapp.dao.GraduationStatusDao;
import ir.isar.isarapp.dao.ProfessorDao;
import ir.isar.isarapp.dao.SegmentDao;
import ir.isar.isarapp.dao.StudentDao;
import ir.isar.isarapp.dao.TermDao;
import ir.isar.isarapp.dao.UnitSummaryDao;
import ir.isar.isarapp.enm.GenderEnum;
import ir.isar.isarapp.enm.MarriageEnum;
import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.entity.UnitSummary;
import ir.isar.isarapp.excp.DataValidationException;
import ir.isar.isarapp.model.StudentModel;
import ir.isar.isarapp.util.BeanCopierUtils;
import ir.isar.isarapp.util.ConversionUtils;
import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author User
 */
@Singleton
public class StudentBiz {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ResourceBundle messages = ResourceBundle.getBundle(getClass().getName());

    @Inject
    private ConversionUtils conversionUtils;
    @Inject
    private BeanCopierUtils beanCopierUtils;

    @Inject
    private EducationalDegreeDao educationalDegreeDao;
    @Inject
    private EducationalFieldDao educationalFieldDao;
    @Inject
    private EducationalGroupDao educationalGroupDao;
    @Inject
    private EducationalTrendDao educationalTrendDao;
    @Inject
    private EducationalTypeDao educationalTypeDao;
    @Inject
    private GraduationStatusDao graduationStatusDao;
    @Inject
    private ProfessorDao professorDao;
    @Inject
    private SegmentDao segmentDao;
    @Inject
    private StudentDao studentDao;
    @Inject
    private TermDao termDao;
    @Inject
    private UnitSummaryDao unitSummaryDao;

    public void convertModelToEntity(StudentModel model, Student entity) throws IllegalArgumentException {
        try {
            entity.setStudentNumber(conversionUtils.parseAsLong(model.getStudentNumber()));
            entity.setFirstName(conversionUtils.parseAsString(model.getFirstName()));
            entity.setLastName(conversionUtils.parseAsString(model.getLastName()));
            entity.setNationalNumber(conversionUtils.parseAsLong(model.getNationalNumber()));
            entity.setBirthCertNumber(conversionUtils.parseAsLong(model.getBirthCertNumber()));
            entity.setBirthDate(conversionUtils.parseAsString(model.getBirthDate()));
            entity.setIssuePlace(conversionUtils.parseAsString(model.getIssuePlace()));
            entity.setFatherName(conversionUtils.parseAsString(model.getFatherName()));
            entity.setGender(GenderEnum.getByLocalName(model.getGender()));
            entity.setMarriage(MarriageEnum.getByLocalName(model.getMarriage()));
            entity.setDiplomaType(conversionUtils.parseAsString(model.getDiplomaType()));
            entity.setDiplomaAverage(conversionUtils.parseAsDouble(model.getDiplomaAverage()));
            entity.setDiplomaYear(conversionUtils.parseAsInteger(model.getDiplomaYear()));
            entity.setDiplomaPlace(conversionUtils.parseAsString(model.getDiplomaPlace()));
            entity.setSegment(segmentDao.loadByValue(model.getSegment()));
            entity.setSegmentPercent(conversionUtils.parseAsInteger(model.getSegmentPercent()));
            entity.setSegmentInfo(conversionUtils.parseAsString(model.getSegmentInfo()));
            entity.setSegmentPlace(conversionUtils.parseAsString(model.getSegmentPlace()));
            entity.setSegmentRank(conversionUtils.parseAsInteger(model.getSegmentRank()));
            entity.setTotalRank(conversionUtils.parseAsInteger(model.getTotalRank()));
            entity.setEntranceYear(conversionUtils.parseAsInteger(model.getEntranceYear()));
            entity.setEducationalType(educationalTypeDao.loadByValue(model.getEducationalType()));
            entity.setEducationalDegree(educationalDegreeDao.loadByValue(model.getEducationalDegree()));
            entity.setEducationalGroup(educationalGroupDao.loadByValue(model.getEducationalGroup()));
            entity.setEducationalField(educationalFieldDao.load(model.getEducationalField(), entity.getEducationalGroup()));
            entity.setEducationalTrend(educationalTrendDao.load(model.getEducationalTrend(), entity.getEducationalField()));
            entity.setProfessor(professorDao.load(model.getProfessor(), entity.getEducationalField()));
            entity.setGraduationStatus(graduationStatusDao.loadByValue(model.getGraduationStatus()));
            entity.setGraduationInfo(conversionUtils.parseAsString(model.getGraduationInfo()));
            entity.setAddress(conversionUtils.parseAsString(model.getAddress()));
            entity.setPhoneNumber(conversionUtils.parseAsString(model.getPhoneNumber()));
            entity.setMobileNumber(conversionUtils.parseAsString(model.getMobileNumber()));
            entity.setEmergencyNumber(conversionUtils.parseAsString(model.getEmergencyNumber()));
            entity.setEmail(conversionUtils.parseAsString(model.getEmail()));
            entity.setAccountNumber(conversionUtils.parseAsString(model.getAccountNumber()));
        } catch (NumberFormatException ex) {
            throw new DataValidationException(messages.getString("isar.StudentBiz.errorInSave"));
        }
    }

    public StudentModel convertToModel(Student entity) {
        try {
            StudentModel model = new StudentModel();
            beanCopierUtils.copy(entity, model);
            model.setNationalNumber(conversionUtils.nationalCode(entity.getNationalNumber()));
            model.setBirthCertNumber(conversionUtils.nationalCode(entity.getNationalNumber()));
            return model;
        } catch (Exception ex) {
            logger.info("Exception in convert entity to model", ex);
            throw new IllegalArgumentException(ex);
        }
    }

    public void saveOrUpdateStudent(Student entity) {
        if (entity.getId() == null) {
            logger.debug("Save Student: {}", entity);
            studentDao.save(entity);
        } else {
            logger.debug("Update Student: {}", entity);
            studentDao.update(entity);
        }
    }

    public Student loadStudent(long studentNo) {
        logger.debug("Load Student: {}", studentNo);
        return studentDao.loadByStudentNumber(studentNo);
    }

    public void deleteStudent(Student student) {
        logger.debug("Delete Student: {}", student.getStudentNumber());
        termDao.deleteTermsForStudent(student);
        UnitSummary unitSummary = student.getUnitSummary();
        studentDao.delete(student);
        unitSummaryDao.delete(unitSummary);
    }
}
