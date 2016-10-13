package ir.isar.isarapp.ui.ctrl;

import ir.isar.isarapp.MainApp;
import ir.isar.isarapp.biz.*;
import ir.isar.isarapp.dao.EducationalDegreeDao;
import ir.isar.isarapp.dao.EducationalFieldDao;
import ir.isar.isarapp.dao.EducationalGroupDao;
import ir.isar.isarapp.dao.EducationalTrendDao;
import ir.isar.isarapp.dao.EducationalTypeDao;
import ir.isar.isarapp.dao.GraduationStatusDao;
import ir.isar.isarapp.dao.ProfessorDao;
import ir.isar.isarapp.dao.SegmentDao;
import ir.isar.isarapp.dao.TermStatusDao;
import ir.isar.isarapp.enm.GenderEnum;
import ir.isar.isarapp.enm.MarriageEnum;
import ir.isar.isarapp.entity.Payment;
import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.entity.Term;
import ir.isar.isarapp.entity.UnitSummary;
import ir.isar.isarapp.model.PaymentModel;
import ir.isar.isarapp.model.StudentModel;
import ir.isar.isarapp.model.TermModel;
import ir.isar.isarapp.model.TermModelCreate;
import ir.isar.isarapp.util.ReflectionUtils;
import ir.isar.isarapp.util.UiUtils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import javax.inject.Inject;

/**
 * @author User
 */
public class StudentInfoController extends BaseController {

    @Inject
    private UiUtils uiUtils;

    @Inject
    private ReflectionUtils reflectionUtils;

    @Inject
    private EducationalDegreeDao educationalDegreeDao;
    @Inject
    private EducationalFieldDao educationalFieldDao;
    @Inject
    private EducationalGroupDao educationalGroupDao;
    @Inject
    private EducationalTrendDao educationalTrendDao;
    @Inject
    private EducationalTypeDao educationalTypeDao;
    @Inject
    private GraduationStatusDao graduationStatusDao;
    @Inject
    private ProfessorDao professorDao;
    @Inject
    private SegmentDao segmentDao;
    @Inject
    private TermStatusDao termStatusDao;

    @Inject
    private StudentBiz studentBiz;
    @Inject
    private TermBiz termBiz;
    @Inject
    private UnitSummaryBiz unitSummaryBiz;
    @Inject
    private PaymentBiz paymentBiz;
    @Inject
    private PrintBiz printBiz;

    @FXML
    private VBox mainWindow;

    @FXML
    private Node mainStudentInfoNode;

    @FXML
    private TitledPane titStudentInfo;

    @FXML
    private TextField txtStudentNumber;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtNationalNumber;

    @FXML
    private TextField txtBirthCertNumber;

    @FXML
    private TextField txtBirthDate;

    @FXML
    private TextField txtIssuePlace;

    @FXML
    private TextField txtFatherName;

    @FXML
    private ComboBox<String> cmbGender;

    @FXML
    private ComboBox<String> cmbMarriage;

    @FXML
    private TextField txtDiplomaType;

    @FXML
    private TextField txtDiplomaAverage;

    @FXML
    private TextField txtDiplomaYear;

    @FXML
    private TextField txtDiplomaPlace;

    @FXML
    private ComboBox<String> cmbSegment;

    @FXML
    private TextField txtSegmentInfo;

    @FXML
    private TextField txtSegmentPercent;

    @FXML
    private TextField txtSegmentPlace;

    @FXML
    private TextField txtSegmentRank;

    @FXML
    private TextField txtTotalRank;

    @FXML
    private TextField txtEntranceYear;

    @FXML
    private ComboBox<String> cmbEducationalType;

    @FXML
    private ComboBox<String> cmbEducationalDegree;

    @FXML
    private ComboBox<String> cmbEducationalGroup;

    @FXML
    private ComboBox<String> cmbEducationalField;

    @FXML
    private ComboBox<String> cmbEducationalTrend;

    @FXML
    private ComboBox<String> cmbProfessor;

    @FXML
    private ComboBox<String> cmbGraduationStatus;

    @FXML
    private TextField txtGraduationInfo;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtMobileNumber;

    @FXML
    private TextField txtEmergencyNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtAccountNumber;
    //MHI
     @FXML
    private TextField termStudentNumber;

