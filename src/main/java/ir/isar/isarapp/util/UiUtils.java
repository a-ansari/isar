package ir.isar.isarapp.util;

import ir.isar.isarapp.MainApp;
import ir.isar.isarapp.ui.ctrl.BaseController;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.BuilderFactory;
import javafx.util.Callback;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author User
 */
@Singleton
public class UiUtils {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private BuilderFactory builderFactory;
    private Callback<Class<?>, Object> guiceControllerFactory;

    @Inject
    public void postConstruct() {
        builderFactory = new JavaFXBuilderFactory();
        guiceControllerFactory = clazz -> AppContextUtils.getInjector().getInstance(clazz);
    }

    public void redirect(String page) {
        redirect(page, null, null);
    }

    public void redirect(String page, Object data) {
        redirect(page, data, null);
    }

    public void redirect(String page, BaseController controller) {
        redirect(page, null, controller);
    }

    public void redirect(String page, Object data, BaseController controller) {
        logger.info("redirect to {}", page);

        FXMLLoader fxmlLoader;
        Pane root;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource(page), null, builderFactory, guiceControllerFactory);
            if (controller != null) {
                fxmlLoader.setController(controller);
            }
            root = fxmlLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException("IOException for page=" + page, ex);
        }

        controller = fxmlLoader.getController();
        controller.setData(data);
        controller.afterCompose();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        try {
            if (MainApp.stage().isMaximized()) {
                Scene prevScene = MainApp.stage().getScene();
                root.setPrefHeight(prevScene.getHeight());
                root.setPrefWidth(prevScene.getWidth());
            }
        } catch (NullPointerException ex) {
        }

        MainApp.stage().setScene(scene);
        controller.afterSceneShown();
    }

    public void clearAllFields(BaseController controller) {
        logger.info("clearallfield: {}", controller.getClass().getDeclaredFields().length);
        for (Field field : controller.getClass().getDeclaredFields()) {
            try {
                if (Control.class.isAssignableFrom(field.getType())) {
                    field.setAccessible(true);
                    Object fieldObject = field.get(controller);
                    if (fieldObject instanceof TextField) {
                        TextField textField = (TextField) fieldObject;
                        textField.setText("");
                    } else if (fieldObject instanceof ComboBox) {
                        ComboBox combobox = (ComboBox) fieldObject;
                        combobox.setValue(null);
                    } else if (fieldObject instanceof Label) {
                        Label label = (Label) fieldObject;
                        label.setText("");
                    } else {
                        logger.info("no matching type: {}", field.getName());
                    }
                    field.setAccessible(false);
                }
            } catch (IllegalAccessException ex) {
                logger.warn("Exception in field=" + field.getName(), ex);
            }
        }
    }

    public void writeToModel(BaseController controller, Serializable model) {
        for (Field field : controller.getClass().getDeclaredFields()) {
            try {
                if (Control.class.isAssignableFrom(field.getType())) {
                    String fieldName = extractNameFromWidget(field.getName());
                    String value;
                    field.setAccessible(true);
                    Object fieldObject = field.get(controller);
                    if (fieldObject instanceof TextField) {
                        TextField textField = (TextField) fieldObject;
                        value = textField.getText();
                    } else if (fieldObject instanceof ComboBox) {
                        ComboBox combobox = (ComboBox) fieldObject;
                        value = (String) combobox.getValue();
                    } else {
                        logger.info("no matching type: {}", field.getName());
                        continue;
                    }
                    field.setAccessible(false);

                    Field modelField = model.getClass().getDeclaredField(fieldName);
                    modelField.setAccessible(true);
                    modelField.set(model, value);
                    modelField.setAccessible(false);
                }
            } catch (NoSuchFieldException ex) {
                logger.warn("NoSuchFieldException in field={}", field.getName());
            } catch (IllegalAccessException ex) {
                logger.warn("Exception in field=" + field.getName(), ex);
            }
        }
    }

    public void readFromModel(BaseController controller, Serializable model) {
        for (Field field : controller.getClass().getDeclaredFields()) {
            try {
                if (Control.class.isAssignableFrom(field.getType())) {
                    String fieldName = extractNameFromWidget(field.getName());

                    Field modelField = model.getClass().getDeclaredField(fieldName);
                    modelField.setAccessible(true);
                    String value = (String) modelField.get(model);
                    modelField.setAccessible(false);

                    field.setAccessible(true);
                    Object fieldObject = field.get(controller);
                    if (fieldObject instanceof TextField) {
                        TextField textField = (TextField) fieldObject;
                        textField.setText(value);
                    } else if (fieldObject instanceof ComboBox) {
                        ComboBox combobox = (ComboBox) fieldObject;
                        combobox.setValue(value);
                    } else {
                        logger.info("no matching type: {}", field.getName());
                        continue;
                    }
                    field.setAccessible(false);
                }
            } catch (NoSuchFieldException ex) {
                logger.warn("NoSuchFieldException in field={}", field.getName());
            } catch (IllegalAccessException ex) {
                logger.warn("Exception in field=" + field.getName(), ex);
            }
        }
    }

    public String extractNameFromWidget(String widgetName) {
        return widgetName.substring(3, 4).toLowerCase() + widgetName.substring(4);
    }

}
