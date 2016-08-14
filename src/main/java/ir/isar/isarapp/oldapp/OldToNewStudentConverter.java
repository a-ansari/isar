package ir.isar.isarapp.oldapp;

import ir.isar.isarapp.model.OldStudentModel;
import ir.isar.isarapp.dao.EducationalDegreeDao;
import ir.isar.isarapp.dao.EducationalFieldDao;
import ir.isar.isarapp.dao.EducationalGroupDao;
import ir.isar.isarapp.dao.EducationalTrendDao;
import ir.isar.isarapp.dao.EducationalTypeDao;
import ir.isar.isarapp.dao.GraduationStatusDao;
import ir.isar.isarapp.dao.ProfessorDao;
import ir.isar.isarapp.dao.SegmentDao;
import ir.isar.isarapp.enm.GenderEnum;
import ir.isar.isarapp.enm.MarriageEnum;
import ir.isar.isarapp.entity.EducationalField;
import ir.isar.isarapp.entity.EducationalGroup;
import ir.isar.isarapp.entity.EducationalTrend;
import ir.isar.isarapp.entity.Professor;
import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.util.ConversionUtils;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author User
 */
@Singleton
public class OldToNewStudentConverter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private ConversionUtils conversionUtils;

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

    public void convert(OldStudentModel model, Student entity) {
        entity.setStudentNumber(conversionUtils.parseAsLong(model.getStudentNumber()));
        entity.setFirstName(conversionUtils.parseAsString(model.getFirstName()));
        entity.setLastName(conversionUtils.parseAsString(model.getLastName()));
        entity.setNationalNumber(null);
        entity.setBirthCertNumber(conversionUtils.parseAsLongNoZero(model.getBirthCertNumber()));
        entity.setBirthDate(convertBirthDate(model.getBirthDate()));
        entity.setIssuePlace(conversionUtils.parseAsString(model.getIssuePlace()));
        entity.setFatherName(conversionUtils.parseAsString(model.getFatherName()));
        entity.setGender(GenderEnum.getByCode(conversionUtils.parseAsInteger(model.getGender())));
        entity.setMarriage(MarriageEnum.getByCode(conversionUtils.parseAsInteger(model.getMarriage())));
        entity.setDiplomaType(conversionUtils.parseAsString(model.getDiplomaType()));
        entity.setDiplomaAverage(conversionUtils.parseAsDoubleNoZero(model.getDiplomaAverage()));
        entity.setDiplomaYear(conversionUtils.parseAsIntegerNoZero(model.getDiplomaYear()));
        entity.setDiplomaPlace(conversionUtils.parseAsString(model.getDiplomaPlace()));
        entity.setSegment(segmentDao.loadOrAdd(model.getSegment()));
        entity.setSegmentPercent(null);
        entity.setSegmentInfo(conversionUtils.parseAsString(model.getSegmentInfo()));
        entity.setSegmentRank(conversionUtils.parseAsIntegerNoZero(model.getSegmentRank()));
        entity.setTotalRank(conversionUtils.parseAsIntegerNoZero(model.getTotalRank()));
        entity.setEntranceYear(conversionUtils.parseAsIntegerNoZero(model.getEntranceYear()));
        entity.setEducationalType(educationalTypeDao.loadOrAdd(model.getEducationalType()));
        entity.setEducationalDegree(educationalDegreeDao.loadOrAdd(model.getEducationalDegree()));

        EducationalGroup educationalGroup = educationalGroupDao.loadOrAdd(model.getEducationalGroup());
        EducationalField educationalField = educationalFieldDao.loadOrAdd(model.getEducationalField(), educationalGroup);
        EducationalTrend educationalTrend = educationalTrendDao.loadOrAdd(model.getEducationalTrend(), educationalField);
        Professor professor = professorDao.loadOrAdd(model.getProfessor(), educationalField);

        entity.setEducationalGroup(educationalGroup);
        entity.setEducationalField(educationalField);
        entity.setEducationalTrend(educationalTrend);
        entity.setProfessor(professor);
        entity.setGraduationStatus(graduationStatusDao.loadOrAdd(model.getGraduationStatus()));
        entity.setGraduationInfo(conversionUtils.parseAsString(model.getGraduationInfo()));

        String address = conversionUtils.parseAsString(model.getAddress1());
        if (address == null) {
            address = conversionUtils.parseAsString(model.getAddress2());
        }
        entity.setAddress(address);

        entity.setPhoneNumber(conversionUtils.parseAsString(model.getPhoneNumber()));
        entity.setMobileNumber(null);
        entity.setEmergencyNumber(null);
        entity.setEmail(null);
    }

    private String convertBirthDate(String date) {
        if (date == null || date.equals("/  /")) {
            return null;
        }
        return date;
    }

}
