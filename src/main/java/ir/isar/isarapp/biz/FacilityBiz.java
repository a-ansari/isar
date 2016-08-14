package ir.isar.isarapp.biz;

import ir.isar.isarapp.dao.ConfigDao;
import ir.isar.isarapp.enm.MarriageEnum;
import ir.isar.isarapp.entity.*;
import ir.isar.isarapp.model.FacilityModel;
import ir.isar.isarapp.util.ConversionUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author User
 */
@Singleton
public class FacilityBiz {

    @Inject
    private ConfigDao configDao;
    @Inject
    private StudentBiz studentBiz;
    @Inject
    private ConversionUtils conversionUtils;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public FacilityModel calculateAllPayments(long studentNumber) {
        FacilityModel model = new FacilityModel();
        try {
            Student student = studentBiz.loadStudent(studentNumber);
            model.setHadieVorood(conversionUtils.toString(hadieVorood(student)));
            model.setKomakHazine(conversionUtils.toString(komakHazine(student)));
            model.setKomakShahrie(conversionUtils.toString(komakShahrie(student)));
            model.setTashvighErtegha(conversionUtils.toString(tashvighErtegha(student)));
            model.setHadieMomtazin(conversionUtils.toString(hadieMomtazin(student)));
            model.setHazineEskan(conversionUtils.toString(hazineEskan(student)));
            model.setHazinePayanName(conversionUtils.toString(hazinePayanName(student)));
            model.setHadieFaregholTahsilan(conversionUtils.toString(hadieFaregholTahsilan(student)));
        } catch (Exception ex) {
            logger.error("", ex);
        }
        return model;
    }

    public Long calculatePaymentAmount(long studentNumber, PaymentType paymentType) {
        Student student = studentBiz.loadStudent(studentNumber);
        if (paymentType.equals(PaymentType.HadieVorood)) {
            return hadieVorood(student);
        }
        if (paymentType.equals(PaymentType.KomakHazine)) {
            return komakHazine(student);
        }
        if (paymentType.equals(PaymentType.KomakShahrie)) {
            return komakShahrie(student);
        }
        if (paymentType.equals(PaymentType.TashvighErtegha)) {
            return tashvighErtegha(student);
        }
        if (paymentType.equals(PaymentType.HadieMomtazin)) {
            return hadieMomtazin(student);
        }
        if (paymentType.equals(PaymentType.HazineEskan)) {
            return hazineEskan(student);
        }
        if (paymentType.equals(PaymentType.HazinePayanName)) {
            return hazinePayanName(student);
        }
        if (paymentType.equals(PaymentType.HadieFaregholTahsilan)) {
            return hadieFaregholTahsilan(student);
        }
        throw new IllegalArgumentException("Not recognized payment type: " + paymentType.getValue());
    }

    boolean allowedTerms(Student student) {
        EducationalDegree degree = student.getEducationalDegree();
        if (degree.equals(EducationalDegree.Karshenasi)) {
            return student.getUnitSummary().getTotalTerms() <= 8;
        }
        if (degree.equals(EducationalDegree.Arshad)) {
            return student.getUnitSummary().getTotalTerms() <= 4;
        }
        if (degree.equals(EducationalDegree.Doctora)) {
            return student.getUnitSummary().getTotalTerms() <= 8;
        }
        return false;
    }

    int segmentGroupType(Student student) {
        Segment segment = student.getSegment();
        if (segment.equals(Segment.Shahed) || segment.equals(Segment.HamsarShahid)) {
            return 1;
        }
        if (segment.equals(Segment.Azade) || segment.equals(Segment.HamsarAzade) || segment.equals(Segment.FarzandAzade)) {
            return 2;
        }
        if (segment.equals(Segment.Janbaz) || segment.equals(Segment.HamsarJanbaz) || segment.equals(Segment.FarzandJanbaz)) {
            if (student.getSegmentPercent() == null) {
                return -1;
            }
            int segmentPercent = student.getSegmentPercent();
            if (segmentPercent >= 70) {
                return 1;
            }
            if (segmentPercent >= 50) {
                return 2;
            }
            if (segment.equals(Segment.Janbaz) && segmentPercent >= 25) {
                return 2;
            }
            if (segmentPercent >= 25) {
                return 3;
            }
        }
        return -1;
    }

    public long hadieVorood(Student student) {
//        String universityType = configDao.getValue(Config.UNIVERSITY_TYPE);
        String universityType = null;
        EducationalDegree degree = student.getEducationalDegree();
        if (universityType == null || universityType.equals(Config.UNIVERSITY_TYPE_PUBLIC)) {
            if (degree.equals(EducationalDegree.Karshenasi)) {
                return 950_000;
            }
            if (degree.equals(EducationalDegree.Arshad)) {
                return 4_000_000;
            }
            if (degree.equals(EducationalDegree.Doctora)) {
                return 6_500_000;
            }
            return -1;
        }
        if (universityType.equals(Config.UNIVERSITY_TYPE_PRIVATE)) {
            if (degree.equals(EducationalDegree.Karshenasi)) {
                return 750_000;
            }
            if (degree.equals(EducationalDegree.Arshad)) {
                return 3_000_000;
            }
            if (degree.equals(EducationalDegree.Doctora)) {
                return 0;
            }
        }
        return -1;
    }

