package ir.isar.isarapp.filter.result;

/**
 *
 * @author User
 */
public class ResultColumn {

    protected final String title;
    protected final String field;

    public ResultColumn(String title, String field) {
        this.title = title;
        this.field = field;
    }

    public String getTitle() {
        return title;
    }

    public String getField() {
        return field;
    }

    @Override
    public String toString() {
        return getTitle();
    }

}
