package ir.isar.isarapp.filter;

import ir.isar.isarapp.enm.GenderEnum;
import ir.isar.isarapp.enm.MarriageEnum;
import ir.isar.isarapp.filter.expression.BaseExpression;
import ir.isar.isarapp.filter.expression.EqualMultiValueExpression;
import ir.isar.isarapp.model.SearchModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Control;

/**
 *
 * @author User
 */
public class EnumFilter extends BaseFilter {

    private String enumClass;

    public EnumFilter(String title, String field) {
        super(title, field);
    }

    public String getEnumClass() {
        return enumClass;
    }

    public void setEnumClass(String enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public List<BaseExpression> getListOfExpresssions() {
        List<BaseExpression> list = new ArrayList<>();
        list.add(new EqualMultiValueExpression(this));
        return list;
    }

    public List getAllItems() {
        if (enumClass.equals("GenderEnum")) {
            return Arrays.asList(GenderEnum.values());
        }
        if (enumClass.equals("MarriageEnum")) {
            return Arrays.asList(MarriageEnum.values());
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Control createValueField(SearchModel model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
