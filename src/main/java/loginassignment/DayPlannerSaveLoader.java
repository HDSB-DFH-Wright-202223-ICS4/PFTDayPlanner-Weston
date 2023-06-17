package loginassignment;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DayPlannerSaveLoader {
    public static List<DayPlannerItem> Items = new ArrayList<>(); //Feed every item in the list to this list.
    public DayPlanner rootClass;


    public void AddToItems(DayPlannerItem item) throws IOException {
        Items.add(item);
        FlushData(); //Clear file before adding new stuff.
        for (DayPlannerItem dayPlannerItem : Items) {//Iterate through items.
            WriteData(dayPlannerItem.text, dayPlannerItem.date, dayPlannerItem.importance);
        }
    }

    public void RemoveFromItems(DayPlannerItem item) throws IOException {
        Items.remove(item);
        FlushData(); //Clear file before adding new stuff.
        for (DayPlannerItem dayPlannerItem : Items) {//Iterate through items.
            WriteData(dayPlannerItem.text, dayPlannerItem.date, dayPlannerItem.importance);
        }
    }

    public void ReadData() throws IOException {//The method that will read the data and then add the data to the to-do list.
        Path of = Path.of("plannersavedata.txt");
        String content = Files.readString(of);

        List<String> lines = Files.readAllLines(of, Charset.defaultCharset());
        for (String line : lines) {
            int LineCommaIndex = 0;
            int LastCommaIndex = 0;
            String text = null;
            String date = null;
            String importance = null;

            //System.out.println(line);

            for(int i=0; i <line.length(); i++)
            {
                if (line.charAt(i) == ',') {

                    switch(LineCommaIndex)
                    {
                        case 0:
                            if(text == null) //Since there's no comma for the first section, get the substring before the comma, I is exclusive
                                text = line.substring(0,i);
                            break;
                        case 1:
                            if(date == null)
                                date = line.substring(text.length() + 1, i);
                            LastCommaIndex = i;
                            break;
                    }
                    LineCommaIndex += 1;

                }

                if(i == line.length() - 1)
                    if(importance == null)
                        importance = line.substring(LastCommaIndex + 1, line.length());
            }

            System.out.println(text + "," + date + "," + importance);
            rootClass.AddEntry(new DayPlannerItem(text, date, importance));
        }
    }

    public void FlushData() throws IOException { //Seperate method so it can be called once before writing new data. (Clears the whole file)
        FileWriter writer = new FileWriter("plannersavedata.txt", false);
        writer.close();
    }


    public void WriteData(String text, String date, String importance) throws IOException {//This is the method that will write to the txt savedata file.
        BufferedWriter writer = new BufferedWriter(new FileWriter("plannersavedata.txt", true));
        writer.append(text + "," + date + "," + importance + "\n");
        writer.close();
    }



}
