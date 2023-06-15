package loginassignment;

import java.util.Date;

public class DayPlannerItem {
        public String text;
        public String date;
        public boolean completeCheck;

        public DayPlannerItem()
        {
            this.text = "";
            this.date = new String();
            this.completeCheck = false;
        }
        public DayPlannerItem(String Text, String Date, boolean CompleteCheck) {
            this.text = Text;
            this.date = Date;
            this.completeCheck = CompleteCheck;
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

        public boolean getComplete()
        {
            return completeCheck;
        }

        public void setComplete(Boolean isComplete)
        {
            this.completeCheck = isComplete;
        }
}
