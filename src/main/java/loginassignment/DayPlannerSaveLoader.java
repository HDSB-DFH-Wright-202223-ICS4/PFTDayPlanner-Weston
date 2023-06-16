package loginassignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayPlannerSaveLoader {
    public static List<DayPlannerItem> Items;

    public static void ReadData()
    {//The method that will read the data and then add the data to the to-do list.

    }

    public static void WriteData(String text, String date) throws IOException {//This is the method that will write to the txt savedata file.
        BufferedWriter writer = new BufferedWriter(new FileWriter("plannersavedata.txt", true));
        if(Items.size() > 0)
        {
            for(int i = 0; i < Items.size(); i++)
            {
                writer.append("/" + text + "," + date + "/");
            }
        }
        writer.close();
    }



}
