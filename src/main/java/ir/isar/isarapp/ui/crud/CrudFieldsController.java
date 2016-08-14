package ir.isar.isarapp.ui.crud;

import ir.isar.isarapp.dao.BaseDao;
import ir.isar.isarapp.dao.EducationalFieldDao;
import ir.isar.isarapp.dao.EducationalGroupDao;
import ir.isar.isarapp.model.EducationalFieldModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javax.inject.Inject;

/**
 *
 * @author User
 */
public class CrudFieldsController extends BaseCrudController<EducationalFieldModel> {

    @Inject
    private EducationalGroupDao educationalGroupDao;

    @Inject
    private EducationalFieldDao educationalFieldDao;

    @Override
    protected BaseDao getDao() {
        return educationalFieldDao;
    }

    @Override
    protected EducationalFieldModel createNewModel() {
        return new EducationalFieldModel();
    }

    @Override
    protected void makeCustomNewRecordWidgets() {
        final ComboBox<String> cmbEducationalGroup = new ComboBox<>();
        cmbEducationalGroup.setPrefWidth(150.0);
        cmbEducationalGroup.valueProperty().bindBidirectional(getBaseModel().educationalGroupProperty());
        mainGridPane.add(cmbEducationalGroup, 1, 0);

        final TextField txtEducationalField = new TextField();
        txtEducationalField.setPrefWidth(150.0);
        txtEducationalField.textProperty().bindBidirectional(getBaseModel().educationalFieldProperty());
        mainGridPane.add(txtEducationalField, 1, 1);

        cmbEducationalGroup.getItems().addAll(educationalGroupDao.loadAllValues());
    }

    @Override
    protected void makeCustomColumnHandlers() {
        final ObservableList<String> groupValues = FXCollections.observableArrayList(educationalGroupDao.loadAllValues());

        tblCrud.getColumns().get(0).setCellFactory((Callback) ComboBoxTableCell.forTableColumn(groupValues));
        tblCrud.getColumns().get(1).setCellFactory((Callback) TextFieldTableCell.forTableColumn());
    }

}
