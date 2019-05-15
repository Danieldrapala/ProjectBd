package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import sample.selecting.SelectDataChooser;
import sample.selecting.SelectedDataViewer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MainWindow implements SelectDataChooser {
    protected DBConnector dbConnector;

    protected Connection connection;

    protected PreparedStatement statement;
    public static Scene scene;
    @FXML
    Label label;
    private SelectedDataViewer selectedDataViewer;

    public MainWindow () {
        dbConnector = DBConnector.getInstance();
        connection = dbConnector.getConnection();
        statement = dbConnector.getStatement();
    }

    public static Scene getScene() {return scene;
    }
    @FXML
    public void delS(MouseEvent mouseEvent) {
        String id="";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("DeleteOpinion");
        dialog.setContentText("Podaj id  do usuniecia:");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            id = result.get();

        }
        try {
            dbConnector.setConnection(connection);
            String query = "CALL deletestudent('"+id+"');";
            PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
            statement.executeQuery();
            label.setText("nice op deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void delT(MouseEvent mouseEvent) {
        String id="";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("DeleteOpinion");
        dialog.setContentText("Podaj id  do usuniecia:");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            id = result.get();

        }
        try {
            dbConnector.setConnection(connection);
            String query = "CALL deleteteacher('"+id+"');";
            PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
            statement.executeQuery();
            label.setText("nice op deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void delL(MouseEvent mouseEvent) {
        String id="";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("DeleteOpinion");
        dialog.setContentText("Podaj id  do usuniecia:");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            id = result.get();

        }
        try {
            dbConnector.setConnection(connection);
            String query = "CALL deletelanguage('"+id+"');";
            PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
            statement.executeQuery();
            label.setText("nice op deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void delC(MouseEvent mouseEvent) {
        String id="";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("DeleteOpinion");
        dialog.setContentText("Podaj id  do usuniecia:");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            id = result.get();

        }
        try {
            dbConnector.setConnection(connection);
            String query = "CALL deletecourse('"+id+"');";
            PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
            statement.executeQuery();
            label.setText("nice op deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void modifycourse(MouseEvent mouseEvent) {
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
            choices.add("id_języka");
            choices.add("Wychowawca");
            choices.add("Liczba_miejsc");
            choices.add("Status_kursu");
            ChoiceDialog<String> dialog2 = new ChoiceDialog<>( null,choices);
            dialog2.setTitle("Choice Dialog");
            dialog2.setContentText("Choose column to change:");

            Optional<String> result2 = dialog.showAndWait();
            if (result2.isPresent()){
                kolumna=result2.get();
                TextInputDialog dialog3 = new TextInputDialog("");
                dialog3.setTitle("ChangeOpinion");
                dialog3.setContentText("Podaj nowa wartosc:");
                Optional<String> result3 = dialog3.showAndWait();
                if (result3.isPresent()) {
                    nowa_ocena = result3.get();
                }
            }

        }
        try {
            dbConnector.setConnection(connection);
            String query = "CALL modifycourse("+id+",'"+kolumna+ "','"+nowa_ocena+"');";
            PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
            statement.executeQuery();
            label.setText("nice op modded");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void modifyLang(MouseEvent mouseEvent) {
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
            choices.add("Nazwa");
            choices.add("Poziom");

            ChoiceDialog<String> dialog2 = new ChoiceDialog<>( null,choices);
            dialog2.setTitle("Choice Dialog");
            dialog2.setContentText("Choose column to change:");

            Optional<String> result2 = dialog.showAndWait();
            if (result2.isPresent()){
                kolumna=result2.get();
                TextInputDialog dialog3 = new TextInputDialog("");
                dialog3.setTitle("ChangeOpinion");
                dialog3.setContentText("Podaj nowa wartosc:");
                Optional<String> result3 = dialog3.showAndWait();
                if (result3.isPresent()) {
                    nowa_ocena = result3.get();
                }
            }

        }
        try {
            dbConnector.setConnection(connection);
            String query = "CALL modifylanguage("+id+",'"+kolumna+ "','"+nowa_ocena+"');";
            PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
            statement.executeQuery();
            label.setText("nice op modded");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

            Optional<String> result2 = dialog.showAndWait();
            if (result2.isPresent()){
                kolumna=result2.get();
                TextInputDialog dialog3 = new TextInputDialog("");
                dialog3.setTitle("ChangeOpinion");
                dialog3.setContentText("Podaj nowa wartosc:");
                Optional<String> result3 = dialog3.showAndWait();
                if (result3.isPresent()) {
                    nowa_ocena = result3.get();
                }
            }

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
    @FXML
    public void modifyteacher(MouseEvent mouseEvent) {
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
            choices.add("Email");
            choices.add("Telefon_Kontaktowy");


            ChoiceDialog<String> dialog2 = new ChoiceDialog<>( null,choices);
            dialog2.setTitle("Choice Dialog");
            dialog2.setContentText("Choose column to change:");

            Optional<String> result2 = dialog.showAndWait();
            if (result2.isPresent()){
                kolumna=result2.get();
                TextInputDialog dialog3 = new TextInputDialog("");
                dialog3.setTitle("ChangeOpinion");
                dialog3.setContentText("Podaj nowa wartosc:");
                Optional<String> result3 = dialog3.showAndWait();
                if (result3.isPresent()) {
                    nowa_ocena = result3.get();
                }
            }

        }
        try {
            dbConnector.setConnection(connection);
            String query = "CALL modifyteacher("+id+",'"+kolumna+ "','"+nowa_ocena+"');";
            PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
            statement.executeQuery();
            label.setText("nice op modded");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void addStudents(MouseEvent mouseEvent) {
        try {
           Parent root = FXMLLoader.load(getClass().getResource("AddStudent.fxml"));

        Scene scene = new Scene(root);
        Main.getPrimaryStage().setScene(scene);
        Main.getPrimaryStage().show();
        } catch (IOException e) {
        e.printStackTrace();
    }
    }
    @FXML
    public void addTeachers(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addTeacher.fxml"));

            Scene scene = new Scene(root);
            Main.getPrimaryStage().setScene(scene);
            Main.getPrimaryStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void addCourse(MouseEvent mouseEvent) {
        String id_nauczyciela="";
        String id_jezyka="";
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
                id_jezyka = result2.get();
            }
        }
        try {
            dbConnector.setConnection(connection);
            String query = "CALL addcourse('"+id_jezyka+ "','"+id_nauczyciela+"');";
            PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
            statement.executeQuery();
            label.setText("nice op modded");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void addLanguage(MouseEvent mouseEvent) {
        String nazwa="";
        String poziom="";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);

        dialog.setTitle("ChangeOpinion");
        dialog.setContentText("Podaj nazwe:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            nazwa = result.get();
            TextInputDialog dialog2 = new TextInputDialog("");
            dialog2.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);

            dialog2.setTitle("ChangeOpinion");
            dialog2.setContentText("Podaj poziom jezyka ocene:");
            Optional<String> result2 = dialog2.showAndWait();
            if (result2.isPresent()) {
                poziom = result2.get();
            }
        }
        try {
            dbConnector.setConnection(connection);
            String query = "CALL addlanguage('"+nazwa+ "','"+poziom+"');";
            PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
            statement.executeQuery();
            label.setText("nice ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void showOp(MouseEvent mouseEvent) {

        String query = "CALL selectopinionall();";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }
    @FXML
    public void showClasss(MouseEvent mouseEvent) {
        String id="";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("klasa by id");
        dialog.setContentText("Podaj id  klasy:");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            id = result.get();

        }
        String query = "CALL selectclasss('"+id+"');";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }
    @FXML
    public void showLanguage(MouseEvent mouseEvent) {

        String query = "CALL selectlanguagelevel();";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }

    @FXML
    public void courses(MouseEvent mouseEvent) {
        String query = "CALL selectcourses()";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }
    @FXML
    public void openC(MouseEvent mouseEvent) {
        String query = "CALL selectopencourses()";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }
    @FXML
    public void students(MouseEvent mouseEvent) {
        String query = "CALL selectallstudents()";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }
    @FXML
    public void studentsred(MouseEvent mouseEvent) {
        String query = "CALL selectstudentsreduced()";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }
    @FXML
    public void teachers(MouseEvent mouseEvent) {
        String query = "CALL selectallteachers()";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }
    @FXML
    public void userT(MouseEvent mouseEvent) {
        String query = "CALL selectusersteachers()";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }
    @FXML
    public void userS(MouseEvent mouseEvent) {
        String query = "CALL selectusersstudents()";
        selectedDataViewer = new SelectedDataViewer(query, this);
    }
    public void modifyusers(MouseEvent mouseEvent) {
        String id="";
        String kolumna="";
        String nowa_ocena="";
        String xd=mouseEvent.getPickResult().getIntersectedNode().getId();
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("ChangeOpinion");
        dialog.setContentText("Podaj login usera:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            id = result.get();
            List<String> choices = new ArrayList<>();
            choices.add("Login");
            choices.add("Hasło");

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
            }

        }
        try {
            dbConnector.setConnection(connection);
            String query = "CALL modifyuser('"+id+"','"+kolumna+ "','"+nowa_ocena+"','"+xd+"');";
            PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void createUser(MouseEvent mouseEvent) {
        String id;
        String pass="";
        String id_wbazie="";
        System.out.println(mouseEvent.getPickResult().getIntersectedNode().getId());
        String xd=mouseEvent.getPickResult().getIntersectedNode().getId();
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("ChangeOpinion");
        dialog.setContentText("Podaj login:");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            id = result.get();
            TextInputDialog dialog3 = new TextInputDialog("");
            dialog3.setTitle("ChangeOpinion");
            dialog3.setContentText("Podaj nowe haslo:");
            Optional<String> result3 = dialog3.showAndWait();
            if (result3.isPresent()) {
                pass = result3.get();

               TextInputDialog dialog4 = new TextInputDialog("");
                dialog4.setTitle("ChangeOpinion");
                dialog4.setContentText("Podaj id dla usera:");
                Optional<String> result4 = dialog4.showAndWait();
                if (result4.isPresent()) {
                    id_wbazie = result4.get();
                }
            }
            try {
                dbConnector.setConnection(connection);
                String query = "CALL createuser("+id+",'"+pass+ "','"+id_wbazie+"','"+xd+"');";
                PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
                statement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
    @FXML
    public void deleteus(MouseEvent mouseEvent) {
        String login = "";
        String xd = mouseEvent.getPickResult().getIntersectedNode().getId();

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("logindelete");
        dialog.setContentText("Podaj login zeby usunac usera :");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            login = result.get();

        }
        dbConnector.setConnection(connection);
        String query = "CALL createuser(" + login+"','" + xd + "');";
        try {
            PreparedStatement statement = dbConnector.getConnection().prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    private void makeBackupCopyHandler(MouseEvent mouseEvent) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "sample/BackupWindow.fxml")));
            Scene scene = new Scene(root);
            Main.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }



    @FXML
    private void restoreBackupCopyHandler(MouseEvent mouseEvent) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    "sample/RestoreWindow.fxml")));
            Scene scene = new Scene(root);
            Main.getPrimaryStage().setScene(scene);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }
    @FXML
    protected void backToStartingWindowHandler (MouseEvent mouseEvent) {
        Main.getPrimaryStage().setScene(Main.getScene());
    }
}
