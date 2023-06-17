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
    @FXML private ChoiceBox importancePickerField; //Used to grab the selected importance (and to add the types to it)


    @FXML private TableView mainTableView; //Used to add new entries.
    @FXML private TableColumn textColumn; //Used to update the field's data.
    @FXML private TableColumn dateColumn; //Used to update the field's data as well.
    @FXML private TableColumn importanceColumn; //Used to update the field's data also.


    @FXML
    public void initialize()
    {//This is called when the FXML VBox is launched.
        AddItemsToImportancePicker();
        //DayPlannerSaveLoader.ReadDate();
    }

    @FXML
    private void AddNewElement(ActionEvent event) throws IOException { //This is directly attached to the 'Add New' Button in the FMXL scene by the #name.
        event.consume();
        System.out.println(CanSubmitNewField());
        TryAddNewEntry();
        //DayPlannerSaveLoader.WriteData();
    }

    private void TryAddNewEntry() throws IOException {
        if(CanSubmitNewField() == true) { //If you meet the proper requirements to add to the list.
            AddEntry(new DayPlannerItem(RetrieveText(), datePickerField.getValue().toString(), importancePickerField.getSelectionModel().getSelectedItem().toString()));
        }else
        {//Isn't catching the DatePicker being un-chosen so there's an alternative try/catch that gets it down below.
            ShowAlert("All Fields Need To Be Populated!");
        }

    }


    @FXML
    private void RemoveSelectedElement(ActionEvent event)
    {//Called directly by the remove button on the FXML, removes your current selected item.
        event.consume();
        //!!Maybe do a check here if getSelectedItem() != null, and else throw up error warning popup.
        if(mainTableView.getSelectionModel().getSelectedItem() != null) {
            mainTableView.getItems().removeAll(mainTableView.getSelectionModel().getSelectedItem());
            ItemRemoved((DayPlannerItem) mainTableView.getSelectionModel().getSelectedItem());
        }
        else
            ShowAlert("No Item Selected!");
    }

    @FXML
    private void UpdateSelectedElement(ActionEvent event)
    {//This replaces the data of whatever element you have currently selected with whatever new data you have entered into the text-fields.
        event.consume();

        int index = mainTableView.getSelectionModel().getSelectedIndex(); //Retrieve the current selected index.
        mainTableView.getItems().remove(index); //Remove the item
        mainTableView.getItems().add(index, new DayPlannerItem(RetrieveText(), datePickerField.getValue().toString(), importancePickerField.getSelectionModel().getSelectedItem().toString()));
        //^ Re-insert the new item at the old index to appear as it's been replaced.
        textColumn.setText("");
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

        //DayPlanner.AddItems();
    }

    public void AddItemsToImportancePicker()
    {//This populates the Importance Picker Field (On Initialization), Due to it not being possible in SceneBuilder.
        importancePickerField.getItems().addAll("Not Important", "Barely Important", "Semi Important", "Pretty Important", "Important", "Very Important"); //Populate list.
        importancePickerField.getSelectionModel().select("Not Important");//Selects a default option
    }


    private void AddEntry(DayPlannerItem entry) throws IOException { //Add new entry to the FXML UI component, then update the Cell's values to display correctly.
        mainTableView.getItems().add(entry);
        textColumn.setCellValueFactory(new PropertyValueFactory<>("text"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        importanceColumn.setCellValueFactory(new PropertyValueFactory<>("importance"));
        ItemAdded(entry);
    }

    private void ItemAdded(DayPlannerItem item) throws IOException {//This is used to add items to item list in savedata class.
        DayPlannerSaveLoader.AddToItems(item);
    }
    private void ItemRemoved(DayPlannerItem item)
    {
        DayPlannerSaveLoader.RemoveFromItems(item);
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
            ShowAlert("All Fields Need To Be Populated!");
        }
        return false; //In any other case, it'll be false as well.
    }

    private void ShowAlert(String text)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING, text, ButtonType.OK);
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
