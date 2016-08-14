package ir.isar.isarapp.filter.expression;

import com.avaje.ebean.ExpressionList;
import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.filter.BaseFilter;

/**
 *
 * @author User
 */
public class EndsWithExpression extends BaseExpression {

    public EndsWithExpression(BaseFilter filter) {
        super(filter);
    }

    @Override
    public ExpressionList<Student> doFilter(ExpressionList<Student> expressionList, Object value) {
        return expressionList.endsWith(filter.getField(), (String) value);
    }

}
