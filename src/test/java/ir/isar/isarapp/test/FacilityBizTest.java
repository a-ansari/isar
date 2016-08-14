package ir.isar.isarapp.test;

import ir.isar.isarapp.biz.FacilityBiz;
import ir.isar.isarapp.model.FacilityModel;
import ir.isar.isarapp.util.AppContextUtils;
import ir.isar.isarapp.util.DataBaseUtils;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author User
 */
public class FacilityBizTest {

//    @BeforeClass
    public static void start() {
        AppContextUtils.startAppContext();
        DataBaseUtils dataBaseUtils = AppContextUtils.getBean(DataBaseUtils.class);
        dataBaseUtils.startDB("Sharif", "admin", "a");
        dataBaseUtils.initialize();
    }

    @Test
    public void test0() {
    }

//    @Test
    public void test1() {
        FacilityBiz facilityBiz = AppContextUtils.getBean(FacilityBiz.class);
        FacilityModel model = facilityBiz.calculateAllPayments(88104843);
        System.out.println(model.getKomakHazine());
    }

}
