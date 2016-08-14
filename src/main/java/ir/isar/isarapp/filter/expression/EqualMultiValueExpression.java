package ir.isar.isarapp.filter.expression;

import com.avaje.ebean.ExpressionList;
import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.filter.BaseFilter;
import java.util.List;

/**
 *
 * @author User
 */
public class EqualMultiValueExpression extends BaseExpression {

    public EqualMultiValueExpression(BaseFilter filter) {
        super(filter);
    }

    @Override
    public ExpressionList<Student> doFilter(ExpressionList<Student> expressionList, Object value) {
        expressionList = expressionList.disjunction();
        for (Object item : (List) value) {
            expressionList = expressionList.eq(filter.getField(), item);
        }
        expressionList = expressionList.endJunction();
        return expressionList;
    }

}
