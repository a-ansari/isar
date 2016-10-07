/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.isar.isarapp.ui.ctrl;

import ir.isar.isarapp.model.TermModelCreate;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ir.isar.isarapp.dao.TermStatusDao;
import ir.isar.isarapp.model.TermModel;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.util.ResourceBundle;



public class TermModelController implements Initializable {
    private  StudentInfoController studentInfo;
    private TermStatusDao termStatusDao;
    private TableView<TermModel> tblTermInfo;
    private final ResourceBundle messages = ResourceBundle.getBundle(getClass().getName());

   TermModelController(StudentInfoController SI,TableView<TermModel> tblTermInfo) {
        this.studentInfo = SI;
        termStatusDao= new TermStatusDao();
        this.tblTermInfo= tblTermInfo;
   }
   
    @FXML
    private TextField termNumber;
    @FXML
    private TextField takenUnits;
    @FXML
    private TextField passedUnits;
    @FXML
    private TextField deletedUnits;
    @FXML
    private TextField failedUnits;
    @FXML
    private TextField unspecifiedUnits;
    @FXML
    private TextField zeroUnits;
    @FXML
    private TextField termAverage;
//    @FXML
//    private TextField totalAverage;
    @FXML
    private ComboBox<String> termStatus;
    @FXML
    private Button SubmitButton;
    @FXML
   private void SubmitButtonAction(ActionEvent event) {
       
        int newTermNumber =Integer.parseInt(termNumber.getText());
        for(TermModel term : tblTermInfo.getItems()){
          if(term.getTermNumber()== newTermNumber){
              Alert repetitiveTermAlert = new Alert(Alert.AlertType.WARNING);
              repetitiveTermAlert.setHeaderText(null);
              repetitiveTermAlert.setTitle(messages.getString("isar.TermModelController.repetitiveTerm"));
              repetitiveTermAlert.setContentText(messages.getString("isar.TermModelController.repetitiveTermWarning"));
               Button exitButton = (Button) repetitiveTermAlert.getDialogPane().lookupButton(ButtonType.OK);
               exitButton.setText(messages.getString("isar.TermModelController.confirm"));
              repetitiveTermAlert.showAndWait();

              return;
           }
       }

        TermModelCreate.setTermNumber(termNumber.getText());
        TermModelCreate.setTakenUnits(takenUnits.getText());
        TermModelCreate.setPassedUnits(passedUnits.getText());
        TermModelCreate.setDeletedUnits(deletedUnits.getText());
        TermModelCreate.setFailedUnits(failedUnits.getText());
        TermModelCreate.setUnspecifiedUnits(unspecifiedUnits.getText());
        TermModelCreate.setZeroUnits(zeroUnits.getText());
        TermModelCreate.setTermAverage(termAverage.getText());
       // TermModelCreate.setTotalAverage(totalAverage.getText());
        TermModelCreate.setTermStatus(termStatus.getValue());
        TermModelCreate.setUpdateAlert(false);
         studentInfo.addTermRow();
       // studentInfo.update(event);
        TermModelCreate.reset();
        Stage stage = (Stage) SubmitButton.getScene().getWindow();
        stage.close();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
       termNumber.textProperty().bindBidirectional(TermModelCreate.termNumber);
       takenUnits.textProperty().bindBidirectional(TermModelCreate.takenUnits);
       passedUnits.textProperty().bindBidirectional(TermModelCreate.passedUnits);
       deletedUnits.textProperty().bindBidirectional(TermModelCreate.deletedUnits);
       failedUnits.textProperty().bindBidirectional(TermModelCreate.failedUnits);
       unspecifiedUnits.textProperty().bindBidirectional(TermModelCreate.unspecifiedUnits);
       zeroUnits.textProperty().bindBidirectional(TermModelCreate.zeroUnits);
       termAverage.textProperty().bindBidirectional(TermModelCreate.termAverage);
//       totalAverage.textProperty().bindBidirectional(TermModelCreate.totalAverage);
       termStatus.getItems().addAll(termStatusDao.loadAllValues());
     //  termStatus.getItems().addAll("نامشخص","عادی","مشروط","حذف ترم با احتساب سنوات","حذف ترم بدون احتساب سنوات");
       termStatus.valueProperty().bindBidirectional(TermModelCreate.termStatus);       
    }    
    
  
}
