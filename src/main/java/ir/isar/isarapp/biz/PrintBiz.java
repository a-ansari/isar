package ir.isar.isarapp.biz;

import ir.isar.isarapp.model.StudentModel;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Singleton;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author User
 */
@Singleton
public class PrintBiz {

    public void print(StudentModel student, String outputFile) throws Exception {
        List list = new ArrayList<>();
        list.add(student);

        File reportFile = new File("jasper/student.jasper");
        Map parameters = new HashMap();
        parameters.put("SUBREPORT_DIR", reportFile.getParent());

        JasperPrint print = JasperFillManager.fillReport(reportFile.getPath(), parameters, new JRBeanCollectionDataSource(list));
        JasperExportManager.exportReportToPdfFile(print, outputFile);
    }
}
