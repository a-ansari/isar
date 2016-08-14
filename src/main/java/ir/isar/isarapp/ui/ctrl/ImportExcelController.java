package ir.isar.isarapp.ui.ctrl;

import ir.isar.isarapp.biz.ImportExcelBiz;
import ir.isar.isarapp.model.ComboModel;
import ir.isar.isarapp.model.StudentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Pair;

import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImportExcelController extends BaseController {
    @Inject
    private ImportExcelBiz importExcelBiz;

    @FXML
    private Button btnSelectFile;

    @FXML
    private Button btnHeaderFile;

    @FXML
    private Button btnImportFile;

    @FXML
    private TextField txtFileAddress;

    @FXML
    private VBox vboxExcelHeader;

    @FXML
    protected void selectFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel File", "*.xls"));
        File file = fileChooser.showOpenDialog(null);
        if (file == null) {
            return;
        }
        txtFileAddress.setText(file.getAbsolutePath());

        btnSelectFile.setDisable(true);
        btnHeaderFile.setDisable(false);
    }

    @FXML
    protected void headerFile(ActionEvent event) {
        String file = txtFileAddress.getText();
        try {
            List<String> excelHeaders = importExcelBiz.loadExcelHeaders(file);
            List<ComboModel> modelHeaders = importExcelBiz.loadModelHeaders();
            for (String excelHeader : excelHeaders) {
                Label label = new Label(excelHeader);

                ComboBox combo = new ComboBox();
                combo.getItems().addAll(modelHeaders);

                HBox hbox = new HBox();
                hbox.getChildren().add(label);
                hbox.getChildren().add(combo);

                vboxExcelHeader.getChildren().add(hbox);
            }
            btnHeaderFile.setDisable(true);
            btnImportFile.setDisable(false);
        } catch (Exception ex) {
            logger.error("error", ex);
        }
    }

    @FXML
    protected void importFile(ActionEvent event) {
        try {
            List<Pair<Integer, String>> items = new ArrayList<>();
            for (int index = 0; index < vboxExcelHeader.getChildren().size(); index++) {
                HBox hbox = (HBox) vboxExcelHeader.getChildren().get(index);
                ComboBox combo = (ComboBox) hbox.getChildren().get(1);
                ComboModel selectedModel = (ComboModel) combo.getSelectionModel().getSelectedItem();
                if (selectedModel != null) {
                    Pair<Integer, String> pair = new Pair<>(index, selectedModel.getValue());
                    items.add(pair);
                }
            }
            String file = txtFileAddress.getText();
            List<StudentModel> studentList = importExcelBiz.loadExcelData(file, items);
            importExcelBiz.persistData(studentList);

            btnImportFile.setDisable(true);
        } catch (Exception ex) {
            logger.error("error", ex);
        }
    }
}
