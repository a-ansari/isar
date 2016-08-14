package ir.isar.isarapp.ui.crud;

import ir.isar.isarapp.dao.BaseDao;
import ir.isar.isarapp.entity.Base;
import ir.isar.isarapp.model.BaseModel;
import ir.isar.isarapp.ui.ctrl.BaseController;
import ir.isar.isarapp.util.UiUtils;
import ir.isar.isarapp.util.ReflectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javax.inject.Inject;

/**
 *
 * @author User
 * @param <T>
 */
public abstract class BaseCrudController<T extends BaseModel> extends BaseController {

    private final T _model;

    protected final ResourceBundle baseMessages;

    @Inject
    protected UiUtils uiUtils;

    @Inject
    protected ReflectionUtils reflectionUtils;
    @FXML
    protected TitledPane mainTitledPane;

    @FXML
    protected GridPane mainGridPane;

    @FXML
    protected TableView<T> tblCrud;

    public BaseCrudController() {
        _model = createNewModel();
        baseMessages = ResourceBundle.getBundle(BaseCrudController.class.getName());
    }

    protected T getBaseModel() {
        return _model;
    }

    @Override
    public void afterCompose() {
        loadNewRecordPanel();
        loadCrudTablePanel();
    }

    protected void loadNewRecordPanel() {
        String prefix = "isar." + getClass().getSimpleName() + ".";
        mainTitledPane.setText(messages.getString(prefix + "main.title"));

        int columnNum = Integer.parseInt(messages.getString(prefix + "colNum"));
        for (int i = 0; i < columnNum; i++) {
            mainGridPane.add(new Label(messages.getString(prefix + "col" + i + ".title")), 0, i);
        }

        makeCustomNewRecordWidgets();

        final Button btnAddNewRecord = new Button(baseMessages.getString("isar.BaseCrudController.save.button.text"));
        mainGridPane.add(btnAddNewRecord, 0, columnNum, 2, 1);
        btnAddNewRecord.addEventFilter(ActionEvent.ACTION, event -> createRecord(event));
    }

    protected void loadCrudTablePanel() {
        loadColumns();
        loadRecords();
        makeTableEditable();
        makeCustomColumnHandlers();
    }

    protected void loadColumns() {
        String prefix = "isar." + getClass().getSimpleName() + ".";
        int columnNum = Integer.parseInt(messages.getString(prefix + "colNum"));
        for (int i = 0; i < columnNum; i++) {
            String title = messages.getString(prefix + "col" + i + ".title");
            String field = messages.getString(prefix + "col" + i + ".field");

            TableColumn column = new TableColumn();
            column.setText(title);
            column.setCellValueFactory(new PropertyValueFactory(field));
            column.setId("col" + field);
            tblCrud.getColumns().add(column);
        }
    }

    protected void loadRecords() {
        List<T> listOfModels = getListOfModels();
        tblCrud.setItems(FXCollections.observableList(listOfModels));
    }

    protected void makeTableEditable() {
        EventHandler cellEditHandler = event -> {
            TableColumn.CellEditEvent cellEditEvent = (TableColumn.CellEditEvent) event;
            if (cellEditEvent.getNewValue() == null
                    || cellEditEvent.getNewValue().equals(cellEditEvent.getOldValue())) {
                return;
            }
            T model = (T) cellEditEvent.getRowValue();
            model.setDirty(true);

            String columnId = cellEditEvent.getTableColumn().getId();
            String fieldName = uiUtils.extractNameFromWidget(columnId);
            reflectionUtils.writeValueToModel(model, fieldName, cellEditEvent.getNewValue());
        };

        tblCrud.setEditable(true);
        for (TableColumn column : tblCrud.getColumns()) {
            String fieldName = uiUtils.extractNameFromWidget(column.getId());
            column.setCellValueFactory(new PropertyValueFactory(fieldName));
            column.addEventHandler(TableColumn.editCommitEvent(), cellEditHandler);
        }
    }

    protected void createRecord(ActionEvent event) {
        if (getBaseModel().hasError()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle(baseMessages.getString("isar.BaseCrudController.save.error.title"));
            alert.setContentText(baseMessages.getString("isar.BaseCrudController.save.error.message"));
            alert.showAndWait();
            return;
        }

        Base entity = (Base) getBaseModel().createEntity();
        getDao().save(entity);

        getBaseModel().clear();

        T newModel = createNewModel();
        newModel.fromEntity(entity);
        tblCrud.getItems().add(newModel);
    }

    @FXML
    protected void saveChanges(ActionEvent event) {
        for (T model : tblCrud.getItems()) {
            if (model.isDirty()) {
                if (model.hasError()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle(baseMessages.getString("isar.BaseCrudController.save.error.title"));
                    alert.setContentText(baseMessages.getString("isar.BaseCrudController.save.error.message"));
                    alert.showAndWait();
                    break;
                }
                saveModel(model);
                model.setDirty(false);
            }
        }
    }

    @FXML
    protected void deleteRecord(ActionEvent event) {
        T model = tblCrud.getSelectionModel().getSelectedItem();
        if (model == null) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle(baseMessages.getString("isar.BaseCrudController.delete.confirm.title"));
        alert.setContentText(baseMessages.getString("isar.BaseCrudController.delete.confirm.message"));
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    deleteEntity(model);
                    tblCrud.getItems().remove(model);
                });
    }

    protected List<T> getListOfModels() {
        List<Base> entityList = getDao().loadAll();
        List<T> modelList = new ArrayList<>();
        for (Base entity : entityList) {
            T model = createNewModel();
            model.fromEntity(entity);
            modelList.add(model);
        }
        return modelList;
    }

    protected void saveModel(T model) {
        Base entity = getDao().loadById(model.getId());
        model.updateEntity(entity);
        getDao().save(entity);
    }

    protected void deleteEntity(T model) {
        getDao().deleteById(model.getId());
    }

    protected abstract BaseDao getDao();

    protected abstract T createNewModel();

    protected abstract void makeCustomNewRecordWidgets();

    protected abstract void makeCustomColumnHandlers();
}
