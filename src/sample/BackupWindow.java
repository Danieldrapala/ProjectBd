package sample;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class BackupWindow {

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private void makeBackstoreHandler (MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Zapisz kopię");
        File file = fileChooser.showSaveDialog(Main.getPrimaryStage());
        if (file != null) {
            String dbname = "szkolajezykowa";

            String executeCmd = "mysqldump --routines -u "+login.getText()+" -p"+password.getText()
                    +" "+dbname+" -r "+ file.getAbsolutePath();

            try {
                Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                int processComplete = runtimeProcess.waitFor();
                if (processComplete == 0) {
                    System.out.println("Backup taken successfully");
                    Main.getPrimaryStage().setScene(RootWind.getScene());
                    clearTextFields();
                } else {
                    System.out.println("Could not take mysql backup");
                    clearTextFields();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }


        }
        Main.getPrimaryStage().setScene(RootWind.getScene());

    }

    private void clearTextFields () {
        login.clear();
        password.clear();
    }

    @FXML
    protected void backToStartingWindowHandler (MouseEvent mouseEvent) {
        Main.getPrimaryStage().setScene(RootWind.getScene());

    }


}