    @FXML
    private Label termFirstName;

    @FXML
    private Label termLastName;
    //E_MHI
    @FXML
    private TableView<TermModel> tblTermInfo;

    @FXML
    private TableColumn<TermModel, Integer> colTermNumber;

    @FXML
    private TableColumn<TermModel, Integer> colTakenUnits;

    @FXML
    private TableColumn<TermModel, Integer> colPassedUnits;

    @FXML
    private TableColumn<TermModel, Integer> colDeletedUnits;

    @FXML
    private TableColumn<TermModel, Integer> colFailedUnits;

    @FXML
    private TableColumn<TermModel, Integer> colUnspecifiedUnits;

    @FXML
    private TableColumn<TermModel, Integer> colZeroUnits;

    @FXML
    private TableColumn<TermModel, Double> colTermAverage;

    @FXML
    private TableColumn<TermModel, Double> colTotalAverage;

    @FXML
    private TableColumn<TermModel, String> colTermStatus;
    
    @FXML
    private Label lblUnitSumTotalTerms;

    @FXML
    private Label lblUnitSumTotalTaken;

    @FXML
    private Label lblUnitSumTotalPassed;

    @FXML
    private Label lblUnitSumTotalDeleted;

    @FXML
    private Label lblUnitSumTotalFailed;

    @FXML
    private Label lblUnitSumTotalUnspecified;

    @FXML
    private Label lblUnitSumTotalZero;

    @FXML
    private Label lblUnitSumTotalAverage;

    @FXML
    private Label lblUnitSumTotalConditional;

    @FXML
    private Label lblUnitSumTotalTerms_Term;

    @FXML
    private Label lblUnitSumTotalTaken_Term;

    @FXML
    private Label lblUnitSumTotalPassed_Term;

    @FXML
    private Label lblUnitSumTotalDeleted_Term;

    @FXML
    private Label lblUnitSumTotalFailed_Term;

    @FXML
    private Label lblUnitSumTotalUnspecified_Term;

    @FXML
    private Label lblUnitSumTotalZero_Term;

    @FXML
    private Label lblUnitSumTotalAverage_Term;

    @FXML
    private Label lblUnitSumTotalConditional_Term;

    @FXML
    private TableView tblPaymentInfo;

    @FXML
    private TableColumn colPaymentTermNumber;

    @FXML
    private TableColumn colPaymentType;

    @FXML
    private TableColumn colPaymentAmount;
    
    @FXML
    private Label status;

    private long oldStudentNumber;

    @Override
    public void afterCompose() {
        loadComboBoxes();
        loadTermColumns();
        loadPaymentColumns();
        loadStudent((Long) data);
    }

    @Override
    public void afterSceneShown() {
        titStudentInfo.setExpanded(true);
        txtStudentNumber.requestFocus();
    }

    private void loadComboBoxes() {
        cmbGender.getItems().addAll(GenderEnum.getAllLocalNames());
        cmbMarriage.getItems().addAll(MarriageEnum.getAllLocalNames());
        cmbEducationalDegree.getItems().addAll(educationalDegreeDao.loadAllValues());
        cmbEducationalGroup.getItems().addAll(educationalGroupDao.loadAllValues());
        cmbEducationalType.getItems().addAll(educationalTypeDao.loadAllValues());
        cmbGraduationStatus.getItems().addAll(graduationStatusDao.loadAllValues());
        cmbSegment.getItems().addAll(segmentDao.loadAllValues());

        cmbEducationalGroup.valueProperty().addListener(listener -> {
            cmbEducationalField.getItems().clear();
            cmbEducationalField.setValue(null);
            cmbEducationalTrend.getItems().clear();
            cmbEducationalTrend.setValue(null);
            cmbProfessor.getItems().clear();
            cmbProfessor.setValue(null);

            List<String> allFields = educationalFieldDao.loadAllValues(cmbEducationalGroup.getValue());
            cmbEducationalField.getItems().addAll(allFields);
        });

        cmbEducationalField.valueProperty().addListener(listener -> {
            String field = cmbEducationalField.getValue();
            String group = cmbEducationalGroup.getValue();

            cmbEducationalTrend.getItems().clear();
            cmbEducationalTrend.setValue(null);
            List<String> allTrends = educationalTrendDao.loadAllValues(field, group);
            cmbEducationalTrend.getItems().addAll(allTrends);

            cmbProfessor.getItems().clear();
            cmbProfessor.setValue(null);
            List<String> allProf = professorDao.loadAllValues(field, group);
            cmbProfessor.getItems().addAll(allProf);
        });
    }

