package ir.isar.isarapp.ui.ctrl;

import ir.isar.isarapp.MainApp;
import ir.isar.isarapp.ui.crud.CrudFieldsController;
import ir.isar.isarapp.ui.crud.CrudProfessorsController;
import ir.isar.isarapp.ui.crud.CrudTrendsController;
import ir.isar.isarapp.util.UiUtils;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MenubarController extends BaseController {
    
    @Inject
    private UiUtils uiUtils;
    
    @Inject
    private CrudFieldsController crudFieldsController;
    
    @Inject
    private CrudTrendsController crudTrendsController;
    
    @Inject
    private CrudProfessorsController CrudProfessorsController;
    
    @FXML
    protected void studentInfo(ActionEvent event) {
        uiUtils.redirect("/fxml/studentInfo.fxml");
    }
    
    @FXML
    protected void search(ActionEvent event) {
        uiUtils.redirect("/fxml/search.fxml");
    }
    
    @FXML
    protected void logout(ActionEvent event) {
        uiUtils.redirect("/fxml/login.fxml");
    }
    
    @FXML
    protected void quit(ActionEvent event) {        
        Alert deleteTermAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteTermAlert.setHeaderText(null);
        deleteTermAlert.setTitle(messages.getString("isar.MenubarController.ExitAlert.title"));
        deleteTermAlert.setContentText(messages.getString("isar.MenubarController.ExitAlert.text"));
         Button exitButton = (Button) deleteTermAlert.getDialogPane().lookupButton(
                ButtonType.OK
        );
        Button cancelButton = (Button) deleteTermAlert.getDialogPane().lookupButton(
                ButtonType.CANCEL
        );
        exitButton.setText(messages.getString("isar.MenubarController.ExitAlert.yes"));
        cancelButton.setText(messages.getString("isar.MenubarController.ExitAlert.no"));
        deleteTermAlert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    MainApp.stage().close();
                });
    }
    
    @FXML
    protected void importSqlServer(ActionEvent event) {
        uiUtils.redirect("/fxml/importDatabase.fxml");
    }

    @FXML
    protected void importExcel(ActionEvent event) {
        uiUtils.redirect("/fxml/importExcel.fxml");
    }

    @FXML
    protected void crudFields(ActionEvent event) {
        uiUtils.redirect("/fxml/crudBase.fxml", crudFieldsController);
    }
    
    @FXML
    protected void crudTrends(ActionEvent event) {
        uiUtils.redirect("/fxml/crudBase.fxml", crudTrendsController);
    }
    
    @FXML
    protected void crudProfessors(ActionEvent event) {
        uiUtils.redirect("/fxml/crudBase.fxml", CrudProfessorsController);
    }
    
    @FXML
    protected void reportBonyad(ActionEvent event) {
        uiUtils.redirect("/fxml/reportBonyad.fxml");
    }
    
    @FXML
    protected void paymentAction(ActionEvent event) {
        uiUtils.redirect("/fxml/paymentAction.fxml");
    }

    @FXML
    protected void about(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(messages.getString("isar.MenubarController.about.title"));
        alert.setContentText(messages.getString("isar.MenubarController.about.message"));
        alert.showAndWait();
    }
}
