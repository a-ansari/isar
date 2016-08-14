package ir.isar.isarapp.ui.ctrl;

import ir.isar.isarapp.biz.PaymentBiz;
import ir.isar.isarapp.dao.PaymentTypeDao;
import ir.isar.isarapp.entity.CodeValue;
import ir.isar.isarapp.entity.PaymentType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.inject.Inject;
import java.util.Optional;

/**
 * @author User
 */
public class PaymentActionController extends BaseController {

    @Inject
    private PaymentTypeDao paymentTypeDao;

    @Inject
    private PaymentBiz paymentBiz;

    @FXML
    private ComboBox cmbPaymentType;

    @FXML
    private ComboBox cmbAppliedStudents;

    @FXML
    private TextField txtTermNo;

    class UiModel {

        final ObjectProperty<SingleSelectionModel<String>> paymentType = new SimpleObjectProperty();
        final ObjectProperty<SingleSelectionModel<String>> appliedStudents = new SimpleObjectProperty();
        final StringProperty termNo = new SimpleStringProperty();
    }

    private UiModel uiModel = new UiModel();

    @Override
    public void afterCompose() {
        uiModel.paymentType.bindBidirectional(cmbPaymentType.selectionModelProperty());
        uiModel.appliedStudents.bindBidirectional(cmbAppliedStudents.selectionModelProperty());
        uiModel.termNo.bindBidirectional(txtTermNo.textProperty());

        for (CodeValue transactionType : paymentTypeDao.loadAll()) {
            cmbPaymentType.getItems().add(transactionType.getValue());
        }

        cmbAppliedStudents.getItems().add(messages.getString("isar.PaymentActionController.applied.all"));
    }

    @FXML
    protected void action(ActionEvent event) {
        String paymentType = uiModel.paymentType.get().getSelectedItem();
        String appliedStudents = uiModel.appliedStudents.get().getSelectedItem();
        String termNo = uiModel.termNo.get();

        if (paymentType == null || appliedStudents == null || termNo.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle(messages.getString("isar.PaymentActionController.error.title"));
            alert.setContentText(messages.getString("isar.PaymentActionController.error.message"));
            alert.showAndWait();
            return;
        }
        Integer termNumber;
        try {
            termNumber = Integer.parseInt(termNo);
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle(messages.getString("isar.PaymentActionController.error.title"));
            alert.setContentText(messages.getString("isar.PaymentActionController.termNumber.message"));
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle(messages.getString("isar.PaymentActionController.confirm.title"));
        alert.setContentText(messages.getString("isar.PaymentActionController.confirm.message"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() != ButtonType.OK){
            return;
        }

        PaymentType paymentType1 = paymentTypeDao.loadByValue(paymentType);
        paymentBiz.doPaymentAction(paymentType1, termNumber);

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(messages.getString("isar.PaymentActionController.finish.title"));
        alert.setContentText(messages.getString("isar.PaymentActionController.finish.message"));
        alert.showAndWait();
    }

}
