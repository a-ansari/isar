package ir.isar.isarapp.filter;

import ir.isar.isarapp.filter.expression.EqualExpression;
import ir.isar.isarapp.filter.expression.BaseExpression;
import ir.isar.isarapp.filter.expression.EndsWithExpression;
import ir.isar.isarapp.filter.expression.LikeExpression;
import ir.isar.isarapp.filter.expression.StartsWithExpression;
import ir.isar.isarapp.model.SearchModel;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

/**
 *
 * @author User
 */
public class StringFilter extends BaseFilter {

    public StringFilter(String title, String field) {
        super(title, field);
    }

    @Override
    public List<BaseExpression> getListOfExpresssions() {
        List<BaseExpression> list = new ArrayList<>();
        list.add(new EqualExpression(this));
        list.add(new StartsWithExpression(this));
        list.add(new EndsWithExpression(this));
        list.add(new LikeExpression(this));
        return list;
    }

    @Override
    public Control createValueField(SearchModel model) {
        TextField txtValue = new TextField();
        txtValue.textProperty().bindBidirectional(model.valueProperty());
        return txtValue;
    }

}
