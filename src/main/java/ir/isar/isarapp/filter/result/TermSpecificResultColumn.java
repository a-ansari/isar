package ir.isar.isarapp.filter.result;

/**
 *
 * @author User
 */
public class TermSpecificResultColumn extends ResultColumn {

    protected Integer termNumber;

    public TermSpecificResultColumn(String title, String field) {
        super(title, field);
    }

    public Integer getTermNumber() {
        return termNumber;
    }

    public void setTermNumber(Integer termNumber) {
        this.termNumber = termNumber;
    }

    @Override
    public String toString() {
        if (termNumber == null) {
            return title;
        }
        return termNumber + " - " + title;
    }
}
