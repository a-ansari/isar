package ir.isar.isarapp.biz;

import ir.isar.isarapp.entity.Base;
import ir.isar.isarapp.oldapp.convert.BaseModelConverter;
import ir.isar.isarapp.oldapp.convert.StudentConverter;
import ir.isar.isarapp.oldapp.convert.TermConverter;
import ir.isar.isarapp.util.TabSeparatedParser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author User
 */
@Singleton
public class ImportDbBiz {

    @Inject
    private TabSeparatedParser tabSeparatedParser;
    @Inject
    private StudentConverter studentConverter;
    @Inject
    private TermConverter termConverter;
    @Inject
    private UnitSummaryBiz unitSummaryBiz;

    public void importDb(String studentAddr, String termAddr, String logAddr) throws IOException {
        try (PrintWriter log = new PrintWriter(new FileWriter(new File(logAddr), false))) {
            log.println("Import of Student Info started...");
            CSVParser parser = tabSeparatedParser.createParser(studentAddr);
            convert(studentConverter, parser, log);
            log.println("Import of Student Info completed.");

            log.println("----------------------------------------");

            log.println("Import of Term Info started...");
            parser = tabSeparatedParser.createParser(termAddr);
            convert(termConverter, parser, log);
            log.println("Import of Term Info completed.");

            log.println("----------------------------------------");

            log.println("Making Unit Summary Info started...");
            unitSummaryBiz.makeAllUnitSummaryInfo();
            log.println("Making Unit Summary Info finished.");
        }
    }

    private void convert(BaseModelConverter converter, CSVParser parser, PrintWriter log) throws IOException {
        int success = 0, failed = 0;
        Serializable model = null;
        Base entity = null;
        for (CSVRecord record : parser) {
            try {
                model = converter.createModel();
                tabSeparatedParser.parse(record, model);
                entity = converter.createEntity(model);
                converter.convertModelToEntity(model, entity);
                converter.saveEntity(entity);
                success++;
            } catch (RuntimeException | IllegalAccessException e) {
                failed++;
                log.println("Exception in model: " + model);
                e.printStackTrace(log);
            }
        }

        log.println("Total successfull items: " + success);
        log.println("Total failed items: " + failed);
    }
}
