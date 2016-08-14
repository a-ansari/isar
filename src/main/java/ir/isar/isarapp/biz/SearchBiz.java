package ir.isar.isarapp.biz;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Query;
import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.filter.expression.BaseExpression;
import ir.isar.isarapp.model.FilterAndResultModel;
import ir.isar.isarapp.model.SearchModel;
import java.util.List;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class SearchBiz {

    private FilterAndResultModel filterAndResultModel;

    public SearchBiz() {
        clearFilterAndResultModel();
    }

    public FilterAndResultModel getFilterAndResultModel() {
        return filterAndResultModel;
    }

    public final void clearFilterAndResultModel() {
        filterAndResultModel = new FilterAndResultModel();
    }

    public List<Student> search(List<SearchModel> searchModelList) {
        boolean hasTermBased = false;
        for (SearchModel searchModel : searchModelList) {
            if (searchModel.getFilter().isTermBased()) {
                hasTermBased = true;
                break;
            }
        }
        Query<Student> query = Ebean.find(Student.class).fetch("unitSummary");
        if (hasTermBased) {
            query = query.fetch("terms");
        }
        ExpressionList<Student> expressionList = query.where();
        expressionList = expressionList.conjunction();
        for (SearchModel searchModel : searchModelList) {
            if (searchModel.getFilter().isTermBased()) {
                expressionList = expressionList.conjunction();
                expressionList = expressionList.eq("terms.termNumber", searchModel.getTermNumber());
            }
            BaseExpression expression = searchModel.getExpression();
            Object value = searchModel.getValue();
            expressionList = expression.doFilter(expressionList, value);
            if (searchModel.getFilter().isTermBased()) {
                expressionList = expressionList.endJunction();
            }
        }
        expressionList = expressionList.endJunction();
        return expressionList.orderBy("studentNumber").findList();
    }

}
