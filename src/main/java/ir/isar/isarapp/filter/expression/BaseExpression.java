package ir.isar.isarapp.filter.expression;

import com.avaje.ebean.ExpressionList;
import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.filter.BaseFilter;
import java.util.ResourceBundle;

/**
 *
 * @author User
 */
public abstract class BaseExpression {

    protected final ResourceBundle baseMessages = ResourceBundle.getBundle(BaseExpression.class.getName());

    protected final String title;

    protected final BaseFilter filter;

    public BaseExpression(BaseFilter filter) {
        this.filter = filter;
        this.title = baseMessages.getString("isar.BaseExpression." + getClass().getSimpleName());
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return getTitle();
    }

    public abstract ExpressionList<Student> doFilter(ExpressionList<Student> expressionList, Object value);

}
