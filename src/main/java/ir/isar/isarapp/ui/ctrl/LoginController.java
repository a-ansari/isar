package ir.isar.isarapp.ui.ctrl;

import ir.isar.isarapp.MainApp;
import ir.isar.isarapp.biz.LoginBiz;
import ir.isar.isarapp.excp.LoginException;
import ir.isar.isarapp.util.UiUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javax.inject.Inject;
import org.apache.commons.io.filefilter.WildcardFileFilter;

public class LoginController extends BaseController {

    public class UiModel {

        public final ObjectProperty<SingleSelectionModel<String>> database = new SimpleObjectProperty();
        public final StringProperty username = new SimpleStringProperty();
        public final StringProperty password = new SimpleStringProperty();
    }
    private final UiModel uiModel = new UiModel();

    @Inject
    private LoginBiz loginBiz;

    @Inject
    private UiUtils uiUtils;

    @FXML
    private ImageView imgLogo;

    @FXML
    private ComboBox cmbDatabase;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Text txtLoginResult;

    @FXML
    protected void login(ActionEvent event) {
        final String database = uiModel.database.get().getSelectedItem();
        final String userName = uiModel.username.get();
        final String password = uiModel.password.get();
        txtLoginResult.setText(messages.getString("isar.LoginController.tryingToLogin"));

        Platform.runLater(() -> {
            try {
                MainApp.stage().getScene().setCursor(Cursor.WAIT);
                loginBiz.tryLogin(database, userName, password);
                uiUtils.redirect("/fxml/studentInfo.fxml");
            } catch (LoginException ex) {
                txtLoginResult.setText(ex.getMessage());
            } finally {
                MainApp.stage().getScene().setCursor(Cursor.DEFAULT);
            }
        });
    }

    @Override
    public void afterCompose() {
        uiModel.database.bindBidirectional(cmbDatabase.selectionModelProperty());
        uiModel.username.bindBidirectional(txtUserName.textProperty());
        uiModel.password.bindBidirectional(txtPassword.textProperty());
        loadImageLogo();
        loadListOfDatabases();
        uiModel.username.set("admin");
    }

    @Override
    public void afterSceneShown() {
        txtPassword.requestFocus();
    }

    private void loadImageLogo() {
        try {
            File file = new File("logo.png");
            Image image = new Image(new FileInputStream(file));
            imgLogo.setImage(image);
        } catch (FileNotFoundException ex) {
        }
    }

    private void loadListOfDatabases() {
        FilenameFilter filter = new WildcardFileFilter("*.h2.db");
        File currentDir = new File(".");
        for (File file : currentDir.listFiles(filter)) {
            cmbDatabase.getItems().add(universityName(file));
        }
        if (cmbDatabase.getItems().size() == 0) {
            cmbDatabase.getItems().add("Sharif");
        }
        if (cmbDatabase.getItems().size() == 1) {
            cmbDatabase.getSelectionModel().selectFirst();
        }
    }

    private String universityName(File file) {
        try {
            String path = file.getPath();
            int index = path.lastIndexOf("/");
            if (index == -1) {
                index = path.lastIndexOf("\\");
            }
            path = path.substring(index + 1);
            path = path.substring(0, path.indexOf(".h2.db"));
            return path;
        } catch (StringIndexOutOfBoundsException ex) {
            return "";
        }
    }
}
