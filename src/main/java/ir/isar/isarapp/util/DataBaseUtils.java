package ir.isar.isarapp.util;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;
import ir.isar.isarapp.entity.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author User
 */
@Singleton
public class DataBaseUtils {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Server h2Server;

    private EbeanServer ebeanServer;

    @Inject
    private CodeValueUtils codeValueUtils;

    public void startWebServer() {
        try {
            h2Server = Server.createWebServer().start();
        } catch (Exception ex) {
            logger.error("Error in starting H2 Web Server on port 8082", ex);
        }
    }

    public void stopWebServer() {
        try {
            h2Server.stop();
        } catch (Exception ex) {
            logger.error("Error in stoping H2 Web Server", ex);
        }
    }

    public EbeanServer ebeanServer() {
        return ebeanServer;
    }

    public void startDB(String database, String username, String password) {
//        database = "isar";
        ServerConfig config = new ServerConfig();
        config.setName("isardb");

        DataSourceConfig isardb = new DataSourceConfig();
        isardb.setDriver("org.h2.Driver");
        isardb.setUsername(username);
        isardb.setPassword(password);
        isardb.setUrl("jdbc:h2:./" + database + ";MV_STORE=FALSE");
        config.setDataSourceConfig(isardb);

        config.setDdlGenerate(false);
        config.setDdlRun(false);

        config.setDefaultServer(true);
        config.setRegister(true);

//        GlobalProperties.put("ebean.debug.sql", "false");
        this.ebeanServer = EbeanServerFactory.create(config);
    }

    public void testConnection() {
        String sql = "select count(*) as count from dual";
        SqlRow row = Ebean.createSqlQuery(sql).findUnique();

        int i = row.getInteger("count");
        if (i != 1) {
            throw new RuntimeException("count from dual = " + i);
        }
    }

    public void initialize() {
        codeValueUtils.initStaticValues(TermStatus.class);
        codeValueUtils.initStaticValues(EducationalDegree.class);
        codeValueUtils.initStaticValues(GraduationStatus.class);
        codeValueUtils.initStaticValues(Segment.class);
        codeValueUtils.initStaticValues(PaymentType.class);
    }
}
