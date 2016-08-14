package ir.isar.isarapp.filter;

import com.avaje.ebean.Ebean;
import ir.isar.isarapp.entity.CodeValue;
import ir.isar.isarapp.filter.expression.BaseExpression;
import ir.isar.isarapp.filter.expression.EqualMultiValueExpression;
import ir.isar.isarapp.model.SearchModel;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Control;
import org.controlsfx.control.CheckComboBox;

/**
 *
 * @author User
 */
public class ComboFilter extends BaseFilter {

    private Class entityClass;

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    public ComboFilter(String title, String field) {
        super(title, field);
    }

    @Override
    public List<BaseExpression> getListOfExpresssions() {
        List<BaseExpression> list = new ArrayList<>();
        list.add(new EqualMultiValueExpression(this));
        return list;
    }

    public List getAllItems() {
        return Ebean.find(entityClass).orderBy("id").findList();
    }

    @Override
    public Control createValueField(SearchModel model) {
        CheckComboBox ccbValues = new CheckComboBox();

        List allItems = getAllItems();
        for (Object item : allItems) {
            CodeValue record = (CodeValue) item;
            ccbValues.getItems().add(record);
        }

        ccbValues.setPrefWidth(150.0);
        ccbValues.checkModelProperty().bindBidirectional(model.valueProperty());
        return ccbValues;
    }

}
