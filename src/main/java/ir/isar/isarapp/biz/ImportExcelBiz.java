package ir.isar.isarapp.biz;

import com.google.inject.Inject;
import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.model.ComboModel;
import ir.isar.isarapp.model.StudentModel;
import javafx.util.Pair;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Singleton
public class ImportExcelBiz {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ResourceBundle messages = ResourceBundle.getBundle(getClass().getName());

    @Inject
    private StudentBiz studentBiz;

    public List<String> loadExcelHeaders(String fileAddr) throws Exception {
        Workbook workbook = Workbook.getWorkbook(new File(fileAddr));
        Sheet sheet = workbook.getSheet(0);

        List<String> list = new ArrayList<>();
        for (int col = 0; col < sheet.getColumns(); col++) {
            Cell cell = sheet.getCell(col, 0);
            list.add(cell.getContents());
        }
        workbook.close();
        return list;
    }

    public List<ComboModel> loadModelHeaders() throws Exception {
        int fieldSize = Integer.parseInt(messages.getString("isar.ImportExcelBiz.header.size"));
        List<ComboModel> list = new ArrayList<>();
        for (int i = 0; i < fieldSize; i++) {
            String field = messages.getString("isar.ImportExcelBiz.header" + i + ".field");
            String value = messages.getString("isar.ImportExcelBiz.header" + i + ".value");
            ComboModel model = new ComboModel(field, value);
            list.add(model);
        }
        return list;
    }

    public List<StudentModel> loadExcelData(String fileAddr, List<Pair<Integer, String>> items) throws Exception {
        Workbook workbook = Workbook.getWorkbook(new File(fileAddr));
        Sheet sheet = workbook.getSheet(0);

        List<StudentModel> studentList = new ArrayList<>();
        for (int row = 1; row < sheet.getRows(); row++) {
            StudentModel student = new StudentModel();
            for (Pair<Integer, String> pair : items) {
                Cell cell = sheet.getCell(pair.getKey(), row);
                String value = cell.getContents();
//                BeanUtils.setProperty(student, pair.getValue(), value);
                FieldUtils.writeField(student, pair.getValue(), value, true);
            }
            studentList.add(student);
        }
        workbook.close();
        return studentList;
    }

    public void persistData(List<StudentModel> studentList ){
        for (StudentModel student: studentList){
            Student entity = new Student();
            studentBiz.convertModelToEntity(student,entity);
            studentBiz.saveOrUpdateStudent(entity);
        }
    }
}
