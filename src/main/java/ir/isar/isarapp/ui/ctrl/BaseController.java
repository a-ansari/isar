package ir.isar.isarapp.ui.ctrl;

import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author User
 */
public abstract class BaseController {

    protected final Logger logger;
    protected final ResourceBundle messages;
    protected Object data;

    public BaseController() {
        logger = LoggerFactory.getLogger(getClass());
        messages = ResourceBundle.getBundle(getClass().getName());
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void afterCompose() {
    }

    public void afterSceneShown() {
    }
}
