package ir.isar.isarapp.biz;

import ir.isar.isarapp.entity.Term;
import ir.isar.isarapp.filter.result.FacilityResultColumn;
import ir.isar.isarapp.filter.result.ResultColumn;
import ir.isar.isarapp.filter.result.TermSpecificResultColumn;
import ir.isar.isarapp.model.FacilityModel;
import ir.isar.isarapp.model.StudentFullModel;
import ir.isar.isarapp.util.ConversionUtils;
import ir.isar.isarapp.util.ReflectionUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.inject.Singleton;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author User
 */
@Singleton
public class ReportExcelBiz {

    private WritableCellFormat headerCellFormat;
    private WritableCellFormat dataCellFormat;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ResourceBundle messages = ResourceBundle.getBundle(getClass().getName());

    @Inject
    private ConversionUtils conversionUtils;
    @Inject
    private ReflectionUtils reflectionUtils;
    @Inject
    private TermBiz termBiz;
    @Inject
    private FacilityBiz facilityBiz;

    public void report(File outputFile, List<StudentFullModel> studentList, List<ResultColumn> columnList) {
        initCellFormat();
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(outputFile);
            WritableSheet sheet = workbook.createSheet(messages.getString("isar.ReportExcelBiz.sheetName"), 0);
            SheetSettings settings = sheet.getSettings();
            settings.setDefaultColumnWidth(11);
//            settings.setDefaultRowHeight(500);

//            sheet.setColumnView(2, 17); // Family
//            sheet.setColumnView(10, 17);// Field
            writeHeaderRow(sheet, columnList);

            int rowNum = 1;
            for (StudentFullModel student : studentList) {
                Object[] values = loadStudentInfoRow(student, columnList);
                writeStudentInfo(sheet, values, rowNum);
                rowNum++;
            }

        } catch (IOException | WriteException ex) {
            logger.error("Error while exporting", ex);
        } finally {
            try {
                workbook.write();
                workbook.close();
            } catch (IOException | WriteException ex) {
            }
        }

    }

    void initCellFormat() {
        headerCellFormat = new WritableCellFormat();
        dataCellFormat = new WritableCellFormat();

        try {
            headerCellFormat.setBackground(Colour.GRAY_25);
            headerCellFormat.setFont(new WritableFont(WritableFont.TAHOMA, WritableFont.DEFAULT_POINT_SIZE, WritableFont.BOLD));
            headerCellFormat.setWrap(true);
            headerCellFormat.setAlignment(Alignment.CENTRE);
            headerCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            headerCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            dataCellFormat.setFont(new WritableFont(WritableFont.TAHOMA));
            dataCellFormat.setWrap(true);
            dataCellFormat.setAlignment(Alignment.CENTRE);
            dataCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            dataCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        } catch (Exception ex) {
            logger.error("WriteException", ex);
        }
    }

    Object[] loadStudentInfoRow(StudentFullModel student, List<ResultColumn> columnList) {
        Object[] values = new Object[columnList.size()];
        for (int i = 0; i < values.length; i++) {
            ResultColumn column = columnList.get(i);
            if (column instanceof TermSpecificResultColumn) {
                Long studentNumber = Long.parseLong(student.getStudentNumber());
                Integer termNumber = ((TermSpecificResultColumn) column).getTermNumber();
                Term term = termBiz.loadByStudentAndTermNumber(studentNumber, termNumber);
                if (term == null) {
                    term = new Term();
                }
                values[i] = reflectionUtils.readValueFromModel(term, column.getField());
            } if (column instanceof FacilityResultColumn) {
                Long studentNumber = Long.parseLong(student.getStudentNumber());
                FacilityModel facilityModel = facilityBiz.calculateAllPayments(studentNumber);
                values[i] = reflectionUtils.readValueFromModel(facilityModel, column.getField());
            } else {
                values[i] = reflectionUtils.readValueFromModel(student, column.getField());
            }
        }
        return values;
    }

    void writeHeaderRow(WritableSheet sheet, List<ResultColumn> columnList) throws WriteException {
        sheet.setRowView(1, 600);
        for (int i = 0; i < columnList.size(); i++) {
            String value = columnList.get(i).toString();
            Label label = new Label(i, 0, value, headerCellFormat);
            label.setCellFormat(headerCellFormat);
            sheet.addCell(label);
        }
    }

    void writeStudentInfo(WritableSheet sheet, Object[] values, int rowNum) throws WriteException {
        sheet.setRowView(rowNum, 510);
        for (int i = 0; i < values.length; i++) {
            String value = "";
            try {
                value = (String) (values[i]);
            } catch (ClassCastException ex) {
                value = conversionUtils.toString(values[i]);
            }
            Label label = new Label(i, rowNum, value, dataCellFormat);
            label.setCellFormat(dataCellFormat);
            sheet.addCell(label);
        }
    }

}