    private void loadTermColumns() {
        StringConverter integerConverter = new IntegerStringConverter();
        StringConverter doubleConverter = new DoubleStringConverter();
        StringConverter stringConverter = new DefaultStringConverter();

        EventHandler cellEditHandler = event -> {
            TableColumn.CellEditEvent cellEditEvent = (TableColumn.CellEditEvent) event;
            if (cellEditEvent.getNewValue().equals(cellEditEvent.getOldValue())) {
                return;
            }
            TermModel model = (TermModel) cellEditEvent.getRowValue();
            model.setDirty(true);

            String columnId = cellEditEvent.getTableColumn().getId();
            String fieldName = uiUtils.extractNameFromWidget(columnId);
            reflectionUtils.writeValueToModel(model, fieldName, cellEditEvent.getNewValue());
        };

        tblTermInfo.setEditable(true);
        for (TableColumn column : tblTermInfo.getColumns()) {
            String fieldName = uiUtils.extractNameFromWidget(column.getId());
            column.setCellValueFactory(new PropertyValueFactory(fieldName));
            column.addEventHandler(TableColumn.editCommitEvent(), cellEditHandler);
        }
        ObservableList<String> termStatusValues = FXCollections.observableArrayList(termStatusDao.loadAllValues());

        colTermNumber.setCellFactory(TextFieldTableCell.forTableColumn(integerConverter));
        colTakenUnits.setCellFactory(TextFieldTableCell.forTableColumn(integerConverter));
        colPassedUnits.setCellFactory(TextFieldTableCell.forTableColumn(integerConverter));
        colDeletedUnits.setCellFactory(TextFieldTableCell.forTableColumn(integerConverter));
        colFailedUnits.setCellFactory(TextFieldTableCell.forTableColumn(integerConverter));
        colUnspecifiedUnits.setCellFactory(TextFieldTableCell.forTableColumn(integerConverter));
        colZeroUnits.setCellFactory(TextFieldTableCell.forTableColumn(integerConverter));
        colTermAverage.setCellFactory(TextFieldTableCell.forTableColumn(doubleConverter));
        colTotalAverage.setCellFactory(TextFieldTableCell.forTableColumn(doubleConverter));
        colTermStatus.setCellFactory(ComboBoxTableCell.forTableColumn(stringConverter, termStatusValues));
    }

    private void loadPaymentColumns() {
        colPaymentTermNumber.setCellValueFactory(new PropertyValueFactory("termNumber"));
        colPaymentType.setCellValueFactory(new PropertyValueFactory("paymentType"));
        colPaymentAmount.setCellValueFactory(new PropertyValueFactory("amount"));
    }

    @FXML
    protected void clearAllFields() {
        oldStudentNumber = 0L;
        uiUtils.clearAllFields(this);
        tblTermInfo.getItems().clear();
        tblPaymentInfo.getItems().clear();
        tblTermInfo.setUserData(null);
    }

    public StudentModel writeToModel() {
        StudentModel model = new StudentModel();
        uiUtils.writeToModel(this, model);
        return model;
    }

    @FXML
    protected void loadStudent(ActionEvent event) {
        long studentNo;
        try {
            studentNo = Long.parseLong(txtStudentNumber.getText());
        } catch (NullPointerException | NumberFormatException ex) {
            return;
        }
        loadStudent(studentNo);
    }

    void loadStudent(Long studentNo) {
        if (studentNo == null) {
            return;
        }
        clearAllFields();
        oldStudentNumber = studentNo;
        Student entity = studentBiz.loadStudent(studentNo);
        if (entity == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle(messages.getString("isar.StudentInfoController.error"));
            alert.setContentText(messages.getString("isar.StudentInfoController.noSuchStudent"));
            alert.showAndWait();
            return;
        }
        tblTermInfo.setUserData(entity);
        StudentModel model = studentBiz.convertToModel(entity);
        uiUtils.readFromModel(this, model);
        loadExtraCombos(model);
        loadTermInfo(entity);
        loadSummaryInfo(entity);
        //addEmptyTermRow();
        loadPaymentInfo(entity);
    //MHI
        termStudentNumber.setText(txtStudentNumber.getText());
        termFirstName.setText(txtFirstName.getText());
        termLastName.setText(txtLastName.getText());
        TermModelCreate.setUpdateAlert(false);
    }
    
    
     @FXML
    protected void loadStudent_inTermInfo(ActionEvent event) {
        txtStudentNumber.setText(termStudentNumber.getText());
        loadStudent(event);
    }
    //E_MHI
    
