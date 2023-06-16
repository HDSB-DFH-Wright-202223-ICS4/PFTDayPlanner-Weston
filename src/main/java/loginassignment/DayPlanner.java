package loginassignment;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.application.Application;
import java.io.IOException;
import java.net.URL;

public class DayPlanner extends Application {

    @FXML private TextField stringTextField; //Used to grab the string from the text field.
    @FXML private DatePicker datePickerField;//Used to grab the date (converted to string) from text field.

    @FXML private TableView mainTableView; //Used to add new entries.
    @FXML private TableColumn textColumn; //Used to update the field's data.
    @FXML private TableColumn dateColumn; //Used to update the field's data as well.


    @FXML
    private void AddNewElement(ActionEvent event)
    { //This is directly attached to the 'Add New' Button in the FMXL scene by the #name.
        event.consume();
        System.out.println(CanSubmitNewField());
        if(CanSubmitNewField() == true) { //If you meet the proper requirements to add to the list.
            AddEntry(new DayPlannerItem(RetrieveText(), datePickerField.getValue().toString()));
        }else
        {//Isn't catching the DatePicker being unchosen so there's an alternative try/catch that gets it down below.
            ShowFieldsPopulatedAlert();
        }
    }

    @FXML
    private void RemoveSelectedElement(ActionEvent event)
    {//Called directly by the remove button on the FXML, removes your current selected item.
        event.consume();
        //!!Maybe do a check here if getSelectedItem() != null, and else throw up error warning popup.
        mainTableView.getItems().removeAll(mainTableView.getSelectionModel().getSelectedItem());
    }


    public static void EnableDayPlannerMenu() throws IOException
    {//Retrieve the FXML file from the "C:/FXML/dayplanner.fxml" location, then load it onto a VBOX, then launch the window.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:///C:/FXML/dayplanner.fxml")); //MUST BE IN CORRECT FOLDER

        VBox vbox = loader.<VBox>load();

        Stage stage = new Stage();
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }

    private void AddEntry(DayPlannerItem entry)
    { //Add new entry to the FXML UI component, then update the Cell's values to display correctly.
        mainTableView.getItems().add(entry);
        textColumn.setCellValueFactory(new PropertyValueFactory<>("text"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private Boolean CanSubmitNewField()
    {//If you're able to submit new data (i.e. you have all the fields filled)
        try { //Doing this try catch because for some odd reason the date-picker field value gets null ref instead of default string, so I do this to catch for a warning popup.
            if (stringTextField.getText().length() > 0 && datePickerField.getValue().toString().length() > 0) { //If their contents are both not null.
                return true; //Then you can.
            } else {
                return false; //Then you can't.
            }
        }
        catch(Exception exc) { //Throw up the alert also over here (Turned the alert into a method to call because it's used more than once)
            ShowFieldsPopulatedAlert();
        }
        return false; //In any other case, it'll be false as well.
    }

    private void ShowFieldsPopulatedAlert()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING, "All fields need to be populated.", ButtonType.OK);
        alert.show();
    }



    private String RetrieveText()
    {//This will retrieve the text from the main input field.
        return stringTextField.getText();
    }


    @Override
    public void start(Stage stage) throws Exception {

    }
}
