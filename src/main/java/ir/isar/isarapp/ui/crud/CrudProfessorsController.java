package ir.isar.isarapp.ui.crud;

import ir.isar.isarapp.dao.BaseDao;
import ir.isar.isarapp.dao.EducationalFieldDao;
import ir.isar.isarapp.dao.EducationalGroupDao;
import ir.isar.isarapp.dao.ProfessorDao;
import ir.isar.isarapp.model.ProfessorModel;
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
public class CrudProfessorsController extends BaseCrudController<ProfessorModel> {

    @Inject
    private ProfessorDao professorDao;

    @Inject
    private EducationalGroupDao educationalGroupDao;

    @Inject
    private EducationalFieldDao educationalFieldDao;

    @Override
    protected BaseDao getDao() {
        return professorDao;
    }

    @Override
    protected ProfessorModel createNewModel() {
        return new ProfessorModel();
    }

    @Override
    protected void makeCustomNewRecordWidgets() {
        final TextField txtProfessorName = new TextField();
        txtProfessorName.setPrefWidth(150.0);
        txtProfessorName.textProperty().bindBidirectional(getBaseModel().professorNameProperty());
        mainGridPane.add(txtProfessorName, 1, 0);

        final ComboBox<String> cmbEducationalGroup = new ComboBox<>();
        cmbEducationalGroup.setPrefWidth(150.0);
        cmbEducationalGroup.valueProperty().bindBidirectional(getBaseModel().educationalGroupProperty());
        mainGridPane.add(cmbEducationalGroup, 1, 1);

        final ComboBox<String> cmbEducationalField = new ComboBox<>();
        cmbEducationalField.setPrefWidth(150.0);
        cmbEducationalField.valueProperty().bindBidirectional(getBaseModel().educationalFieldProperty());
        mainGridPane.add(cmbEducationalField, 1, 2);

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

        tblCrud.getColumns().get(0).setCellFactory((Callback) TextFieldTableCell.forTableColumn());
        tblCrud.getColumns().get(1).setCellFactory((Callback) ComboBoxTableCell.forTableColumn(groupValues));
        tblCrud.getColumns().get(2).setCellFactory((Callback) ComboBoxTableCell.forTableColumn(fieldValues));

        tblCrud.getColumns().get(2).addEventHandler(TableColumn.editStartEvent(), event -> {
            String groupName = ((ProfessorModel) event.getRowValue()).getEducationalGroup();
            fieldValues.clear();
            fieldValues.addAll(educationalFieldDao.loadAllValues(groupName));
        });
    }

}