    private void loadExtraCombos(StudentModel model) {
        cmbGender.setValue(model.getGender());
        cmbMarriage.setValue(model.getMarriage());
    }

    protected void addTermRow() {
        if (needAddEmptyRow()) {
            TermModel model = new TermModel();
            model.setTermNumber(Integer.parseInt(TermModelCreate.termNumber.getValue()));
            model.setTakenUnits(Integer.parseInt(TermModelCreate.takenUnits.getValue()));
            model.setPassedUnits(Integer.parseInt(TermModelCreate.passedUnits.getValue()));
            model.setDeletedUnits(Integer.parseInt(TermModelCreate.deletedUnits.getValue()));
            model.setFailedUnits(Integer.parseInt(TermModelCreate.failedUnits.getValue()));
            model.setUnspecifiedUnits(Integer.parseInt(TermModelCreate.unspecifiedUnits.getValue()));
            model.setZeroUnits(Integer.parseInt(TermModelCreate.zeroUnits.getValue()));
            model.setTermAverage(Double.parseDouble(TermModelCreate.termAverage.getValue()));
           // model.setTotalAverage(Double.parseDouble(TermModelCreate.totalAverage.getValue()));
            model.setTermStatus(TermModelCreate.termStatus.getValue());
            termBiz.calculateTotalAverage(model,tblTermInfo);
            model.setDirty(true);
            tblTermInfo.getItems().add(model);
            tblTermInfo.getItems().sort(new TermNumberComparator());
            for(TermModel term:tblTermInfo.getItems())
                termBiz.calculateTotalAverage(term,tblTermInfo);
            updateTermInfo();           
        }
    }
//    private void addEmptyTermRow() {
//        if (needAddEmptyRow()) {
//            tblTermInfo.getItems().add(new TermModel());
//        }
//    }

    private boolean needAddEmptyRow() {
        ObservableList<TermModel> items = tblTermInfo.getItems();
        if (items.isEmpty()) {
            return true;
        }
        TermModel last = items.get(items.size() - 1);
        return !(last.getTermStatus() == null || last.getTermStatus().isEmpty());
    }

    private List<TermModel> getTermModelList(Student student) {
        List<Term> termEntityList = termBiz.loadAllTerms(student);
        List<TermModel> termModelList = new ArrayList<>();
        for (Term entity : termEntityList) {
            TermModel model = new TermModel();
            model.fromEntity(entity);
            termModelList.add(model);
        }
        return termModelList;
    }

    private void loadTermInfo(Student student) {
        ObservableList<TermModel> termObservableList = FXCollections.observableList(getTermModelList(student));
        tblTermInfo.setItems(termObservableList);
        for(TermModel term:tblTermInfo.getItems())
            termBiz.calculateTotalAverage(term,tblTermInfo);
    }

    private List<PaymentModel> getPaymentModelList(Student student) {
        List<Payment> paymentEntityList = paymentBiz.loadAllPayments(student);
        List<PaymentModel> paymentModelList = new ArrayList<>();
        for (Payment payment : paymentEntityList) {
            PaymentModel model = new PaymentModel();
            model.fromEntity(payment);
            paymentModelList.add(model);
        }
        return paymentModelList;
    }

    private void loadPaymentInfo(Student student) {
        ObservableList<PaymentModel> paymentObservableList = FXCollections.observableList(getPaymentModelList(student));
        tblPaymentInfo.setItems(paymentObservableList);
    }

