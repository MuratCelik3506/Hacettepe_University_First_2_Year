import java.util.Date;

public class Assignment implements Comparable {
    private String name;
    private String start;
    private int duration;
    private int importance;
    private boolean maellard;

    /*
        Getter methods
     */
    public String getName() {
        return this.name;
    }

    public String getStartTime() {
        return this.start;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getImportance() {
        return this.importance;
    }

    public boolean isMaellard() {
        return this.maellard;
    }

    /**
     * Finish time should be calculated here
     *
     * @return calculated finish time as String
     */
    public String getFinishTime() { // calculate finih time
        int finishtime = 0;
        String[] arr = this.start.split(":");
        int hour =Integer.valueOf(arr[0]);
        int minute = Integer.valueOf(arr[1]);
        hour += this.duration;
        String h_str = String.format("%02d", hour);
        String m_str = String.format("%02d", minute);
        return ""+h_str+":"+m_str;
    }

    /**
     * Weight calculation should be performed here
     *
     * @return calculated weight
     */
    public double getWeight() { // calculate weight of assignment
        int tmp_mael = maellard ? 1001 : 1;

        return (double) (importance * tmp_mael) / (double) duration;
    }

    /**
     * This method is needed to use {@link java.util.Arrays#sort(Object[])} ()}, which sorts the given array easily
     *
     * @param o Object to compare to
     * @return If self > object, return > 0 (e.g. 1)
     * If self == object, return 0
     * If self < object, return < 0 (e.g. -1)
     */
    @Override
    public int compareTo(Object o) { // if we sort array, sorting methods use this method for correct sorting
        String[] this_finish = this.getFinishTime().split(":");
        String[] o_finish = ((Assignment) o).getFinishTime().split(":");

        int hour_this = Integer.valueOf(this_finish[0]);
        int min_this = Integer.valueOf(this_finish[1]);
        int hour_other = Integer.valueOf(o_finish[0]);
        int min_other = Integer.valueOf(o_finish[1]);

        if(hour_this>hour_other)
            return 1;
        else if(hour_this<hour_other)
            return -1;
        else{
            if(min_this>min_other)
                return 1;
            else if(min_this<min_other)
                return -1;
        }

        return 0;
    }

    /**
     * @return Should return a string in the following form:
     * Assignment{name='Refill vending machines', start='12:00', duration=1, importance=45, maellard=false, finish='13:00', weight=45.0}
     */
    @Override
    public String toString() {
        return "Assignment{name='" + this.name +"', start='" + this.start + "', duration="+this.duration+", importance="+this.importance+", maellard="+this.maellard+", finish='"+this.getFinishTime()+"', weight="+ this.getWeight()+"}";
    }



    public int compareTimeFinishtoStart(Object o) { // compare one assignment finish time and one assignment start time
        String[] this_finish = this.getFinishTime().split(":");
        String[] o_finish = ((Assignment) o).getStartTime().split(":");
        int hour_this = Integer.valueOf(this_finish[0]);
        int min_this = Integer.valueOf(this_finish[1]);
        int hour_other = Integer.valueOf(o_finish[0]);
        int min_other = Integer.valueOf(o_finish[1]);

        if (hour_this > hour_other)
            return 1;
        else if (hour_this < hour_other)
            return -1;
        else {
            if (min_this > min_other)
                return 1;
            else if (min_this < min_other)
                return -1;
        }
        return 0;
    }

}