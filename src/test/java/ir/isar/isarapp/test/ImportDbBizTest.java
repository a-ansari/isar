package ir.isar.isarapp.test;

import ir.isar.isarapp.biz.ImportDbBiz;
import ir.isar.isarapp.util.AppContextUtils;
import ir.isar.isarapp.util.DataBaseUtils;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author User
 */
public class ImportDbBizTest {

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
}
