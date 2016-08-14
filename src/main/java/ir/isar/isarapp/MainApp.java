package ir.isar.isarapp;

import ir.isar.isarapp.util.AppContextUtils;
import ir.isar.isarapp.util.DataBaseUtils;
import ir.isar.isarapp.util.UiUtils;

import java.util.ResourceBundle;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp extends Application {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ResourceBundle messages = ResourceBundle.getBundle(getClass().getName());

    private static Stage stage;

    public static Stage stage() {
        return stage;
    }

    @Override
    public void start(Stage stage) {
        logger.info("Starting Application...");

        logger.info("Starting AppContext...");
        AppContextUtils.startAppContext();

        logger.info("Starting Database Web Console...");
        AppContextUtils.getBean(DataBaseUtils.class).startWebServer();

        MainApp.stage = stage;
        stage.setTitle(messages.getString("isar.MainApp.title"));
        AppContextUtils.getBean(UiUtils.class).redirect("/fxml/login.fxml");

        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        logger.info("Showing Main Stage...");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        logger.info("Stopping Application...");

        logger.info("Stopping Database Web Console...");
        AppContextUtils.getBean(DataBaseUtils.class).stopWebServer();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        demoMode();
        launch(args);
    }

    static void demoMode() {
        Thread demoThread = new Thread(() -> {
            try {
                synchronized (MainApp.class) {
                    MainApp.class.wait(5 * 60 * 1000);
                }
            } catch (InterruptedException e) {
            }
            Platform.runLater(() -> {
                stage.close();
            });
        });
        demoThread.setDaemon(true);
        demoThread.start();
    }
}
