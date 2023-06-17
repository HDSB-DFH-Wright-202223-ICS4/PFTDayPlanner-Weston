package loginassignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayPlannerSaveLoader {
    public static List<DayPlannerItem> Items = new ArrayList<>(); //Feed every item in the list to this list.


    public static void AddToItems(DayPlannerItem item) throws IOException {
        Items.add(item);
        FlushData();
        for(int i=0; i < Items.size(); i++)
        {
            WriteData(Items.get(i).text, Items.get(i).date, Items.get(i).importance);
        }
    }

    public static void RemoveFromItems(DayPlannerItem item)
    {
        Items.remove(item);
    }

    public static void ReadData()
    {//The method that will read the data and then add the data to the to-do list.

    }

    public static void FlushData() throws IOException {
        FileWriter writer = new FileWriter("plannersavedata.txt", false);
        writer.close();
    }


    public static void WriteData(String text, String date, String importance) throws IOException {//This is the method that will write to the txt savedata file.
        BufferedWriter writer = new BufferedWriter(new FileWriter("plannersavedata.txt", true));
        writer.append("/" + text + "," + date + "," + importance + "/");
        writer.close();
    }



}
