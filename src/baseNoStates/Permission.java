package baseNoStates;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

public class Permission {

    private List<Area> areasConceded;
    private Schedule schedules;

    public Permission(List<Area> areasConceded, Schedule schedules){
        this.areasConceded = areasConceded;
        this.schedules = schedules;
    }

    public boolean checkPermission(Area area, LocalDateTime moment){
        boolean permissionGrantedArea = false;
        Iterator<Area> it = areasConceded.iterator();
        while (it.hasNext()){
            Area a = it.next();
            assert a != null : "Null Area";
            if(a.belongsInside(area)){
                permissionGrantedArea = true;
            }
        }

        boolean permissionGrantedTime = schedules.checkIfInSchedule(moment);

        return (permissionGrantedArea && permissionGrantedTime);

    }



}
