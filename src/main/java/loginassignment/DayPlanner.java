package loginassignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Objects;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;

import static javafx.application.Application.launch;

public class DayPlanner extends Application {

    @FXML private TextField stringTextField; //Used to grab the string from the text field.
    @FXML private DatePicker datePickerField;//Used to grab the date (converted to string) from text field.

    @FXML private TableView mainTableView; //Used to add new entries.
    @FXML private TableColumn textColumn; //Used to update the field's data.
    @FXML private TableColumn dateColumn; //Used to update the field's data as well.


    @FXML
    private void AddNewElement(ActionEvent event)
    {
        event.consume();
        System.out.println(CanSubmitNewField());
        if(CanSubmitNewField() == true) {
            AddEntry(new DayPlannerItem(RetrieveText(), datePickerField.getValue().toString(), false));
        }else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "All fields need to be populated.", ButtonType.OK);
            alert.show();
        }
    }

    public static void EnableDayPlannerMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:///C:/FXML/dayplanner.fxml")); //MUST BE IN CORRECT FOLDER

        VBox vbox = loader.<VBox>load();

        Stage stage = new Stage();
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }

    private void AddEntry(DayPlannerItem entry)
    {
        mainTableView.getItems().add(entry);
        textColumn.setCellValueFactory(new PropertyValueFactory<>("text"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private Boolean CanSubmitNewField()
    {
        if(stringTextField.getText().length() > 0 && datePickerField.getValue().toString().length() > 0) {
            return true;
        }
        else {
            return false;
        }
    }


    private String RetrieveText()
    {//This will retrieve the text from the main input field.
        return stringTextField.getText();
    }


    @Override
    public void start(Stage stage) throws Exception {

    }
}