    void loadSummaryInfo(Student student) {
        try {
            UnitSummary unitSummary = student.getUnitSummary();
            
            lblUnitSumTotalTerms.setText(unitSummary.getTotalTerms().toString());
            lblUnitSumTotalTaken.setText(unitSummary.getTotalTaken().toString());
            lblUnitSumTotalPassed.setText(unitSummary.getTotalPassed().toString());
            lblUnitSumTotalDeleted.setText(unitSummary.getTotalDeleted().toString());
            lblUnitSumTotalFailed.setText(unitSummary.getTotalFailed().toString());
            lblUnitSumTotalUnspecified.setText(unitSummary.getTotalUnspecified().toString());
            lblUnitSumTotalZero.setText(unitSummary.getTotalZero().toString());
            lblUnitSumTotalAverage.setText(unitSummary.getTotalAverage().toString());
            lblUnitSumTotalConditional.setText(unitSummary.getTotalConditional().toString());
            
            lblUnitSumTotalTerms_Term.setText(unitSummary.getTotalTerms().toString());
            lblUnitSumTotalTaken_Term.setText(unitSummary.getTotalTaken().toString());
            lblUnitSumTotalPassed_Term.setText(unitSummary.getTotalPassed().toString());
            lblUnitSumTotalDeleted_Term.setText(unitSummary.getTotalDeleted().toString());
            lblUnitSumTotalFailed_Term.setText(unitSummary.getTotalFailed().toString());
            lblUnitSumTotalUnspecified_Term.setText(unitSummary.getTotalUnspecified().toString());
            lblUnitSumTotalZero_Term.setText(unitSummary.getTotalZero().toString());
            lblUnitSumTotalAverage_Term.setText(unitSummary.getTotalAverage().toString());
            lblUnitSumTotalConditional_Term.setText(unitSummary.getTotalConditional().toString());
        } catch (NullPointerException ex) {
        }
    }

    protected void refreshSummaryInfo(Student student) {
        unitSummaryBiz.refreshSummaryInfo(student);
        loadSummaryInfo(student);
    }

