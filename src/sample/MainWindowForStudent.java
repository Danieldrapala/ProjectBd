package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import sample.selecting.SelectDataChooser;
import sample.selecting.SelectedDataViewer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class MainWindowForStudent implements SelectDataChooser {
    private static Scene scene;

    protected DBConnector dbConnector;

    protected Connection connection;

    protected PreparedStatement statement;

    protected SelectedDataViewer selectedDataViewer;

    protected String query;
    @FXML
    public Label label;

    public MainWindowForStudent() {
        dbConnector = DBConnector.getInstance();
        connection = dbConnector.getConnection();
        statement = dbConnector.getStatement();
    }


    @FXML
    public void showTinfo(MouseEvent mouseEvent) {
        String id="";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("teachercontact");
        dialog.setContentText("Podaj id nauczyciela zeby zobaczyc dane kontaktowe:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            id = result.get();

        }
        String query = "CALL selectteacherinfo('"+id+"');";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }
    @FXML
    public void showOp(MouseEvent mouseEvent) {
        String id="";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("DeleteOpinion");
        dialog.setContentText("Podaj id nauczyciela zeby zobaczyc ocenę:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            id = result.get();

        }
        String query = "CALL selectopinion('"+id+"');";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }
    @FXML
    public void showClasses(MouseEvent mouseEvent) {
        String query = "CALL selectclass('"+Main.id+"');";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }
    @FXML
    public void showLanguage(MouseEvent mouseEvent) {

            String query = "CALL selectlanguagelevel();";
            selectedDataViewer = new SelectedDataViewer(query, this);
    }
    @FXML
    public void opModifyhan(MouseEvent mouseEvent) {
        String id_nauczyciela="";
        String nowa_ocena="";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);

        dialog.setTitle("ChangeOpinion");
        dialog.setContentText("Podaj id_nauczyciela:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            id_nauczyciela = result.get();
            TextInputDialog dialog2 = new TextInputDialog("");
            dialog2.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);

            dialog2.setTitle("ChangeOpinion");
            dialog2.setContentText("Podaj nowa ocene:");
            Optional<String> result2 = dialog2.showAndWait();
            if (result2.isPresent()) {
                nowa_ocena = result2.get();
            }
        }
        try {
            dbConnector.setConnection(connection);
            String query = "CALL modifyopinion("+Main.id+",'"+id_nauczyciela+ "','"+nowa_ocena+"');";
            PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
            statement.executeQuery();
            label.setText("nice op modded");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void openC(MouseEvent mouseEvent) {
                query = "CALL selectopencourses()";
                selectedDataViewer = new SelectedDataViewer(query, this);
            }

    @FXML
    public void addOp(MouseEvent mouseEvent) {
        String id_nauczyciela="";
        String nowa_ocena="";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("addOp");
        dialog.setContentText("Podaj id_nauczyciela:");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            id_nauczyciela = result.get();
            TextInputDialog dialog2 = new TextInputDialog("");
            dialog2.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);

            dialog2.setTitle("addop");
            dialog2.setContentText("Podaj nowa ocene:");
            Optional<String> result2 = dialog2.showAndWait();
            if (result2.isPresent()) {
                nowa_ocena = result2.get();
            }
        }
        try {
            dbConnector.setConnection(connection);
            String query = "CALL addopinion('"+ Main.id+"','"+id_nauczyciela+ "','"+nowa_ocena+"');";
            PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
            statement.executeQuery();
            label.setText("nice op added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void delOp(MouseEvent mouseEvent) {
            String id="";
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("DeleteOpinion");
            dialog.setContentText("Podaj id Opinii do usuniecia:");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);

        Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                id = result.get();

            }
            try {
                dbConnector.setConnection(connection);
                String query = "CALL deleteopinion('"+id+"');";
                PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
                statement.executeQuery();
                label.setText("nice op deleted");

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    @FXML
    public void joinC(MouseEvent mouseEvent) {
        String id="";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("JoinCourse");
        dialog.setContentText("Podaj id kursu do którego chcesz dołączyć");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            id = result.get();

        }
        try {
            dbConnector.setConnection(connection);
            String query = "CALL joincourse('"+id+"','"+ Main.id+"');";
            PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
            statement.executeQuery();
            label.setText("nice joined");

        } catch (SQLException e) {
            label.setText(e.getMessage());
        }
    }
    @FXML
    public void leaveC(MouseEvent mouseEvent) {
        String id="";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("JoinCourse");
        dialog.setContentText("Podaj id kursu od którego chcesz odejść");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            id = result.get();

        }
        try {
            dbConnector.setConnection(connection);
            String query = "CALL leavecourse('"+id+"',(Select id_ucznia FROM UsersStudents WHERE LOGIN='"+Main.Login+"'));";
            PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
            statement.executeQuery();
            label.setText("nice you left");

        } catch (SQLException e) {
            label.setText("podaj ten w ktorym jestes...");
        }
    }

    @FXML
    public void modifyLog(MouseEvent mouseEvent) {
        String haslo="";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
        dialog.setTitle("modify");
        dialog.setContentText("haslo nowe ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            haslo = result.get();

        }
        try {
            dbConnector.setConnection(connection);
            String query = "CALL modifyuser('"+Main.Login+"','Haslo','"+haslo+"','uczen')";
            PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
            statement.executeQuery();
            label.setText("nice you changed haslo to"+haslo);

        } catch (SQLException e) {
            e.printStackTrace();
            label.setText("nie udalo sie?");
        }
    }
    @FXML
    public void backToStartingWindowHandler(MouseEvent mouseEvent) {

        Main.getPrimaryStage().setScene(Main.getScene());

    }
    public static Scene getScene () {
        return scene;
    }

}
