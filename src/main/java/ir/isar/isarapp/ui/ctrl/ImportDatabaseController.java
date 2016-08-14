package ir.isar.isarapp.ui.ctrl;

import ir.isar.isarapp.MainApp;
import ir.isar.isarapp.biz.ImportDbBiz;
import ir.isar.isarapp.util.UiUtils;
import java.io.File;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ImportDatabaseController extends BaseController {

    @Inject
    private ImportDbBiz importDbBiz;
    @Inject
    private UiUtils uiUtils;

    @FXML
    TextField txtStudentFile;

    @FXML
    TextField txtTermFile;

    @FXML
    TextField txtLogFile;

    @FXML
    Button btnImport;

    @FXML
    public void chooseStudentFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.setInitialDirectory(new File("."));
        File file = fileChooser.showOpenDialog(null);
        if (file == null) {
            txtStudentFile.setText("");
        } else {
            txtStudentFile.setText(file.getAbsolutePath());
        }
        checkImportButton();
    }

    @FXML
    public void chooseTermFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.setInitialDirectory(new File("."));
        File file = fileChooser.showOpenDialog(null);
        if (file == null) {
            txtTermFile.setText("");
        } else {
            txtTermFile.setText(file.getAbsolutePath());
        }
        checkImportButton();
    }

    @FXML
    public void chooseLogFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.setInitialFileName("log.txt");
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        File file = fileChooser.showSaveDialog(null);
        if (file == null) {
            txtLogFile.setText("");
        } else {
            txtLogFile.setText(file.getAbsolutePath());
        }
        checkImportButton();
    }

    private void checkImportButton() {
        btnImport.setDisable(txtStudentFile.getText().equals("") || txtTermFile.getText().equals("")
                || txtLogFile.getText().equals(""));
    }

    @FXML
    public void importDb(ActionEvent event) {
        final String studentAddr = txtStudentFile.getText();
        final String termAddr = txtTermFile.getText();
        final String logAddr = txtLogFile.getText();
        MainApp.stage().getScene().setCursor(Cursor.WAIT);
        Platform.runLater(() -> {
            try {
                importDbBiz.importDb(studentAddr, termAddr, logAddr);
            } catch (Exception ex) {
                logger.error("Error in import db", ex);
            } finally {
                MainApp.stage().getScene().setCursor(Cursor.DEFAULT);
            }
        });
    }

}
