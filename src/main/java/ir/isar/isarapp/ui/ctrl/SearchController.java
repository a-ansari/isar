package ir.isar.isarapp.ui.ctrl;

import ir.isar.isarapp.MainApp;
import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.filter.expression.BaseExpression;
import ir.isar.isarapp.filter.BaseFilter;
import ir.isar.isarapp.biz.FilterBiz;
import ir.isar.isarapp.biz.ReportExcelBiz;
import ir.isar.isarapp.biz.SearchBiz;
import ir.isar.isarapp.biz.SearchResultBiz;
import ir.isar.isarapp.biz.StudentFullBiz;
import ir.isar.isarapp.biz.TermBiz;
import ir.isar.isarapp.entity.Term;
import ir.isar.isarapp.filter.ComboFilter;
import ir.isar.isarapp.filter.EnumFilter;
import ir.isar.isarapp.filter.result.ResultColumn;
import ir.isar.isarapp.filter.result.TermSpecificResultColumn;
import ir.isar.isarapp.model.FilterAndResultModel;
import ir.isar.isarapp.model.SearchModel;
import ir.isar.isarapp.model.StudentFullModel;
import ir.isar.isarapp.util.ConversionUtils;
import ir.isar.isarapp.util.ReflectionUtils;
import ir.isar.isarapp.util.UiUtils;
import java.awt.Desktop;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
//import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javax.inject.Inject;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;

/**
 *
 * @author User
 */
public class SearchController extends BaseController {
    
    //private final ResourceBundle columnTitles = ResourceBundle.getBundle(SearchResultBiz.class.getName());

    @Inject
    private FilterBiz filterBiz;

    @Inject
    private SearchBiz searchBiz;

    @Inject
    private SearchResultBiz searchResultBiz;

    @Inject
    private StudentFullBiz studentFullBiz;

    @Inject
    private ReportExcelBiz reportExcelBiz;

    @Inject
    private UiUtils uiUtils;

    @Inject
    private ReflectionUtils reflectionUtils;

    @Inject
    private ConversionUtils conversionUtils;

    @Inject
    private TermBiz termBiz;

    @FXML
    private TableView<SearchModel> tblFilterPanel;

    @FXML
    private ComboBox<BaseFilter> cmbFilter;

    @FXML
    private ComboBox<BaseExpression> cmbExpression;

    @FXML
    private TextField txtValue;

    @FXML
    private CheckComboBox ccbValue;

    @FXML
    private TextField txtTermNo;

    @FXML
    private Label lblTermNo;

    @FXML
    private Pane paneValue;

    @FXML
    private Label lblResultCount;

    @FXML
    private TableView<StudentFullModel> tblResultPanel;

    @FXML
    private CheckComboBox<ResultColumn> ccbResultColumns;

    @FXML
    private CheckBox chkHasRowNumber;
    
    @FXML
    private GridPane AverageGridPane;
    
    @FXML
    private Label lblAverage;
    
    @FXML
    private Label lblAverageTitle;
    
    @FXML
    private Label lblAverage1;
    
    @FXML
    private Label lblAverage2;
    
    @FXML
    private Label lblAverage3;
    
    @FXML
    private Label lblAverage4;
    
    @FXML
    private Label lblAverage5;
    
    @FXML
    private Label lblAverage6;
     
    @FXML
    private Label lblAverage7;
      
    @FXML
    private Label lblAverage8;
    
    @FXML
    private Label lblResult1;
    
    @FXML
    private Label lblResult2;
    
    @FXML
    private Label lblResult3;
    
    @FXML
    private Label lblResult4;
    
    @FXML
    private Label lblResult5;
    
    @FXML
    private Label lblResult6;
     
    @FXML
    private Label lblResult7;
      
    @FXML
    private Label lblResult8;

    private final SearchModel _model = new SearchModel();

    @Override
    public void afterCompose() {
        loadFilterList();
        loadFilterTable();
        loadResultList();
//        loadResultTable();
        addDoubleClickEvent();

        loadFilterItems();
        loadResultItems();
    }

