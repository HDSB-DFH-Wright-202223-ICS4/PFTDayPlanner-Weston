package loginassignment;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginMenu extends Application {

    public static TextField usernameField;
    private PasswordField passwordField;

    private Label asteriskOne;
    private Label asteriskTwo;

    public static void main(String[] args)
    {
        launch();
    }
 
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login");

        usernameField = new TextField();

        passwordField = new PasswordField();

        Label header = new Label("Log-in");
        header.setStyle("-fx-font-size: 25px;");
        Label label0 = new Label("Username:");

        Label label1 = new Label("Password:");

        Button submitButton = new Button("Login");
        Button createAccountButton = new Button("Sign Up");

         asteriskOne = new Label("*");
         asteriskTwo = new Label("*");
        asteriskOne.setStyle("-fx-text-fill: red; -fx-font-size: 25px;");
        asteriskTwo.setStyle("-fx-text-fill: red; -fx-font-size: 25px;");
        asteriskOne.setDisable(true);
        asteriskTwo.setDisable(true);

        ReadData.UpdateSavedInfo();

        submitButton.setOnAction(e -> onSubmit());
        createAccountButton.setOnAction(e -> RegisterMenu.EnableRegisterMenu());

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(header, 1, 0);
        gridPane.add(label0,0,1);
        gridPane.add(usernameField, 1,1);
        gridPane.add(asteriskOne, 2, 1);
        gridPane.add(label1, 0, 2);
        gridPane.add(passwordField, 1, 2);
        gridPane.add(asteriskTwo, 2, 2);
        gridPane.add(submitButton, 0, 4);
        gridPane.add(createAccountButton, 1,4);
        gridPane.setStyle("-fx-font-size: 14px; -fx-background-color: grey;");

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void onSubmit() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (!Objects.equals(username, "") & !Objects.equals(password, "")) {
            if (ReadData.SavedUsernames.toArray().length > 0) {
                for (int i = 0; i < ReadData.SavedUsernames.toArray().length; i++) {
                    if (Objects.equals(username, ReadData.SavedUsernames.get(i)) && Objects.equals(password, ReadData.SavedPasswords.get(i))) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Logged In!");
                        alert.showAndWait();
                        passwordField.clear();
                        passwordField.requestFocus();
                        passwordField.positionCaret(0);
                        DayPlanner.EnableDayPlannerMenu();
                        Stage stage = (Stage) passwordField.getScene().getWindow();
                        stage.close();
                    } else if (i == ReadData.SavedUsernames.toArray().length - 1) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Account doesn't exist!");
                        alert.showAndWait();
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Account doesn't exist!");
                alert.showAndWait();
            }
        }
        else {
            if(Objects.equals(username, "") && Objects.equals(password, "")) {
                asteriskOne.setDisable(false);
                asteriskTwo.setDisable(false);
            }
            else if(Objects.equals(username, ""))
            {
                asteriskOne.setDisable(false);
            }
            else if(Objects.equals(password, ""))
            {
                asteriskTwo.setDisable(false);
            }
        }
    }
}