    public long komakHazine(Student student) {
        int groupType = segmentGroupType(student);
        if (groupType == -1) {
            return -1;
        }
        if (!allowedTerms(student)) {
            return 0;
        }
        EducationalDegree degree = student.getEducationalDegree();
        if (degree.equals(EducationalDegree.Karshenasi)) {
            if (student.getMarriage() == MarriageEnum.Unmarried) {
                switch (groupType) {
                    case 1:
                        return 2_500_000;
                    case 2:
                        return 1_900_000;
                    case 3:
                        return 1_000_000;
                }
            }
            if (student.getMarriage() == MarriageEnum.Married) {
                switch (groupType) {
                    case 1:
                        return 3_750_000;
                    case 2:
                        return 2_850_000;
                    case 3:
                        return 1_500_000;
                }
            }
        }
        if (degree.equals(EducationalDegree.Arshad)) {
            if (student.getMarriage() == MarriageEnum.Unmarried) {
                switch (groupType) {
                    case 1:
                        return 3_600_000;
                    case 2:
                        return 2_700_000;
                    case 3:
                        return 1_500_000;
                }
            }
            if (student.getMarriage() == MarriageEnum.Married) {
                switch (groupType) {
                    case 1:
                        return 5_400_000;
                    case 2:
                        return 4_050_000;
                    case 3:
                        return 2_250_000;
                }
            }
        }
        if (degree.equals(EducationalDegree.Doctora)) {
            if (student.getMarriage() == MarriageEnum.Unmarried) {
                switch (groupType) {
                    case 1:
                        return 4_200_000;
                    case 2:
                        return 3_150_000;
                    case 3:
                        return 1_800_000;
                }
            }
            if (student.getMarriage() == MarriageEnum.Married) {
                switch (groupType) {
                    case 1:
                        return 6_300_000;
                    case 2:
                        return 4_750_000;
                    case 3:
                        return 2_700_000;
                }
            }
        }
        return -1;
    }

    public long komakShahrie(Student student) {
        int groupType = segmentGroupType(student);
        if (groupType == -1) {
            return -1;
        }
        if (!allowedTerms(student)) {
            return 0;
        }
        EducationalDegree degree = student.getEducationalDegree();
        if (degree.equals(EducationalDegree.Karshenasi)) {
            if (student.getMarriage() == MarriageEnum.Unmarried) {
                switch (groupType) {
                    case 1:
                        return 65;
                    case 2:
                        return 60;
                    case 3:
                        return 25;
                }
            }
            if (student.getMarriage() == MarriageEnum.Married) {
                switch (groupType) {
                    case 1:
                        return 75;
                    case 2:
                        return 70;
                    case 3:
                        return 35;
                }
            }
        }
        if (degree.equals(EducationalDegree.Arshad)) {
            if (student.getMarriage() == MarriageEnum.Unmarried) {
                switch (groupType) {
                    case 1:
                        return 70;
                    case 2:
                        return 65;
                    case 3:
                        return 30;
                }
            }
            if (student.getMarriage() == MarriageEnum.Married) {
                switch (groupType) {
                    case 1:
                        return 80;
                    case 2:
                        return 75;
                    case 3:
                        return 40;
                }
            }
        }
        if (degree.equals(EducationalDegree.Doctora)) {
            if (student.getMarriage() == MarriageEnum.Unmarried) {
                switch (groupType) {
                    case 1:
                        return 75;
                    case 2:
                        return 70;
                    case 3:
                        return 35;
                }
            }
            if (student.getMarriage() == MarriageEnum.Married) {
                switch (groupType) {
                    case 1:
                        return 85;
                    case 2:
                        return 80;
                    case 3:
                        return 45;
                }
            }
        }
        return -1;
    }

    public long tashvighErtegha(Student student) {
        int groupType = segmentGroupType(student);
        if (groupType == -1) {
            return -1;
        }
        if (!allowedTerms(student)) {
            return 0;
        }
        if (student.getUnitSummary().getTotalAverage() < 16) {
            return 0;
        }
        switch (groupType) {
            case 1:
                return 100;
            case 2:
                return 75;
            case 3:
                return 50;
        }
        return -1;
    }

    public long hadieMomtazin(Student student) {
        EducationalDegree degree = student.getEducationalDegree();
        if (degree.equals(EducationalDegree.Karshenasi) && student.getUnitSummary().getTotalAverage() >= 16) {
            return 1_400_000;
        }
        if (degree.equals(EducationalDegree.Arshad) && student.getUnitSummary().getTotalAverage() >= 16) {
            return 2_100_000;
        }
        if (degree.equals(EducationalDegree.Doctora) && student.getUnitSummary().getTotalAverage() >= 15) {
            return 4_300_000;
        }
        return 0;
    }

    public long hazineEskan(Student student) {
        Segment segment = student.getSegment();
        if (segment.equals(Segment.Shahed)
                || (student.getSegmentPercent() >= 70 && (segment.equals(Segment.FarzandJanbaz) || segment.equals(Segment.Janbaz)))) {
            if (student.getMarriage() == MarriageEnum.Unmarried) {
                return 250_000;
            }
            if (student.getMarriage() == MarriageEnum.Married) {
                return 500_000;
            }
        }
        return 0;
    }

    public long hazinePayanName(Student student) {
        return 0;
    }

    public long hadieFaregholTahsilan(Student student) {
        return 0;
    }
}
