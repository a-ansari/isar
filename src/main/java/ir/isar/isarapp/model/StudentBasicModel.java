package ir.isar.isarapp.model;

import ir.isar.isarapp.entity.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author User
 */
public class StudentBasicModel {

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

    public void fromEntity(Student entity) {
        try {
            setStudentNumber(entity.getStudentNumber().toString());
        } catch (NullPointerException ex) {
        }
        try {
            setFirstName(entity.getFirstName());
        } catch (NullPointerException ex) {
        }
        try {
            setLastName(entity.getLastName());
        } catch (NullPointerException ex) {
        }
        try {
            setEntranceYear(entity.getEntranceYear().toString());
        } catch (NullPointerException ex) {
        }
        try {
            setEducationalDegree(entity.getEducationalDegree().getValue());
        } catch (NullPointerException ex) {
        }
        try {
            setEducationalGroup(entity.getEducationalGroup().getValue());
        } catch (NullPointerException ex) {
        }
        try {
            setEducationalField(entity.getEducationalField().getValue());
        } catch (NullPointerException ex) {
        }
        try {
            setEducationalTrend(entity.getEducationalTrend().getValue());
        } catch (NullPointerException ex) {
        }
        try {
            setSegment(entity.getSegment().getValue());
        } catch (NullPointerException ex) {
        }
        try {
            setGraduationStatus(entity.getGraduationStatus().getValue());
        } catch (NullPointerException ex) {
        }
    }
}
