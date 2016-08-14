package ir.isar.isarapp.entity;

import ir.isar.isarapp.enm.GenderEnum;
import ir.isar.isarapp.enm.MarriageEnum;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 *
 * @author User
 */
@Entity
public class Student extends Base {

    @Column(nullable = false, unique = true)
    private Long studentNumber;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Long nationalNumber;

    @Column
    private Long birthCertNumber;

    @Column
    private String birthDate;

    @Column
    private String issuePlace;

    @Column
    private String fatherName;

    @Column
    private GenderEnum gender;

    @Column
    private MarriageEnum marriage;

    @Column
    private String diplomaType;

    @Column
    private Double diplomaAverage;

    @Column
    private Integer diplomaYear;

    @Column
    private String diplomaPlace;

    @ManyToOne
    private Segment segment;

    @Column
    private Integer segmentPercent;

    @Column
    private String segmentInfo;

    @Column
    private String segmentPlace;

    @Column
    private Integer segmentRank;

    @Column
    private Integer totalRank;

    @Column
    private Integer entranceYear;

    @ManyToOne
    private EducationalType educationalType;

    @ManyToOne
    private EducationalDegree educationalDegree;

    @ManyToOne
    private EducationalGroup educationalGroup;

    @ManyToOne
    private EducationalField educationalField;

    @ManyToOne
    private EducationalTrend educationalTrend;

    @ManyToOne
    private Professor professor;

    @ManyToOne
    private GraduationStatus graduationStatus;

    @Column
    private String graduationInfo;

    @Column
    private String address;

    @Column
    private String phoneNumber;

    @Column
    private String mobileNumber;

    @Column
    private String emergencyNumber;

    @Column
    private String email;

    @Column
    private String accountNumber;

    @OneToOne
    private UnitSummary unitSummary;

    @OneToMany
    private List<Term> terms;

    @Version
    private Timestamp lastUpdateTime;

    public Long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Long studentNumber) {
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

    public Long getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(Long nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    public Long getBirthCertNumber() {
        return birthCertNumber;
    }

    public void setBirthCertNumber(Long birthCertNumber) {
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

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public MarriageEnum getMarriage() {
        return marriage;
    }

    public void setMarriage(MarriageEnum marriage) {
        this.marriage = marriage;
    }

    public String getDiplomaType() {
        return diplomaType;
    }

    public void setDiplomaType(String diplomaType) {
        this.diplomaType = diplomaType;
    }

    public Double getDiplomaAverage() {
        return diplomaAverage;
    }

    public void setDiplomaAverage(Double diplomaAverage) {
        this.diplomaAverage = diplomaAverage;
    }

    public Integer getDiplomaYear() {
        return diplomaYear;
    }

    public void setDiplomaYear(Integer diplomaYear) {
        this.diplomaYear = diplomaYear;
    }

    public String getDiplomaPlace() {
        return diplomaPlace;
    }

    public void setDiplomaPlace(String diplomaPlace) {
        this.diplomaPlace = diplomaPlace;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public Integer getSegmentPercent() {
        return segmentPercent;
    }

    public void setSegmentPercent(Integer segmentPercent) {
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

    public Integer getSegmentRank() {
        return segmentRank;
    }

    public void setSegmentRank(Integer segmentRank) {
        this.segmentRank = segmentRank;
    }

    public Integer getTotalRank() {
        return totalRank;
    }

    public void setTotalRank(Integer totalRank) {
        this.totalRank = totalRank;
    }

    public Integer getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(Integer entranceYear) {
        this.entranceYear = entranceYear;
    }

    public EducationalType getEducationalType() {
        return educationalType;
    }

    public void setEducationalType(EducationalType educationalType) {
        this.educationalType = educationalType;
    }

    public EducationalDegree getEducationalDegree() {
        return educationalDegree;
    }

    public void setEducationalDegree(EducationalDegree educationalDegree) {
        this.educationalDegree = educationalDegree;
    }

    public EducationalGroup getEducationalGroup() {
        return educationalGroup;
    }

    public void setEducationalGroup(EducationalGroup educationalGroup) {
        this.educationalGroup = educationalGroup;
    }

    public EducationalField getEducationalField() {
        return educationalField;
    }

    public void setEducationalField(EducationalField educationalField) {
        this.educationalField = educationalField;
    }

    public EducationalTrend getEducationalTrend() {
        return educationalTrend;
    }

    public void setEducationalTrend(EducationalTrend educationalTrend) {
        this.educationalTrend = educationalTrend;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public GraduationStatus getGraduationStatus() {
        return graduationStatus;
    }

    public void setGraduationStatus(GraduationStatus graduationStatus) {
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

    public UnitSummary getUnitSummary() {
        return unitSummary;
    }

    public void setUnitSummary(UnitSummary unitSummary) {
        this.unitSummary = unitSummary;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

}
