package baseNoStates;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Schedule {
    private LocalTime timeStart;
    private LocalTime timeEnd;

    private LocalDateTime beginingValid;
    private LocalDateTime endValid;

    private List<DayOfWeek> daysOfWeek;


    public Schedule (LocalTime timeStart, LocalTime timeEnd, List<DayOfWeek>  weekDay, LocalDateTime beginingValid, LocalDateTime endValid){
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.beginingValid = beginingValid;
        this.endValid = endValid;
        this.daysOfWeek = weekDay;
    }

    public boolean checkIfInSchedule(LocalDateTime moment){
        //Checks date in calendar level
        boolean validDate = (moment.isAfter(beginingValid) && moment.isBefore(endValid));
        //This includes the cases where it's equal

        //Checks Time of the day
        boolean rightTime = moment.toLocalTime().isAfter(timeStart) && moment.toLocalTime().isBefore(timeEnd);

        boolean rightDayOfTheWeek = daysOfWeek.contains(moment.getDayOfWeek());

        return validDate && rightTime && rightDayOfTheWeek;
    }
}
