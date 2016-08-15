/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.isar.isarapp.ui.ctrl;

import ir.isar.isarapp.enm.GenderEnum;
import ir.isar.isarapp.enm.MarriageEnum;
import ir.isar.isarapp.model.TermModelCreate;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ir.isar.isarapp.dao.TermStatusDao;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import javax.inject.Inject;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class TermModelController implements Initializable {
    private  StudentInfoController studentInfo;
    private TermStatusDao termStatusDao;
   
//    @FXML
//    private Label label;
   TermModelController(StudentInfoController SI) {
        this.studentInfo = SI;
        termStatusDao= new TermStatusDao();
   }
//    @FXML
//    private Label label;
//    
//    @FXML
//    private void SubmitButtonAction(ActionEvent event) {
////        System.out.println("You clicked me!");
////        label.setText("Hello World!");
//    }
//    
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }  
  
   
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
    @FXML
    private TextField totalAverage;
    @FXML
    private ComboBox<String> termStatus;
    @FXML
    private Button SubmitButton;
    @FXML
   private void SubmitButtonAction(ActionEvent event) {
       
        studentInfo.addTermRow();
        TermModelCreate.setTermNumber(termNumber.getText());
        TermModelCreate.setTakenUnits(takenUnits.getText());
        TermModelCreate.setPassedUnits(passedUnits.getText());
        TermModelCreate.setDeletedUnits(deletedUnits.getText());
        TermModelCreate.setFailedUnits(failedUnits.getText());
        TermModelCreate.setUnspecifiedUnits(unspecifiedUnits.getText());
        TermModelCreate.setZeroUnits(zeroUnits.getText());
        TermModelCreate.setTermAverage(termAverage.getText());
        TermModelCreate.setTotalAverage(totalAverage.getText());
        TermModelCreate.setTermStatus(termStatus.getValue());
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
       totalAverage.textProperty().bindBidirectional(TermModelCreate.totalAverage);
       // System.out.println(termStatusDao);
       termStatus.getItems().addAll(termStatusDao.loadAllValues());
     //  termStatus.getItems().addAll("نامشخص","عادی","مشروط","حذف ترم با احتساب سنوات","حذف ترم بدون احتساب سنوات");
       termStatus.valueProperty().bindBidirectional(TermModelCreate.termStatus);       
    }    
    
  
}
