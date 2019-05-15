package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

public class Create {
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
    @FXML
    public Button btn;

    @FXML
    public void btnHandler() {

        Main.getPrimaryStage().setScene(Main.getScene());
    }
    @FXML
    public void InsertStudentHandler(){
        try {
            DBConnector dbConnector = DBConnector.getInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SzkolaJezykowa", "root", "123");
            dbConnector.setConnection(connection);
            String query = "CALL addstudent('"+pesel.getText()+"','"
                   + surname.getText()+"','"+name.getText()+"','"+date.getText()+"','"+liveplace.getText()+"','"+Code.getText()+"','"+town.getText()+"');";
            PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
            statement.executeQuery();
            String query2 = "CALL createuser('"+pesel.getText()+"','123',(SELECT id FROM Uczniowie WHERE PESEL="+pesel.getText()+"),'uczen');";
            PreparedStatement statement2 = dbConnector.getConnection().prepareStatement(query2);
            statement2.executeQuery();
            err.setText("Twój Login To:"+pesel.getText());
            err2.setText("Hasło:123" );
            connection.close();
            btn.setDisable(false);

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
