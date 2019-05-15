package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import sample.selecting.SelectDataChooser;
import sample.selecting.SelectedDataViewer;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainWindowForTeacher implements SelectDataChooser {
    private static Scene scene;
    protected DBConnector dbConnector;

    protected Connection connection;

    protected PreparedStatement statement;

    protected String query;

    private SelectedDataViewer selectedDataViewer;

    private Label label;

    public MainWindowForTeacher() {
        dbConnector = DBConnector.getInstance();
        connection = dbConnector.getConnection();
        statement = dbConnector.getStatement();
    }

    @FXML
    public void selectallstudents(MouseEvent mouseEvent) {

        String query = "CALL selectallstudents();";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }
    @FXML
    public void selectclasst(MouseEvent mouseEvent) {

        String query = "CALL selectclasst('"+Main.id+"');";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }
    @FXML
    public void showClasss(MouseEvent mouseEvent) {
        String id="";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("pokaż uczniów w klasie");
        dialog.setContentText("podaj id Klasy ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            id = result.get();

        }
        String query = "CALL selectclasss('"+id+"');";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }

    @FXML
    public void modifyStudent(MouseEvent mouseEvent) {
        String id="";
        String kolumna="";
        String nowa_ocena="";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("ChangeOpinion");
        dialog.setContentText("Podaj id_nauczyciela:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            id = result.get();
            List<String> choices = new ArrayList<>();
            choices.add("Imię");
            choices.add("Nazwisko");
            choices.add("Adres");
            choices.add("Kod_Pocztowy");
            choices.add("Miasto");
            choices.add("Data_urodzenia");
            ChoiceDialog<String> dialog2 = new ChoiceDialog<>( null,choices);
            dialog2.setTitle("Choice Dialog");
            dialog2.setContentText("Choose column to change:");

            Optional<String> result2 = dialog2.showAndWait();
            if (result2.isPresent()){
                kolumna=result2.get();
                TextInputDialog dialog3 = new TextInputDialog("");
                dialog3.setTitle("ChangeOpinion");
                dialog3.setContentText("Podaj nowa wartosc:");
                Optional<String> result3 = dialog3.showAndWait();
                if (result3.isPresent()) {
                    nowa_ocena = result3.get();
                }
                try {
                    dbConnector.setConnection(connection);
                    String query = "CALL modifystudent("+id+",'"+kolumna+ "','"+nowa_ocena+"');";
                    PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
                    statement.executeQuery();
                    label.setText("nice op modded");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }
    @FXML
    public void showOp(MouseEvent mouseEvent) {

        String query = "CALL selectopinion('"+Main.id+"');";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }






    @FXML
    public void modifyLog(MouseEvent mouseEvent) {
        String haslo="";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("modify");
        dialog.setContentText("haslo nowe ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            haslo = result.get();

        }
        try {
            dbConnector.setConnection(connection);
            String query = "CALL modifyuser('"+Main.Login+"','Haslo','"+haslo+"','nauczyciel')";
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


