package ir.isar.isarapp.filter;

import ir.isar.isarapp.filter.expression.BaseExpression;
import ir.isar.isarapp.model.SearchModel;
import java.util.List;
import javafx.scene.control.Control;

/**
 *
 * @author User
 */
public abstract class BaseFilter {

    protected final String title;
    protected final String field;

    protected boolean termBased;

    public BaseFilter(String title, String field) {
        this.title = title;
        this.field = field;
    }

    public String getTitle() {
        return title;
    }

    public String getField() {
        return field;
    }

    public boolean isTermBased() {
        return termBased;
    }

    public void setTermBased(boolean termBased) {
        this.termBased = termBased;
    }

    @Override
    public String toString() {
        return getTitle();
    }

    public abstract List<BaseExpression> getListOfExpresssions();

    public abstract Control createValueField(SearchModel model);
}
