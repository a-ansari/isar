package ir.isar.isarapp.model;

import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.entity.Term;
import ir.isar.isarapp.entity.UnitSummary;
import ir.isar.isarapp.filter.result.ResultColumn;
import ir.isar.isarapp.util.ConversionUtils;
import java.io.Serializable;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author User
 */
public class StudentFullModel implements Serializable {

    private final StringProperty rowNumber = new SimpleStringProperty();

    public String getRowNumber() {
        return rowNumber.get();
    }

    public void setRowNumber(String value) {
        rowNumber.set(value);
    }

    public StringProperty rowNumberProperty() {
        return rowNumber;
    }

    private final StringProperty studentNumber = new SimpleStringProperty();

    public String getStudentNumber() {
        return studentNumber.get();
    }

    public void setStudentNumber(String value) {
        studentNumber.set(value);
    }

    public StringProperty studentNumberProperty() {
        return studentNumber;
    }

    private final StringProperty firstName = new SimpleStringProperty();

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String value) {
        firstName.set(value);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    private final StringProperty lastName = new SimpleStringProperty();

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String value) {
        lastName.set(value);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    private final StringProperty nationalNumber = new SimpleStringProperty();

    public String getNationalNumber() {
        return nationalNumber.get();
    }

    public void setNationalNumber(String value) {
        nationalNumber.set(value);
    }

    public StringProperty nationalNumberProperty() {
        return nationalNumber;
    }

    private final StringProperty birthCertNumber = new SimpleStringProperty();

    public String getBirthCertNumber() {
        return birthCertNumber.get();
    }

    public void setBirthCertNumber(String value) {
        birthCertNumber.set(value);
    }

    public StringProperty birthCertNumberProperty() {
        return birthCertNumber;
    }
    private final StringProperty birthDate = new SimpleStringProperty();

    public String getBirthDate() {
        return birthDate.get();
    }

    public void setBirthDate(String value) {
        birthDate.set(value);
    }

    public StringProperty birthDateProperty() {
        return birthDate;
    }
    private final StringProperty issuePlace = new SimpleStringProperty();

    public String getIssuePlace() {
        return issuePlace.get();
    }

    public void setIssuePlace(String value) {
        issuePlace.set(value);
    }

    public StringProperty issuePlaceProperty() {
        return issuePlace;
    }
    private final StringProperty fatherName = new SimpleStringProperty();

    public String getFatherName() {
        return fatherName.get();
    }

    public void setFatherName(String value) {
        fatherName.set(value);
    }

    public StringProperty fatherNameProperty() {
        return fatherName;
    }
    private final StringProperty gender = new SimpleStringProperty();

    public String getGender() {
        return gender.get();
    }

    public void setGender(String value) {
        gender.set(value);
    }

    public StringProperty genderProperty() {
        return gender;
    }
    private final StringProperty marriage = new SimpleStringProperty();

    public String getMarriage() {
        return marriage.get();
    }

    public void setMarriage(String value) {
        marriage.set(value);
    }

    public StringProperty marriageProperty() {
        return marriage;
    }
    private final StringProperty diplomaType = new SimpleStringProperty();

    public String getDiplomaType() {
        return diplomaType.get();
    }

    public void setDiplomaType(String value) {
        diplomaType.set(value);
    }

    public StringProperty diplomaTypeProperty() {
        return diplomaType;
    }
    private final StringProperty diplomaAverage = new SimpleStringProperty();

    public String getDiplomaAverage() {
        return diplomaAverage.get();
    }

    public void setDiplomaAverage(String value) {
        diplomaAverage.set(value);
    }

    public StringProperty diplomaAverageProperty() {
        return diplomaAverage;
    }
    private final StringProperty diplomaYear = new SimpleStringProperty();

    public String getDiplomaYear() {
        return diplomaYear.get();
    }

    public void setDiplomaYear(String value) {
        diplomaYear.set(value);
    }

    public StringProperty diplomaYearProperty() {
        return diplomaYear;
    }
    private final StringProperty diplomaPlace = new SimpleStringProperty();

    public String getDiplomaPlace() {
        return diplomaPlace.get();
    }

    public void setDiplomaPlace(String value) {
        diplomaPlace.set(value);
    }

    public StringProperty diplomaPlaceProperty() {
        return diplomaPlace;
    }
    private final StringProperty segment = new SimpleStringProperty();

    public String getSegment() {
        return segment.get();
    }

    public void setSegment(String value) {
        segment.set(value);
    }

    public StringProperty segmentProperty() {
        return segment;
    }
    private final StringProperty segmentPercent = new SimpleStringProperty();

    public String getSegmentPercent() {
        return segmentPercent.get();
    }

    public void setSegmentPercent(String value) {
        segmentPercent.set(value);
    }

    public StringProperty segmentPercentProperty() {
        return segmentPercent;
    }
    private final StringProperty segmentInfo = new SimpleStringProperty();

    public String getSegmentInfo() {
        return segmentInfo.get();
    }

    public void setSegmentInfo(String value) {
        segmentInfo.set(value);
    }

    public StringProperty segmentInfoProperty() {
        return segmentInfo;
    }
    private final StringProperty segmentPlace = new SimpleStringProperty();

    public String getSegmentPlace() {
        return segmentPlace.get();
    }

    public void setSegmentPlace(String value) {
        segmentPlace.set(value);
    }

    public StringProperty segmentPlaceProperty() {
        return segmentPlace;
    }
    private final StringProperty segmentRank = new SimpleStringProperty();

    public String getSegmentRank() {
        return segmentRank.get();
    }

    public void setSegmentRank(String value) {
        segmentRank.set(value);
    }

    public StringProperty segmentRankProperty() {
        return segmentRank;
    }
    private final StringProperty totalRank = new SimpleStringProperty();

    public String getTotalRank() {
        return totalRank.get();
    }

    public void setTotalRank(String value) {
        totalRank.set(value);
    }

    public StringProperty totalRankProperty() {
        return totalRank;
    }

    private final StringProperty entranceYear = new SimpleStringProperty();

    public String getEntranceYear() {
        return entranceYear.get();
    }

    public void setEntranceYear(String value) {
        entranceYear.set(value);
    }

    public StringProperty entranceYearProperty() {
        return entranceYear;
    }
    private final StringProperty educationalType = new SimpleStringProperty();

    public String getEducationalType() {
        return educationalType.get();
    }

    public void setEducationalType(String value) {
        educationalType.set(value);
    }

    public StringProperty educationalTypeProperty() {
        return educationalType;
    }
    private final StringProperty educationalDegree = new SimpleStringProperty();

    public String getEducationalDegree() {
        return educationalDegree.get();
    }

    public void setEducationalDegree(String value) {
        educationalDegree.set(value);
    }

    public StringProperty educationalDegreeProperty() {
        return educationalDegree;
    }
    private final StringProperty educationalGroup = new SimpleStringProperty();

    public String getEducationalGroup() {
        return educationalGroup.get();
    }

    public void setEducationalGroup(String value) {
        educationalGroup.set(value);
    }

    public StringProperty educationalGroupProperty() {
        return educationalGroup;
    }
    private final StringProperty educationalField = new SimpleStringProperty();

    public String getEducationalField() {
        return educationalField.get();
    }

    public void setEducationalField(String value) {
        educationalField.set(value);
    }

    public StringProperty educationalFieldProperty() {
        return educationalField;
    }
    private final StringProperty educationalTrend = new SimpleStringProperty();

    public String getEducationalTrend() {
        return educationalTrend.get();
    }

    public void setEducationalTrend(String value) {
        educationalTrend.set(value);
    }

    public StringProperty educationalTrendProperty() {
        return educationalTrend;
    }
    private final StringProperty professor = new SimpleStringProperty();

    public String getProfessor() {
        return professor.get();
    }

    public void setProfessor(String value) {
        professor.set(value);
    }

    public StringProperty professorProperty() {
        return professor;
    }

    private final StringProperty graduationStatus = new SimpleStringProperty();

    public String getGraduationStatus() {
        return graduationStatus.get();
    }

    public void setGraduationStatus(String value) {
        graduationStatus.set(value);
    }

    public StringProperty graduationStatusProperty() {
        return graduationStatus;
    }
    private final StringProperty graduationInfo = new SimpleStringProperty();

    public String getGraduationInfo() {
        return graduationInfo.get();
    }

    public void setGraduationInfo(String value) {
        graduationInfo.set(value);
    }

    public StringProperty graduationInfoProperty() {
        return graduationInfo;
    }
    private final StringProperty address = new SimpleStringProperty();

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String value) {
        address.set(value);
    }

    public StringProperty addressProperty() {
        return address;
    }
    private final StringProperty phoneNumber = new SimpleStringProperty();

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String value) {
        phoneNumber.set(value);
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }
    private final StringProperty mobileNumber = new SimpleStringProperty();

    public String getMobileNumber() {
        return mobileNumber.get();
    }

    public void setMobileNumber(String value) {
        mobileNumber.set(value);
    }

    public StringProperty mobileNumberProperty() {
        return mobileNumber;
    }
    private final StringProperty emergencyNumber = new SimpleStringProperty();

    public String getEmergencyNumber() {
        return emergencyNumber.get();
    }

    public void setEmergencyNumber(String value) {
        emergencyNumber.set(value);
    }

    public StringProperty emergencyNumberProperty() {
        return emergencyNumber;
    }
    private final StringProperty email = new SimpleStringProperty();

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String value) {
        email.set(value);
    }

    public StringProperty emailProperty() {
        return email;
    }
    private final StringProperty accountNumber = new SimpleStringProperty();

    public String getAccountNumber() {
        return accountNumber.get();
    }

    public void setAccountNumber(String value) {
        accountNumber.set(value);
    }

    public StringProperty accountNumberProperty() {
        return accountNumber;
    }

    ////////////////////////////////////////////////////////
    private final StringProperty totalTaken = new SimpleStringProperty();

    public String getTotalTaken() {
        return totalTaken.get();
    }

    public void setTotalTaken(String value) {
        totalTaken.set(value);
    }

    public StringProperty totalTakenProperty() {
        return totalTaken;
    }
    private final StringProperty totalPassed = new SimpleStringProperty();

    public String getTotalPassed() {
        return totalPassed.get();
    }

    public void setTotalPassed(String value) {
        totalPassed.set(value);
    }

    public StringProperty totalPassedProperty() {
        return totalPassed;
    }
    private final StringProperty totalAverage = new SimpleStringProperty();

    public String getTotalAverage() {
        return totalAverage.get();
    }

    public void setTotalAverage(String value) {
        totalAverage.set(value);
    }

    public StringProperty totalAverageProperty() {
        return totalAverage;
    }
    private final StringProperty totalConditional = new SimpleStringProperty();

    public String getTotalConditional() {
        return totalConditional.get();
    }

    public void setTotalConditional(String value) {
        totalConditional.set(value);
    }

    public StringProperty totalConditionalProperty() {
        return totalConditional;
    }

    ////////////////////////////////////////////////////////
    private final StringProperty lastTermNumber = new SimpleStringProperty();

    public String getLastTermNumber() {
        return lastTermNumber.get();
    }

    public void setLastTermNumber(String value) {
        lastTermNumber.set(value);
    }

    public StringProperty lastTermNumberProperty() {
        return lastTermNumber;
    }
    private final StringProperty lastTermAverage = new SimpleStringProperty();

    public String getLastTermAverage() {
        return lastTermAverage.get();
    }

    public void setLastTermAverage(String value) {
        lastTermAverage.set(value);
    }

    public StringProperty lastTermAverageProperty() {
        return lastTermAverage;
    }
    private final StringProperty lastTermTakenUnits = new SimpleStringProperty();

    public String getLastTermTakenUnits() {
        return lastTermTakenUnits.get();
    }

    public void setLastTermTakenUnits(String value) {
        lastTermTakenUnits.set(value);
    }

    public StringProperty lastTermTakenUnitsProperty() {
        return lastTermTakenUnits;
    }
    private final StringProperty lastTermPassedUnits = new SimpleStringProperty();

    public String getLastTermPassedUnits() {
        return lastTermPassedUnits.get();
    }

    public void setLastTermPassedUnits(String value) {
        lastTermPassedUnits.set(value);
    }

    public StringProperty lastTermPassedUnitsProperty() {
        return lastTermPassedUnits;
    }

    public void fromEntity(Student student, UnitSummary unitSummary, Term lastTerm, List<ResultColumn> columns, ConversionUtils conversionUtils) {
        setStudentNumber(conversionUtils.toString(student.getStudentNumber()));
        setFirstName(conversionUtils.toString(student.getFirstName()));
        setLastName(conversionUtils.toString(student.getLastName()));
        setNationalNumber(conversionUtils.nationalCode(student.getNationalNumber()));
        setBirthCertNumber(conversionUtils.nationalCode(student.getBirthCertNumber()));
        setBirthDate(conversionUtils.toString(student.getBirthDate()));
        setIssuePlace(conversionUtils.toString(student.getIssuePlace()));
        setFatherName(conversionUtils.toString(student.getFatherName()));
        setGender(conversionUtils.toString(student.getGender()));
        setMarriage(conversionUtils.toString(student.getMarriage()));
        setDiplomaType(conversionUtils.toString(student.getDiplomaType()));
        setDiplomaAverage(conversionUtils.toString(student.getDiplomaAverage()));
        setDiplomaYear(conversionUtils.toString(student.getDiplomaYear()));
        setDiplomaPlace(conversionUtils.toString(student.getDiplomaPlace()));
        setSegment(conversionUtils.toString(student.getSegment()));
        setSegmentPercent(conversionUtils.toString(student.getSegmentPercent()));
        setSegmentInfo(conversionUtils.toString(student.getSegmentInfo()));
        setSegmentPlace(conversionUtils.toString(student.getSegmentPlace()));
        setSegmentRank(conversionUtils.toString(student.getSegmentRank()));
        setTotalRank(conversionUtils.toString(student.getTotalRank()));
        setEntranceYear(conversionUtils.toString(student.getEntranceYear()));
        setEducationalType(conversionUtils.toString(student.getEducationalType()));
        setEducationalDegree(conversionUtils.toString(student.getEducationalDegree()));
        setEducationalGroup(conversionUtils.toString(student.getEducationalGroup()));
        setEducationalField(conversionUtils.toString(student.getEducationalField()));
        setEducationalTrend(conversionUtils.toString(student.getEducationalTrend()));
        setProfessor(conversionUtils.toString(student.getProfessor()));
        setGraduationStatus(conversionUtils.toString(student.getGraduationStatus()));
        setGraduationInfo(conversionUtils.toString(student.getGraduationInfo()));
        setAddress(conversionUtils.toString(student.getAddress()));
        setPhoneNumber(conversionUtils.toString(student.getPhoneNumber()));
        setMobileNumber(conversionUtils.toString(student.getMobileNumber()));
        setEmergencyNumber(conversionUtils.toString(student.getEmergencyNumber()));
        setEmail(conversionUtils.toString(student.getEmail()));
        setAccountNumber(conversionUtils.toString(student.getAccountNumber()));

        setTotalTaken(conversionUtils.toString(unitSummary.getTotalTaken()));
        setTotalPassed(conversionUtils.toString(unitSummary.getTotalPassed()));
        setTotalAverage(conversionUtils.toString(unitSummary.getTotalAverage()));
        setTotalConditional(conversionUtils.toString(unitSummary.getTotalConditional()));

        setLastTermNumber(conversionUtils.toString(lastTerm.getTermNumber()));
        setLastTermAverage(conversionUtils.toString(lastTerm.getTermAverage()));
        setLastTermTakenUnits(conversionUtils.toString(lastTerm.getTakenUnits()));
        setLastTermPassedUnits(conversionUtils.toString(lastTerm.getPassedUnits()));
    }
}
