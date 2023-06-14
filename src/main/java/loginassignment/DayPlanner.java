package loginassignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class DayPlanner {
    static TableView<DayPlannerItem> table = new TableView<DayPlannerItem>();


    public static void EnableDayPlannerMenu() {

        Parent root = null;

        try {
            root = FXMLLoader.load(DayPlanner.class.getResource("/dayplanner.fxml"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Label header = new Label("Day Planner");
        header.setStyle("-fx-font-size: 25px;");

        BorderPane pane = new BorderPane();



        TableColumn stringColumn = new TableColumn<DayPlannerItem, String>("Text");
        stringColumn.setCellValueFactory(new PropertyValueFactory<DayPlannerItem, String>("text"));

        TableColumn dateColumn = new TableColumn<DayPlannerItem, Integer>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<DayPlannerItem, Integer>("date"));

        TableColumn completeColumn = new TableColumn<DayPlannerItem, Boolean>("Complete");
        completeColumn.setCellValueFactory(new PropertyValueFactory<DayPlannerItem, Boolean>("complete"));

        table.getColumns().add(stringColumn);
        table.getColumns().add(dateColumn);
        table.getColumns().add(completeColumn);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getItems().add(new DayPlannerItem("Hello", 1, false));
        table.getItems().add(new DayPlannerItem("Bye", 1, false));
        table.getItems().add(new DayPlannerItem("Fortnite", 1, false));

        Button addButton = new Button("Add Entry");
        addButton.setOnAction(e -> AddEntry(new DayPlannerItem("Hi Hi", 0, false)));
        pane.setBottom(addButton);

        pane.setTop(table);
        pane.setRight(header);



        //GridPane gridPane = new GridPane();
       //gridPane.setPadding(new Insets(10, 10, 10, 10));
        //gridPane.setVgap(10);
       // gridPane.setHgap(10);
       // gridPane.add(header, 1, 0);
        //gridPane.add(table, 1, 1);

       // gridPane.setStyle("-fx-font-size: 14px; -fx-background-color: grey;");


        Scene scene = new Scene(root, 600, 470);
        Stage stage = new Stage();
        stage.setTitle("Day Planner");
        stage.setScene(scene);
        stage.show();
    }

    private static void AddEntry(DayPlannerItem entry)
    {
        table.getItems().add(entry);
    }

}
