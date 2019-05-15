package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.*;


public class RootWind {

    private static Scene scene;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;

    @FXML
    private Label errorLabel;
    @FXML
    private CheckBox ch1;
    @FXML
    private CheckBox ch2;
    @FXML
    private CheckBox ch3;

    @FXML
    private void NewStudentButtonHandler(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Create.fxml"));

            scene = new Scene(root);
                        Main.getPrimaryStage().setScene(scene);
                        Main.getPrimaryStage().show();
        } catch (IOException e) {

        }
    }

        @FXML
    private void loginButtonHandler () {
        Main.Login = loginField.getText();
        String password = passwordField.getText();
        String zgadza="";
        boolean isLoggedIn = false;
        DBConnector dbConnector = DBConnector.getInstance();


       try {
           System.out.println(Main.Login);
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/szkolajezykowa",  Main.Login, password);
            dbConnector.setConnection(connection);
           try {
               DBConnector dbConnectortemp = DBConnector.getInstance();

               Connection connectiontemp = DriverManager.getConnection("jdbc:mysql://localhost:3306/SzkolaJezykowa", "root", "123");

           dbConnectortemp.setConnection(connectiontemp);
           String query = "CALL checkprivileges('"+Main.Login+"');";
           PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
           ResultSet resultSettemp=statement.executeQuery();
           if(resultSettemp.next())
               zgadza=resultSettemp.getString(1);

           connection.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
            isLoggedIn = true;
        } catch (SQLException ex) {
            loginField.clear();
            passwordField.clear();
            errorLabel.setText("BŁĘDNE HASŁO LUB LOGIN LUB UPRAWNIENIE!");
        }
            if (isLoggedIn) {
            try {



                Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));

                if(zgadza.equals("uczen")) {

                    String query = "CALL takeids('"+Main.Login+"');";
                    System.out.println(query);
                    PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery();
                    if(resultSet.next()) {
                        Main.id = resultSet.getString(1);
                        System.out.println(Main.id);

                    }
                    root = FXMLLoader.load(getClass().getResource("MainWindowForStudent.fxml"));
                }
                if(zgadza.equals("nauczyciel")) {
                    String query = "CALL takeidt('"+Main.Login+"');";
                    System.out.println(query);
                    PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery();
                    if(resultSet.next()) {
                        Main.id = resultSet.getString(1);
                        System.out.println(Main.id);

                    }
                    root = FXMLLoader.load(getClass().getResource("MainWindowForTeacher.fxml"));
                }
                if(zgadza.equals("administrator")) {
                    root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
                }

                scene = new Scene(root);
                Main.getPrimaryStage().setScene(scene);
                Main.getPrimaryStage().show();
            } catch (IOException | SQLException ex){
                System.err.println(ex.toString());
                System.exit(1);
            }
        }
    }

        public static Scene getScene () {
            return scene;
        }
    }


