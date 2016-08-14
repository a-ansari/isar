package ir.isar.isarapp.biz;

import ir.isar.isarapp.util.DataBaseUtils;
import ir.isar.isarapp.excp.LoginException;
import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author User
 */
@Singleton
public class LoginBiz {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ResourceBundle messages = ResourceBundle.getBundle(getClass().getName());

    @Inject
    private DataBaseUtils dataBaseUtils;

    @Inject
    private SearchBiz searchBiz;

    public void tryLogin(String database, String username, String password) {
        logger.info("Trying to login with username={}", username);
        beforeLogin(database, username, password);
        doLogin(database, username, password);
        afterLogin();
    }

    private void beforeLogin(String database, String username, String password) throws LoginException {
        if (database.isEmpty()) {
            throw new LoginException(messages.getString("isar.LoginBiz.databaseMandatory"));
        }
        if (username.isEmpty()) {
            throw new LoginException(messages.getString("isar.LoginBiz.usernameMandatory"));
        }
        if (password.isEmpty()) {
            throw new LoginException(messages.getString("isar.LoginBiz.passwordMandatory"));
        }
    }

    private void doLogin(String database, String username, String password) throws LoginException {
        try {
            dataBaseUtils.startDB(database, username, password);
            dataBaseUtils.testConnection();
        } catch (Exception ex) {
            logger.info("Database error with username=" + username, ex);
            throw new LoginException((messages.getString("isar.LoginBiz.generalDBError")));
        }
    }

    private void afterLogin() {
        dataBaseUtils.initialize();
        searchBiz.clearFilterAndResultModel();
    }
}
