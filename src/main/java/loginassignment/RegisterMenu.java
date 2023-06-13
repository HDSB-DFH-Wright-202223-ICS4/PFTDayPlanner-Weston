package loginassignment;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.CharArrayReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class RegisterMenu extends Application {

    private static PasswordField CreateAccountPswdField;
    private static PasswordField CreateAccountPswdConfirmField;
    private static TextField CreateAccountUsernameField;

    @Override
    public void start(Stage stage) throws Exception {

    }


    public static void EnableRegisterMenu()
    {
        CreateAccountUsernameField = new TextField();
        CreateAccountPswdField = new PasswordField();
        CreateAccountPswdConfirmField = new PasswordField();

        Label header = new Label("Register");
        header.setStyle("-fx-font-size: 25px;");
        Label label0 = new Label("Username:");
        Label label1 = new Label("Password:");
        Label label2 = new Label("Confirm Password:");
        Button createAccountButton = new Button("Sign Up");
        createAccountButton.setOnAction(e -> RegisterAccount());

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(header, 1, 0);
        gridPane.add(label0,0,1);
        gridPane.add(CreateAccountUsernameField, 1, 1);
        gridPane.add(label1, 0, 2);
        gridPane.add(CreateAccountPswdField, 1, 2);
        gridPane.add(label2, 0, 3);
        gridPane.add(CreateAccountPswdConfirmField, 1, 3);
        gridPane.add(createAccountButton, 1, 4);

        gridPane.setStyle("-fx-font-size: 14px; -fx-background-color: grey;");



        Scene scene = new Scene(gridPane, 300, 215);
        Stage stage = new Stage();
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }

    private static void RegisterAccount()
    {
        String u = CreateAccountUsernameField.getText(); //username
        String pw = CreateAccountPswdField.getText(); //password
        String cpw = CreateAccountPswdConfirmField.getText(); //confirm pass

        if(!Objects.equals(u, "") && !Objects.equals(pw, "") && !Objects.equals(cpw, ""))
        {
            if(u.length() > 6 && pw.length() > 6) {

                if (Objects.equals(pw, cpw)) {

                    if (DoesPswdContainSpecialChars(pw)) {

                        if (!ReadData.SavedUsernames.contains(u)) {

                            ReadData.SavedPasswords.add(pw);
                            ReadData.SavedUsernames.add(u);
                            InfoBox("Successfully Created Account!");
                            LoginMenu.usernameField.appendText(u);

                            try {
                                WriteData.WriteNewAccount(u, pw);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            Stage stage = (Stage) CreateAccountPswdField.getScene().getWindow();
                            stage.close();
                        } else { //If the username is taken.
                            ErrorBox("That username is taken!");
                        }
                    } else { //Must have special character & special letter.
                        ErrorBox("Password must contain a special character & a capital letter.");
                    }
                } else { //Passwords don't match.
                    ErrorBox("Passwords don't match!");
                }
            }else { //Username & Password length.
                ErrorBox("Username & Password need to be longer than 6 characters!");
            }
        }
        else { //One of the fields is empty.
            ErrorBox("A field is empty!");
        }
    }

    public static void InfoBox(String text)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
    public static void ErrorBox(String text)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public static Boolean DoesPswdContainSpecialChars(String username)
    {
        Boolean hasCapital = false;
        Boolean hasSymbol = false;

        for(int i =0; i <username.length(); i++)
        {
            char character = username.charAt(i);
            if(Character.isUpperCase(character))
                hasCapital = true;
            else if(character == '!' || character == '@' || character == '#' || character == '$' || character == '%' || character == '^' || character == '&' || character == '*')
                hasSymbol = true;
        }

        if(hasCapital && hasSymbol)
        return true;
        else
        return false;
    }
}