    void loadFilterList() {
        cmbFilter.valueProperty().bindBidirectional(_model.filterProperty());
        cmbExpression.valueProperty().bindBidirectional(_model.expressionProperty());
        txtTermNo.textProperty().bindBidirectional(_model.termNumberProperty());

        cmbFilter.getItems().addAll(filterBiz.listAllFilters());
        cmbFilter.valueProperty().addListener(listener -> {
            BaseFilter filter = cmbFilter.getValue();
            _model.setExpression(null);
            cmbExpression.getItems().clear();

            ccbValue.setVisible(false);
            txtValue.setVisible(false);
            try {
                ccbValue.getCheckModel().clearChecks();
            } catch (NullPointerException ex) {
            }
            ccbValue.getItems().clear();
            txtValue.setText("");
//            try{
//            ccbValue.checkModelProperty().unbindBidirectional(_model.valueProperty());
//            txtValue.textProperty().unbindBidirectional(_model.valueProperty());
//            }catch(Exception ex){
//                
//            }

            lblTermNo.setVisible(false);
            txtTermNo.setVisible(false);
            txtTermNo.setText("");

            if (filter == null) {
//                if (!paneValue.getChildren().isEmpty()) {
//                    _model.valueProperty().unbind();
//                    paneValue.getChildren().remove(0);
//                }
                return;
            }
            cmbExpression.getItems().addAll(filter.getListOfExpresssions());

//            Control valueField = filter.createValueField(_model);
//            if (!paneValue.getChildren().isEmpty()) {
//                _model.valueProperty().unbind();
//                paneValue.getChildren().remove(0);
//            }
//            paneValue.getChildren().add(valueField);
            if (filter.isTermBased()) {
                lblTermNo.setVisible(true);
                txtTermNo.setVisible(true);
            }

            if (filter instanceof ComboFilter) {
                List items = ((ComboFilter) filter).getAllItems();
                for (Object item : items) {
                    ccbValue.getItems().add(item);
                }
                _model.setValueProperty(ccbValue.checkModelProperty());
                ccbValue.setVisible(true);
            } else if (filter instanceof EnumFilter) {
                List items = ((EnumFilter) filter).getAllItems();
                for (Object item : items) {
                    ccbValue.getItems().add(item);
                }
                _model.setValueProperty(ccbValue.checkModelProperty());
                ccbValue.setVisible(true);
            } else {
                _model.setValueProperty(txtValue.textProperty());
                txtValue.setVisible(true);
            }

        });
    }

    void loadFilterTable() {
        for (TableColumn column : tblFilterPanel.getColumns()) {
            column.setCellValueFactory(new PropertyValueFactory(uiUtils.extractNameFromWidget(column.getId())));
        }
    }

    void loadResultList() {
        ccbResultColumns.getItems().addAll(searchResultBiz.getAllColumns());
        for (Integer index : searchResultBiz.getDefaultColumns()) {
            ccbResultColumns.getCheckModel().check(index);
        }
        ccbResultColumns.checkModelProperty().addListener(new ChangeListener<IndexedCheckModel<ResultColumn>>() {
            @Override
            public void changed(ObservableValue<? extends IndexedCheckModel<ResultColumn>> observable, IndexedCheckModel<ResultColumn> oldValue, IndexedCheckModel<ResultColumn> newValue) {
                System.out.println("MODEL CHANGED"); //bug: this method never calls
            }
        });
    }

//    void loadResultTable() {
//        for (TableColumn column : tblResultPanel.getColumns()) {
//            column.setCellValueFactory(new PropertyValueFactory(uiUtils.extractNameFromWidget(column.getId())));
//        }
//    }
    @FXML
    protected void addFilter(ActionEvent action) {
        if (_model.hasError()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle(messages.getString("isar.SearchController.save.error.title"));
            alert.setContentText(messages.getString("isar.SearchController.save.error.message"));
            alert.showAndWait();
            return;
        }

        tblFilterPanel.getItems().add(_model.clone());
        _model.clear();
    }

    @FXML
    protected void deleteFilter(ActionEvent action) {
        SearchModel model = tblFilterPanel.getSelectionModel().getSelectedItem();
        if (model == null) {
            return;
        }
//        Action response = Dialogs.create()
//                .title(messages.getString("isar.SearchController.delete.confirm.title"))
//                .message(messages.getString("isar.SearchController.delete.confirm.message"))
//                .actions(Dialog.Actions.YES, Dialog.Actions.NO)
//                .showConfirm();
//        if (response == Dialog.Actions.YES) {
        tblFilterPanel.getItems().remove(model);
//        }
    }

