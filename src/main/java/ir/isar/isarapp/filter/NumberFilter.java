package ir.isar.isarapp.filter;

import ir.isar.isarapp.filter.expression.BaseExpression;
import ir.isar.isarapp.filter.expression.EqualExpression;
import ir.isar.isarapp.filter.expression.GreaterThanExpression;
import ir.isar.isarapp.filter.expression.LessThanExpression;
import ir.isar.isarapp.model.SearchModel;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

/**
 *
 * @author User
 */
public class NumberFilter extends BaseFilter {

    public NumberFilter(String title, String field) {
        super(title, field);
    }

    @Override
    public List<BaseExpression> getListOfExpresssions() {
        List<BaseExpression> list = new ArrayList<>();
        list.add(new EqualExpression(this));
        list.add(new GreaterThanExpression(this));
        list.add(new LessThanExpression(this));
        return list;
    }

    @Override
    public Control createValueField(SearchModel model) {
        TextField txtValue = new TextField();
        txtValue.textProperty().bindBidirectional(model.valueProperty());
        return txtValue;
    }

}
