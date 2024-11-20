package baseNoStates;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that represensts a Schedule.
 * All schedules are assigned to a permission.
 * All the schedules have weekdays, and timeframes assigned to them.
 * They also have a valid period of time.
 */
public class Schedule {
    //Daily schedule
    private LocalTime timeStart;
    private LocalTime timeEnd;
    // Validity of the schedule in the calendar level
    private LocalDateTime beginingValid;
    private LocalDateTime endValid;
    //Days of the week that the schedule is valid
    private List<DayOfWeek> daysOfWeek;


    public Schedule(LocalTime timeStart, LocalTime timeEnd, List<DayOfWeek>  weekDay, LocalDateTime beginingValid, LocalDateTime endValid) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.beginingValid = beginingValid;
        this.endValid = endValid;
        this.daysOfWeek = weekDay;
    }

    //Checks if a moment is inside the schedule
    public boolean checkIfInSchedule(LocalDateTime moment) {
        //Checks date in calendar level
        boolean validDate = (moment.isAfter(beginingValid) && moment.isBefore(endValid));

        //Checks Time of the day
        boolean rightTime = moment.toLocalTime().isAfter(timeStart) && moment.toLocalTime().isBefore(timeEnd);

        //Checks if the day of the week is valid
        boolean rightDayOfTheWeek = daysOfWeek.contains(moment.getDayOfWeek());

        return validDate && rightTime && rightDayOfTheWeek;
    }
}
