package base;

public enum ArrestDuration {
    WEEK, MONTH, YEAR;

    @Override
    public String toString() {
         if(this.equals(ArrestDuration.WEEK)) {
            return "Week";
        } else if(this.equals(ArrestDuration.MONTH)) {
             return "Month";
         }else  {
             return "Year";
         }
    }
}
