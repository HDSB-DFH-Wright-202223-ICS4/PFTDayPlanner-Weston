package loginassignment;

import java.util.Date;

public class DayPlannerItem {
        public String text;
        public String date;

        public DayPlannerItem()
        {
            this.text = "";
            this.date = new String();
        }
        public DayPlannerItem(String Text, String Date) {
            this.text = Text;
            this.date = Date;
        }

        public String getText()
        {
            return text;
        }

        public void setText(String text)
        {
            this.text = text;
        }

        public String getDate()
        {
            return date;
        }

        public void setDate(String date)
        {
            this.date = date;
        }

        //Make new getters/setters for new variables, so they can be read from DayPlanner.java
}
