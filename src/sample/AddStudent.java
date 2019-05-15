package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStudent {
    protected DBConnector dbConnector;

    protected Connection connection;

    protected PreparedStatement statement;
    @FXML
    public TextField surname;
    @FXML
    public TextField date;
    @FXML
    public TextField pesel;
    @FXML
    public TextField town;
    @FXML
    public TextField liveplace;
    @FXML
    public TextField name;
    @FXML
    public TextField Code;
    @FXML
    public Label err;
    @FXML
    public Label err2;
    public AddStudent () {
        dbConnector = DBConnector.getInstance();
        connection = dbConnector.getConnection();
        statement = dbConnector.getStatement();
    }


    @FXML
    protected void backToStartingWindowHandler (MouseEvent mouseEvent) {
        Main.getPrimaryStage().setScene(MainWindow.getScene());
    }

    @FXML
    public void addStudent(){
        try {

            String query = "CALL addstudent('"+pesel.getText()+"','"
                    + surname.getText()+"','"+name.getText()+"','"+date.getText()+"','"+liveplace.getText()+"','"+Code.getText()+"','"+town.getText()+"');";
            PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
            statement.executeQuery();
            err.setText("udało się To:"+pesel.getText());
            Main.getPrimaryStage().setScene(MainWindow.getScene());

        } catch (SQLException e) {
            surname.clear();
            pesel.clear();
            name.clear();
            date.clear();
            liveplace.clear();
            Code.clear();
            town.clear();
            err.setText("bledne dane sprobuj jeszcze raz");
            e.printStackTrace();
        }

    }

}
