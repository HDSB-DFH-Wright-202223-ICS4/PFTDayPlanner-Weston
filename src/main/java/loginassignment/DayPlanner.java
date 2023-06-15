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
    static TableView<DayPlannerItem> table = new TableView<DayPlannerItem>();

    @FXML private TextField stringTextField;

    @FXML private DatePicker datePickerField;
    @FXML private TableView mainTableView;

    @FXML private TableColumn textColumn;


    @FXML
    private void AddNewElement(ActionEvent event)
    {
        event.consume();
        System.out.println(stringTextField.getText());
        //System.out.println(datePickerField.getAccessibleText());
        mainTableView.getItems().add(new DayPlannerItem(stringTextField.getText(), 0, false));
        textColumn.setCellValueFactory(new PropertyValueFactory<>("text"));
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

    private static void AddEntry(DayPlannerItem entry)
    {
        table.getItems().add(entry);
    }

    private static String RetrieveText()
    {//This will retrieve the text from the main input field.
        return "";
    }


    @Override
    public void start(Stage stage) throws Exception {

    }
}
