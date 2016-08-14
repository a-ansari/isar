package ir.isar.isarapp.model;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author User
 */
public class StudentModel implements Serializable {
    private String studentNumber;

    private String firstName;

    private String lastName;

    private String nationalNumber;

    private String birthCertNumber;

    private String birthDate;

    private String issuePlace;

    private String fatherName;

    private String gender;

    private String marriage;

    private String diplomaType;

    private String diplomaAverage;

    private String diplomaYear;

    private String diplomaPlace;

    private String segment;
    
    private String segmentPercent;

    private String segmentInfo;
    
    private String segmentPlace;

    private String segmentRank;

    private String totalRank;

    private String entranceYear;

    private String educationalType;

    private String educationalDegree;

    private String educationalGroup;

    private String educationalField;

    private String educationalTrend;

    private String professor;

    private String graduationStatus;

    private String graduationInfo;

    private String address;

    private String phoneNumber;

    private String mobileNumber;

    private String emergencyNumber;

    private String email;
    
    private String accountNumber;

    private List<TermModel> terms;

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(String nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    public String getBirthCertNumber() {
        return birthCertNumber;
    }

    public void setBirthCertNumber(String birthCertNumber) {
        this.birthCertNumber = birthCertNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getIssuePlace() {
        return issuePlace;
    }

    public void setIssuePlace(String issuePlace) {
        this.issuePlace = issuePlace;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getDiplomaType() {
        return diplomaType;
    }

    public void setDiplomaType(String diplomaType) {
        this.diplomaType = diplomaType;
    }

    public String getDiplomaAverage() {
        return diplomaAverage;
    }

    public void setDiplomaAverage(String diplomaAverage) {
        this.diplomaAverage = diplomaAverage;
    }

    public String getDiplomaYear() {
        return diplomaYear;
    }

    public void setDiplomaYear(String diplomaYear) {
        this.diplomaYear = diplomaYear;
    }

    public String getDiplomaPlace() {
        return diplomaPlace;
    }

    public void setDiplomaPlace(String diplomaPlace) {
        this.diplomaPlace = diplomaPlace;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getSegmentPercent() {
        return segmentPercent;
    }

    public void setSegmentPercent(String segmentPercent) {
        this.segmentPercent = segmentPercent;
    }

    public String getSegmentInfo() {
        return segmentInfo;
    }

    public void setSegmentInfo(String segmentInfo) {
        this.segmentInfo = segmentInfo;
    }

    public String getSegmentPlace() {
        return segmentPlace;
    }

    public void setSegmentPlace(String segmentPlace) {
        this.segmentPlace = segmentPlace;
    }

    public String getSegmentRank() {
        return segmentRank;
    }

    public void setSegmentRank(String segmentRank) {
        this.segmentRank = segmentRank;
    }

    public String getTotalRank() {
        return totalRank;
    }

    public void setTotalRank(String totalRank) {
        this.totalRank = totalRank;
    }

    public String getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(String entranceYear) {
        this.entranceYear = entranceYear;
    }

    public String getEducationalType() {
        return educationalType;
    }

    public void setEducationalType(String educationalType) {
        this.educationalType = educationalType;
    }

    public String getEducationalDegree() {
        return educationalDegree;
    }

    public void setEducationalDegree(String educationalDegree) {
        this.educationalDegree = educationalDegree;
    }

    public String getEducationalGroup() {
        return educationalGroup;
    }

    public void setEducationalGroup(String educationalGroup) {
        this.educationalGroup = educationalGroup;
    }

    public String getEducationalField() {
        return educationalField;
    }

    public void setEducationalField(String educationalField) {
        this.educationalField = educationalField;
    }

    public String getEducationalTrend() {
        return educationalTrend;
    }

    public void setEducationalTrend(String educationalTrend) {
        this.educationalTrend = educationalTrend;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getGraduationStatus() {
        return graduationStatus;
    }

    public void setGraduationStatus(String graduationStatus) {
        this.graduationStatus = graduationStatus;
    }

    public String getGraduationInfo() {
        return graduationInfo;
    }

    public void setGraduationInfo(String graduationInfo) {
        this.graduationInfo = graduationInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<TermModel> getTerms() {
        return terms;
    }

    public void setTerms(List<TermModel> terms) {
        this.terms = terms;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
