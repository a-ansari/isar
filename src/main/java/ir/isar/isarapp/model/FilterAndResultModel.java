package ir.isar.isarapp.model;

import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.filter.result.ResultColumn;
import java.util.List;

/**
 *
 * @author User
 */
public class FilterAndResultModel {

    private List<SearchModel> filterList;
    private List<ResultColumn> columnList;
    private List<Student> resultList;
    private Boolean hasRowNumber;
    private String selectedStudentNumber;

    public List<SearchModel> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<SearchModel> filterList) {
        this.filterList = filterList;
    }

    public List<ResultColumn> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<ResultColumn> columnList) {
        this.columnList = columnList;
    }

    public List<Student> getResultList() {
        return resultList;
    }

    public void setResultList(List<Student> resultList) {
        this.resultList = resultList;
    }

    public Boolean getHasRowNumber() {
        return hasRowNumber;
    }

    public void setHasRowNumber(Boolean hasRowNumber) {
        this.hasRowNumber = hasRowNumber;
    }

    public String getSelectedStudentNumber() {
        return selectedStudentNumber;
    }

    public void setSelectedStudentNumber(String selectedStudentNumber) {
        this.selectedStudentNumber = selectedStudentNumber;
    }

}
