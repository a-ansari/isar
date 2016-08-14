package ir.isar.isarapp.ui.crud;

import ir.isar.isarapp.dao.BaseDao;
import ir.isar.isarapp.dao.EducationalFieldDao;
import ir.isar.isarapp.dao.EducationalGroupDao;
import ir.isar.isarapp.dao.EducationalTrendDao;
import ir.isar.isarapp.model.EducationalTrendModel;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javax.inject.Inject;

/**
 *
 * @author User
 */
public class CrudTrendsController extends BaseCrudController<EducationalTrendModel> {

    @Inject
    private EducationalGroupDao educationalGroupDao;

    @Inject
    private EducationalFieldDao educationalFieldDao;

    @Inject
    private EducationalTrendDao educationalTrendDao;

    @Override
    protected BaseDao getDao() {
        return educationalTrendDao;
    }

    @Override
    protected EducationalTrendModel createNewModel() {
        return new EducationalTrendModel();
    }

    @Override
    protected void makeCustomNewRecordWidgets() {
        final ComboBox<String> cmbEducationalGroup = new ComboBox<>();
        cmbEducationalGroup.setPrefWidth(150.0);
        cmbEducationalGroup.valueProperty().bindBidirectional(getBaseModel().educationalGroupProperty());
        mainGridPane.add(cmbEducationalGroup, 1, 0);

        final ComboBox<String> cmbEducationalField = new ComboBox<>();
        cmbEducationalField.setPrefWidth(150.0);
        cmbEducationalField.valueProperty().bindBidirectional(getBaseModel().educationalFieldProperty());
        mainGridPane.add(cmbEducationalField, 1, 1);

        final TextField txtEducationalTrend = new TextField();
        txtEducationalTrend.setPrefWidth(150.0);
        txtEducationalTrend.textProperty().bindBidirectional(getBaseModel().educationalTrendProperty());
        mainGridPane.add(txtEducationalTrend, 1, 2);

        cmbEducationalGroup.getItems().addAll(educationalGroupDao.loadAllValues());
        cmbEducationalGroup.valueProperty().addListener(listener -> {
            cmbEducationalField.getItems().clear();
            cmbEducationalField.setValue(null);

            List<String> allFields = educationalFieldDao.loadAllValues(cmbEducationalGroup.getValue());
            cmbEducationalField.getItems().addAll(allFields);
        });
    }

    @Override
    protected void makeCustomColumnHandlers() {
        final ObservableList<String> groupValues = FXCollections.observableArrayList(educationalGroupDao.loadAllValues());
        final ObservableList<String> fieldValues = FXCollections.observableArrayList();

        tblCrud.getColumns().get(0).setCellFactory((Callback) ComboBoxTableCell.forTableColumn(groupValues));
        tblCrud.getColumns().get(1).setCellFactory((Callback) ComboBoxTableCell.forTableColumn(fieldValues));
        tblCrud.getColumns().get(2).setCellFactory((Callback) TextFieldTableCell.forTableColumn());

        tblCrud.getColumns().get(0).addEventHandler(TableColumn.editCommitEvent(), event -> {
            fieldValues.clear();
            ((EducationalTrendModel) event.getRowValue()).setEducationalField(null);
        });
        tblCrud.getColumns().get(1).addEventHandler(TableColumn.editStartEvent(), event -> {
            String groupName = ((EducationalTrendModel) event.getRowValue()).getEducationalGroup();
            fieldValues.clear();
            fieldValues.addAll(educationalFieldDao.loadAllValues(groupName));
        });
    }
}
