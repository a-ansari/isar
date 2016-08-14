package ir.isar.isarapp.model;

import ir.isar.isarapp.filter.expression.BaseExpression;
import ir.isar.isarapp.filter.BaseFilter;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import org.controlsfx.control.CheckModel;

/**
 * @author User
 */
public class SearchModel {

    private final ObjectProperty<BaseFilter> filter = new SimpleObjectProperty<>();
    private final ObjectProperty<BaseExpression> expression = new SimpleObjectProperty<>();
    private Property value = null;
    private final StringProperty termNumber = new SimpleStringProperty();

    public BaseFilter getFilter() {
        return filter.get();
    }

    public void setFilter(BaseFilter value) {
        filter.set(value);
    }

    public ObjectProperty filterProperty() {
        return filter;
    }

    public BaseExpression getExpression() {
        return expression.get();
    }

    public void setExpression(BaseExpression value) {
        expression.set(value);
    }

    public ObjectProperty expressionProperty() {
        return expression;
    }

    public Object getValue() {
        return value.getValue();
    }

    public void setValue(Object v) {
        value.setValue(v);
    }

    public Property valueProperty() {
        return value;
    }

    public void setValueProperty(Property valueProperty) {
        this.value = valueProperty;
    }

    public String getTermNumber() {
        return termNumber.get();
    }

    public void setTermNumber(String value) {
        termNumber.set(value);
    }

    public StringProperty termNumberProperty() {
        return termNumber;
    }

    public boolean hasError() {
        return getFilter() == null || getExpression() == null || getValue() == null;
    }

    @Override
    public SearchModel clone() {
        SearchModel model = new SearchModel();
        model.setFilter(getFilter());
        model.setExpression(getExpression());
        model.setTermNumber(getTermNumber());

        Object v = getValue();
        if (v instanceof CheckModel) {
            ObservableList selectedItems = ((CheckModel) v).getCheckedItems();
            List list = new ArrayList();
            list.addAll(selectedItems);
            v = list;
        }
        if (v instanceof String) {
            v = convertYeAndKe((String) v);
        }
        SimpleObjectProperty prop = new SimpleObjectProperty();
        prop.setValue(v);
        model.setValueProperty(prop);
        return model;
    }

    public String convertYeAndKe(String txt) {
        return txt.replaceAll("\u0643","\u06A9").replaceAll("\u064A","\u06CC");
    }

    public void clear() {
        setFilter(null);
//        setExpression(null);
//        setValue(null);
    }
}