    @FXML
    protected void search(ActionEvent action) {
        if (resultOrderList == null) {
            resultOrderList = ccbResultColumns.getCheckModel().getCheckedItems();
        }
        FilterAndResultModel fullModel = searchBiz.getFilterAndResultModel();

        fullModel.setFilterList(tblFilterPanel.getItems());
        fullModel.setColumnList(resultOrderList);
        fullModel.setResultList(searchBiz.search(fullModel.getFilterList()));
        fullModel.setHasRowNumber(chkHasRowNumber.isSelected());

        loadResultItems();
    }

    void loadFilterItems() {
        List<SearchModel> filterList = searchBiz.getFilterAndResultModel().getFilterList();
        if (filterList == null) {
            return;
        }
        tblFilterPanel.getItems().clear();
        for (SearchModel model : filterList) {
            tblFilterPanel.getItems().add(model);
        }
    }

    void loadResultItems() {
        FilterAndResultModel fullModel = searchBiz.getFilterAndResultModel();

        if (fullModel.getFilterList() == null) {
            return;
        }

        lblResultCount.setText(String.valueOf(fullModel.getResultList().size()));

        tblResultPanel.getColumns().clear();
        if (fullModel.getHasRowNumber()) {
            String title = messages.getString("isar.SearchController.rowNumber.label");
            String firstColumnTitle = "";
            try {
                firstColumnTitle = fullModel.getColumnList().get(0).getTitle();
            } catch (Exception ex) {
            }
            if (!title.equals(firstColumnTitle)) {
                ResultColumn resultColumn = new ResultColumn(title, "rowNumber");
                List<ResultColumn> columns = new ArrayList<>();
                columns.add(resultColumn);
                columns.addAll(fullModel.getColumnList());
                fullModel.setColumnList(columns);
            }
        }
        for (ResultColumn resultColumn : fullModel.getColumnList()) {
            TableColumn tableColumn = new TableColumn();
            tableColumn.setText(resultColumn.toString());
            tableColumn.setCellValueFactory(new MyCellValueFactory(resultColumn));
            tableColumn.setPrefWidth(100);
            tblResultPanel.getColumns().add(tableColumn);
        }

        tblResultPanel.getItems().clear();
        int rowNumber = 1, selectedRow = -1;
        for (Student student : fullModel.getResultList()) {
            StudentFullModel model = studentFullBiz.convertToFullModel(student, fullModel.getColumnList());
            model.setRowNumber(Integer.toString(rowNumber));
            tblResultPanel.getItems().add(model);
            if (model.getStudentNumber().equals(fullModel.getSelectedStudentNumber())) {
                selectedRow = rowNumber - 1;
            }
            rowNumber++;
        }

        if (selectedRow != -1) {
            tblResultPanel.getSelectionModel().select(selectedRow);
            tblResultPanel.getSelectionModel().focus(selectedRow);
            Platform.runLater(() -> {
                tblResultPanel.requestFocus();
            });
        }
    }

