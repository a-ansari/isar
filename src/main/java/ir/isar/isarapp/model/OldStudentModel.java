package ir.isar.isarapp.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author User
 */
public class OldStudentModel implements Serializable {

    @ColumnOrder(order = 0)
    private String studentNumber;

    @ColumnOrder(order = 1)
    private String firstName;

    @ColumnOrder(order = 2)
    private String lastName;

    @ColumnOrder(order = 3)
    private String birthCertNumber;

    @ColumnOrder(order = 4)
    private String birthDate;

    @ColumnOrder(order = 5)
    private String issuePlace;

    @ColumnOrder(order = 6)
    private String fatherName;

    @ColumnOrder(order = 7)
    private String gender;

    @ColumnOrder(order = 8)
    private String marriage;

    @ColumnOrder(order = 9)
    private String diplomaType;

    @ColumnOrder(order = 10)
    private String diplomaAverage;

    @ColumnOrder(order = 11)
    private String diplomaYear;

    @ColumnOrder(order = 12)
    private String diplomaPlace;

    @ColumnOrder(order = 13)
    private String avgT;
    /**
     * TODO!!!*
     */

    @ColumnOrder(order = 14)
    private String segmentRank;

    @ColumnOrder(order = 15)
    private String totalRank;

    @ColumnOrder(order = 16)
    private String segment;

    @ColumnOrder(order = 17)
    private String segmentInfo;

    @ColumnOrder(order = 18)
    private String entranceYear;

    @ColumnOrder(order = 19)
    private String educationalType;

    @ColumnOrder(order = 20)
    private String educationalDegree;

    @ColumnOrder(order = 21)
    private String educationalGroup;

    @ColumnOrder(order = 22)
    private String educationalField;

    @ColumnOrder(order = 23)
    private String educationalTrend;

    @ColumnOrder(order = 24)
    private String professor;

    @ColumnOrder(order = 25)
    private String graduationStatus;

    @ColumnOrder(order = 26)
    private String graduationInfo;

    @ColumnOrder(order = 27)
    private String address1;

    @ColumnOrder(order = 28)
    private String address2;

    @ColumnOrder(order = 29)
    private String phoneNumber;

    @ColumnOrder(order = 30)
    private String mashrootiSeri;

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

    public String getAvgT() {
        return avgT;
    }

    public void setAvgT(String avgT) {
        this.avgT = avgT;
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

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getSegmentInfo() {
        return segmentInfo;
    }

    public void setSegmentInfo(String segmentInfo) {
        this.segmentInfo = segmentInfo;
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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMashrootiSeri() {
        return mashrootiSeri;
    }

    public void setMashrootiSeri(String mashrootiSeri) {
        this.mashrootiSeri = mashrootiSeri;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
