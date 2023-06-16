package loginassignment;

import java.util.Date;

public class DayPlannerItem {
        public String text;
        public String date;
        public String importance;

        public DayPlannerItem()
        {
            this.text = "";
            this.date = new String();
            this.importance = new String();
        }
        public DayPlannerItem(String Text, String Date, String Importance) {
            this.text = Text;
            this.date = Date;
            this.importance = Importance;
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

        public String getImportance() {return importance;}
        public void setImportance(String importance) {this.importance = importance; }

        //Make new getters/setters for new variables, so they can be read from DayPlanner.java
}