    @FXML
    protected void exportExcel(ActionEvent action) {
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

                FilterAndResultModel fullModel = searchBiz.getFilterAndResultModel();
                List<StudentFullModel> resultList = tblResultPanel.getItems();
                reportExcelBiz.report(outputFile, resultList, fullModel.getColumnList());

                logger.info("Report finished successfully");
                Desktop.getDesktop().open(outputFile);
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setHeaderText(null);
//                alert.setTitle(messages.getString("isar.SearchController.report.success.title"));
//                alert.setContentText(messages.getString("isar.SearchController.report.success.message"));
//                alert.showAndWait();
            } catch (Exception ex) {
                logger.error("Exception in Report", ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle(messages.getString("isar.SearchController.report.error.title"));
                alert.setContentText(messages.getString("isar.SearchController.report.error.message"));
                alert.showAndWait();
            } finally {
                MainApp.stage().getScene().setCursor(Cursor.DEFAULT);
            }
        });
    }
    
    @FXML
    protected void calculateAverage(ActionEvent action) {
        Label[] avgTitleLabels = {lblAverage1,lblAverage2,lblAverage3,lblAverage4,lblAverage5,lblAverage6,lblAverage7,lblAverage8};
        Label[] avgResultLabels = {lblResult1,lblResult2,lblResult3,lblResult4,lblResult5,lblResult6,lblResult7,lblResult8};
        Integer i = 0, j = 0;
        Double sum = new Double(0);
        Double average = new Double(0);
        int termNumber;
        for (Label label : avgTitleLabels) {
            label.setText("");
        }
         for (Label label : avgResultLabels) {
            label.setText("");
        }
        //AverageGridPane.setGridLinesVisible(true);
        lblAverageTitle.setText("میانگین");
        FilterAndResultModel fullModel = searchBiz.getFilterAndResultModel();
        for (ResultColumn resultColumn : fullModel.getColumnList()) {
            String field = resultColumn.getField(); 
            switch(field){
                case "totalTaken":
                    for (Student student : fullModel.getResultList()) {
                        sum += student.getUnitSummary().getTotalTaken();
                     }
                    average = sum / fullModel.getResultList().size();
                    avgTitleLabels[i++].setText(resultColumn.toString());
                    avgResultLabels[j++].setText(average.toString());
                    sum = 0.0;
                    break;
                case "totalPassed":
                     for (Student student : fullModel.getResultList()) {
                        sum += student.getUnitSummary().getTotalPassed();
                     }
                    average = sum / fullModel.getResultList().size();
                    avgTitleLabels[i++].setText(resultColumn.toString());
                    avgResultLabels[j++].setText(average.toString());
                    sum = 0.0;
                    break;
                case "totalAverage":
                     for (Student student : fullModel.getResultList()) {
                        sum += student.getUnitSummary().getTotalAverage();
                     }
                    average = sum / fullModel.getResultList().size();
                    avgTitleLabels[i++].setText(resultColumn.toString());
                    avgResultLabels[j++].setText(average.toString());
                    sum = 0.0;
                    break;
                case "takenUnits":
                    termNumber=Integer.parseInt(resultColumn.toString().substring(0, 3));
                    for (Student student : fullModel.getResultList()) {
                        for (Term term : student.getTerms()) {
                            if(term.getTermNumber()==termNumber)
                                sum += term.getTakenUnits();
                        }
                    }
                    average = sum / fullModel.getResultList().size();
                    avgTitleLabels[i++].setText(resultColumn.toString());
                    avgResultLabels[j++].setText(average.toString());
                    sum = 0.0;            
                    break;
                case "passedUnits":
                    termNumber=Integer.parseInt(resultColumn.toString().substring(0, 3));
                    for (Student student : fullModel.getResultList()) {
                        for (Term term : student.getTerms()) {
                            if(term.getTermNumber()==termNumber)
                                sum += term.getPassedUnits();
                        }
                    }
                    average = sum / fullModel.getResultList().size();
                    avgTitleLabels[i++].setText(resultColumn.toString());
                    avgResultLabels[j++].setText(average.toString());
                    sum = 0.0;   
                    break;    
                case "termAverage":
                    termNumber=Integer.parseInt(resultColumn.toString().substring(0, 3));
                    for (Student student : fullModel.getResultList()) {
                        for (Term term : student.getTerms()) {
                            if(term.getTermNumber()==termNumber)
                                sum += term.getTermAverage();
                        }
                    }
                    average = sum / fullModel.getResultList().size();
                    avgTitleLabels[i++].setText(resultColumn.toString());
                    avgResultLabels[j++].setText(average.toString());
                    sum = 0.0;   
                    break;
                default:
                    break;           
        }
        }
    }
    
