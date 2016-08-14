package ir.isar.isarapp.filter.expression;

import com.avaje.ebean.ExpressionList;
import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.filter.BaseFilter;

/**
 *
 * @author User
 */
public class GreaterThanExpression extends BaseExpression {

    public GreaterThanExpression(BaseFilter filter) {
        super(filter);
    }

    @Override
    public ExpressionList<Student> doFilter(ExpressionList<Student> expressionList, Object value) {
        return expressionList.ge(filter.getField(), value);
    }

}
