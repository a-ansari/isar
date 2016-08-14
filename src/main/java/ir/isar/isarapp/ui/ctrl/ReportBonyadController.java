package ir.isar.isarapp.ui.ctrl;

import ir.isar.isarapp.MainApp;
import ir.isar.isarapp.biz.ReportExcelBiz;
import ir.isar.isarapp.biz.SearchResultBiz;
import ir.isar.isarapp.biz.StudentFullBiz;
import ir.isar.isarapp.dao.StudentDao;
import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.filter.result.ResultColumn;
import ir.isar.isarapp.model.StudentFullModel;
import java.awt.Desktop;
import java.io.File;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javax.inject.Inject;

/**
 *
 * @author User
 */
public class ReportBonyadController extends BaseController {

    @Inject
    private ReportExcelBiz reportExcelBiz;

    @Inject
    private SearchResultBiz searchResultBiz;

    @Inject
    private StudentDao studentDao;

    @Inject
    private StudentFullBiz studentFullBiz;

    @FXML
    protected void report(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.setInitialFileName("export.xls");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel File", "*.xls"));
        fileChooser.setInitialDirectory(new File("."));
        File outputFile = fileChooser.showSaveDialog(null);
        if (outputFile == null) {
            return;
        }

        Platform.runLater(() -> {
            try {
                MainApp.stage().getScene().setCursor(Cursor.WAIT);
                List<Student> studentList = studentDao.loadAllStudyingStudents();
                List<StudentFullModel> fullModelList = studentFullBiz.convertToFullModel(studentList);
                List<ResultColumn> bonyadColumns = searchResultBiz.getBonyadColumns();
                reportExcelBiz.report(outputFile, fullModelList, bonyadColumns);

                logger.info("Bonyad Report finished successfully");
                Desktop.getDesktop().open(outputFile);
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setHeaderText(null);
//                alert.setTitle(messages.getString("isar.ReportBonyadController.success"));
//                alert.setContentText(messages.getString("isar.ReportBonyadController.successMessage"));
//                alert.showAndWait();
            } catch (Exception ex) {
                logger.info("Exception in Bonyad Report", ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle(messages.getString("isar.ReportBonyadController.error"));
                alert.setContentText(messages.getString("isar.ReportBonyadController.errorMessage"));
                alert.showAndWait();
            } finally {
                MainApp.stage().getScene().setCursor(Cursor.DEFAULT);
            }
        });
    }
}
