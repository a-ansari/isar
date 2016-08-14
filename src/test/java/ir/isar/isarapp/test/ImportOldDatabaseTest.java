package ir.isar.isarapp.test;

import ir.isar.isarapp.entity.Student;
import ir.isar.isarapp.entity.Term;
import ir.isar.isarapp.model.OldStudentModel;
import ir.isar.isarapp.model.OldTermModel;
import ir.isar.isarapp.oldapp.convert.StudentConverter;
import ir.isar.isarapp.oldapp.convert.TermConverter;
import ir.isar.isarapp.util.TabSeparatedParser;
import ir.isar.isarapp.util.AppContextUtils;
import ir.isar.isarapp.util.DataBaseUtils;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author User
 */
public class ImportOldDatabaseTest {

//    @BeforeClass
    public static void start() {
        AppContextUtils.startAppContext();
        DataBaseUtils dataBaseUtils = AppContextUtils.getBean(DataBaseUtils.class);
        dataBaseUtils.startDB("Sharif","admin", "a");
        dataBaseUtils.initialize();
    }
    
    @Test
    public void test0() {
        
    }

//    @Test
    public void test1() throws Exception {
        TabSeparatedParser tabSeparatedParser = AppContextUtils.getBean(TabSeparatedParser.class);
        CSVParser parser = tabSeparatedParser.createParser("sample.txt");
        StudentConverter converter = AppContextUtils.getBean(StudentConverter.class);

        for (CSVRecord record : parser) {
            OldStudentModel model = new OldStudentModel();
            tabSeparatedParser.parse(record, model);

            Student entity = new Student();
            converter.convertModelToEntity(model, entity);
            System.out.println(entity);
        }

    }

//    @Test
    public void test2() throws Exception {
        TabSeparatedParser tabSeparatedParser = AppContextUtils.getBean(TabSeparatedParser.class);
        CSVParser parser = tabSeparatedParser.createParser("sample_term.txt");
        TermConverter converter = AppContextUtils.getBean(TermConverter.class);

        for (CSVRecord record : parser) {
            OldTermModel model = new OldTermModel();
            tabSeparatedParser.parse(record, model);

            Term entity = new Term();
            converter.convertModelToEntity(model, entity);
            System.out.println(entity);
        }
    }
}