    @FXML
    protected void update(ActionEvent event) {
        try {
            StudentModel model = writeToModel();
            if (model.getStudentNumber().isEmpty()) {
                return;
            }

            long studentNo = Long.parseLong(model.getStudentNumber());
            Student entity;
            if (oldStudentNumber != 0 && oldStudentNumber != studentNo) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle(messages.getString("isar.StudentInfoController.changedStudentNumber.title"));
                alert.setContentText(messages.getString("isar.StudentInfoController.changedStudentNumber.message"));
                Optional<ButtonType> result = alert.showAndWait();
                if (!result.isPresent() || result.get() == ButtonType.CANCEL) {
                    return;
                }
                entity = studentBiz.loadStudent(oldStudentNumber);
                entity.setStudentNumber(studentNo);
            } else {
                if(TermModelCreate.getUpdateAlert()){
                  Alert updateAlert = new Alert(Alert.AlertType.CONFIRMATION);
                  updateAlert.setHeaderText(null);
                  updateAlert.setTitle(messages.getString("isar.StudentInfoController.updateAlert.title"));
                  updateAlert.setContentText(messages.getString("isar.StudentInfoController.updateAlert.message"));
                  Button exitButton = (Button) updateAlert.getDialogPane().lookupButton(ButtonType.OK);
                  Button cancelButton = (Button) updateAlert.getDialogPane().lookupButton(ButtonType.CANCEL);
                  exitButton.setText(messages.getString("isar.StudentInfoController.updateAlert.yes"));
                  cancelButton.setText(messages.getString("isar.StudentInfoController.updateAlert.cancel"));
                  updateAlert.showAndWait();
                }
                entity = studentBiz.loadStudent(studentNo);
            }
            oldStudentNumber = studentNo;

            if (entity == null) {
                entity = new Student();
                entity.setUnitSummary(unitSummaryBiz.createNewSummaryInfo());
            }
            tblTermInfo.setUserData(entity);
            studentBiz.convertModelToEntity(model, entity);
            studentBiz.saveOrUpdateStudent(entity);
            updateTermInfo();
            //addEmptyTermRow();
            
            Timeline timeline = new Timeline();
            status.setText("   بروز شد ...");        
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new KeyValue(status.textProperty(), "")));
            timeline.play();
        } catch (Exception ex) {
            logger.info("Exception in update student", ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle(messages.getString("isar.StudentInfoController.error"));
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    private void updateTermInfo() {
        Student student = (Student) tblTermInfo.getUserData();
        boolean needRefresh = false;
        for (TermModel model : tblTermInfo.getItems()) {
            if (model.isDirty()) {
                needRefresh = true;
                termBiz.saveTermInfo(model, student);
                model.setDirty(false);
            }
        }
        if (needRefresh) {
            refreshSummaryInfo(student);
        }
    }

    @FXML
    protected void delete(ActionEvent event) {
        Student student = (Student) tblTermInfo.getUserData();
        if (student == null) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle(messages.getString("isar.StudentInfoController.deleteStudent"));
        alert.setContentText(messages.getString("isar.StudentInfoController.deleteStudentConfirmation"));
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    studentBiz.deleteStudent(student);
                    clearAllFields();
                });
    }

    void printOldVersion() {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);

        Node node = mainStudentInfoNode;
        double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        node.getTransforms().add(new Scale(scaleX, scaleY));

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean success = job.printPage(node);
            if (success) {
                job.endJob();
            }
        }

        node.getTransforms().add(new Scale(1 / scaleX, 1 / scaleY));
    }

    void printNewVersion() {
        final Student student = (Student) tblTermInfo.getUserData();
        if (student == null) {
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.setInitialFileName("student.pdf");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF File", "*.pdf"));
        fileChooser.setInitialDirectory(new File("."));
        final File outputFile = fileChooser.showSaveDialog(null);
        if (outputFile == null) {
            return;
        }

        Platform.runLater(() -> {
            try {
                MainApp.stage().getScene().setCursor(Cursor.WAIT);
                StudentModel model = studentBiz.convertToModel(student);
                List<TermModel> termList = getTermModelList(student);
                model.setTerms(termList);
                printBiz.print(model, outputFile.getAbsolutePath());
                Desktop.getDesktop().open(outputFile);
            } catch (Exception ex) {
                logger.error("Exception in print", ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle(messages.getString("isar.StudentInfoController.print.error.title"));
                alert.setContentText(messages.getString("isar.StudentInfoController.print.error.message"));
                alert.showAndWait();
            } finally {
                MainApp.stage().getScene().setCursor(Cursor.DEFAULT);
            }
        });
    }

    @FXML
    protected void print(ActionEvent event) {
        printNewVersion();
    }

    @FXML
    protected void exportExcel(ActionEvent event) {
    }

    @FXML
    protected void termExpanded() {
//        MainApp.stage().setHeight(600.0);
//        mainWindow.setMinHeight(600.0);
//        mainWindow.setPrefHeight(600.0);
    }

    @FXML
    protected void deleteTerm() {
        TermModel selectedItem = tblTermInfo.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }
        //MHI
        Alert deleteTermAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteTermAlert.setHeaderText(null);
        deleteTermAlert.setTitle(messages.getString("isar.StudentInfoController.deleteTerm"));
        deleteTermAlert.setContentText(messages.getString("isar.StudentInfoController.deleteTermConfirmation"));
         Button yesButton = (Button) deleteTermAlert.getDialogPane().lookupButton(
                ButtonType.OK
        );
        Button cancelButton = (Button) deleteTermAlert.getDialogPane().lookupButton(
                ButtonType.CANCEL
        );
        yesButton.setText(messages.getString("isar.StudentInfoController.yes"));
        cancelButton.setText(messages.getString("isar.StudentInfoController.no"));
        deleteTermAlert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                      if (selectedItem.getId() != null) {
                          //maybe this item added recently and not persisted yet
                         termBiz.delete(selectedItem.getId());
                      }
                     tblTermInfo.getItems().remove(selectedItem);
                     refreshSummaryInfo((Student) tblTermInfo.getUserData());
                });
      
    }
    
     @FXML
    //MHI
    protected void addTerm() throws IOException {
        // System.out.println(tblTermInfo.toString());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/termModel.fxml"));         
                fxmlLoader.setController(new TermModelController(this,tblTermInfo));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("ترم");
                stage.setScene(new Scene(root1));  
                stage.show();
}
   
}

class TermNumberComparator implements Comparator<TermModel>{
 
	@Override
	public int compare(TermModel T1, TermModel T2) {
		return T1.getTermNumber() - T2.getTermNumber();
	}
}
 //E_MHI