//    @FXML
//    protected void calculateSum(ActionEvent action) {
//        
//    }

    private void addDoubleClickEvent() {
        tblResultPanel.setRowFactory(tv -> {
            TableRow<StudentFullModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    StudentFullModel rowData = row.getItem();
                    FilterAndResultModel fullModel = searchBiz.getFilterAndResultModel();
                    fullModel.setSelectedStudentNumber(rowData.getStudentNumber());
                    Long studentNumber = Long.parseLong(rowData.getStudentNumber());
                    uiUtils.redirect("/fxml/studentInfo.fxml", studentNumber);
                }
            });
            return row;
        });
    }

    private List<ResultColumn> resultOrderList;

    @FXML
    protected void defineResultOrder(ActionEvent event) {
        Dialog dialog = new Dialog();
        dialog.setHeaderText(null);
        dialog.setTitle(messages.getString("isar.SearchController.DefineResultOrder.title"));

        final ListView<ResultColumn> listView = new ListView<>();
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.setPrefHeight(300);
        listView.setPrefWidth(200);
        if (resultOrderList == null) {
            resultOrderList = ccbResultColumns.getCheckModel().getCheckedItems();
        }
        //TODO
        for (ResultColumn column : ccbResultColumns.getCheckModel().getCheckedItems()) {
            listView.getItems().add(column);
        }

        Button btnUp = new Button("Up");
        btnUp.addEventFilter(ActionEvent.ACTION, ev -> {
            ResultColumn selected = listView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                return;
            }
            int index = listView.getItems().indexOf(selected);
            if (index == 0) {
                return;
            }
            listView.getItems().remove(index);
            listView.getItems().add(index - 1, selected);
            listView.getSelectionModel().select(index - 1);
        });

        Button btnDown = new Button("Down");
        btnDown.addEventFilter(ActionEvent.ACTION, ev -> {
            ResultColumn selected = listView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                return;
            }
            int index = listView.getItems().indexOf(selected);
            if (index == listView.getItems().size() - 1) {
                return;
            }
            listView.getItems().remove(index);
            listView.getItems().add(index + 1, selected);
            listView.getSelectionModel().select(index + 1);
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().add(btnUp);
        vbox.getChildren().add(btnDown);

        HBox hbox = new HBox(10);
        hbox.getChildren().add(vbox);
        hbox.getChildren().add(listView);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
        dialog.getDialogPane().setContent(hbox);

        final Button btnOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        btnOk.addEventFilter(ActionEvent.ACTION, ev -> {
            resultOrderList = listView.getItems();
        });

        dialog.showAndWait();
    }

    @FXML
    protected void addSpecificTermColumns(ActionEvent event) {
        Dialog dialog = new Dialog();
        dialog.setHeaderText(null);
        dialog.setTitle(messages.getString("isar.SearchController.SpecificTerm.title"));

        Label lblTermNumber = new Label(messages.getString("isar.SearchController.SpecificTerm.termNumber"));
        final TextField txtTermNumber = new TextField();
        Label lblColumnMessage = new Label(messages.getString("isar.SearchController.SpecificTerm.columnMessage"));
        final CheckComboBox<TermSpecificResultColumn> chkTermInfos = new CheckComboBox();

        txtTermNumber.setMinWidth(150);
        txtTermNumber.setMaxWidth(150);
        chkTermInfos.setMinWidth(150);
        chkTermInfos.setMaxWidth(150);
        chkTermInfos.getItems().addAll(searchResultBiz.getAllTermSpecificColumns());

        GridPane gridPane = new GridPane();
        gridPane.setHgap(8);
        gridPane.setVgap(8);
        gridPane.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        gridPane.add(lblTermNumber, 0, 0);
        gridPane.add(txtTermNumber, 1, 0);
        gridPane.add(lblColumnMessage, 0, 1);
        gridPane.add(chkTermInfos, 1, 1);

        dialog.getDialogPane().setContent(gridPane);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

        final Button btnOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        btnOk.addEventFilter(ActionEvent.ACTION, ev -> {
            IndexedCheckModel<ResultColumn> checkModel = ccbResultColumns.getCheckModel();
            for (TermSpecificResultColumn resultColumn : chkTermInfos.getCheckModel().getCheckedItems()) {
                resultColumn.setTermNumber(Integer.parseInt(txtTermNumber.getText()));
                ccbResultColumns.getItems().add(resultColumn);
                checkModel.check(resultColumn);
            }
            ccbResultColumns.setCheckModel(checkModel);
        });

        dialog.showAndWait();
    }

    class MyCellValueFactory<S, T> extends PropertyValueFactory<S, T> {

        private final ResultColumn resultColumn;

        public MyCellValueFactory(ResultColumn resultColumn) {
            super(resultColumn.getField());
            this.resultColumn = resultColumn;
        }

        @Override
        public ObservableValue call(TableColumn.CellDataFeatures<S, T> param) {
            if (resultColumn instanceof TermSpecificResultColumn) {
                StudentFullModel student = (StudentFullModel) param.getValue();
                Long studentNumber = Long.parseLong(student.getStudentNumber());
                Integer termNumber = ((TermSpecificResultColumn) resultColumn).getTermNumber();
                Term term = termBiz.loadByStudentAndTermNumber(studentNumber, termNumber);
                if (term == null) {
                    term = new Term();
                }
                Object result = reflectionUtils.readValueFromModel(term, resultColumn.getField());
                return new ReadOnlyObjectWrapper<>(conversionUtils.toString(result));
            } else {
                return super.call(param);
            }
        }
    }
}
