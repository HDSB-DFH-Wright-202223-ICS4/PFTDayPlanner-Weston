package loginassignment;

public class DayPlannerItem {
        public String text;
        public int date;
        public boolean completeCheck;

        public DayPlannerItem()
        {
            this.text = "";
            this.date = 0;
            this.completeCheck = false;
        }
        public DayPlannerItem(String Text, int Date, boolean CompleteCheck) {
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

        public int getDate()
        {
            return date;
        }

        public void setDate(int date)
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
