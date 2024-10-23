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

        System.out.println(areasConceded.size());
        System.out.println(areasConceded.getFirst().getId());

        for (Area a : areasConceded) {

            assert a != null : "a ins NULL";
            System.out.println(area.getId());
            assert area != null : "Area is NULL ";

            if (a.belongsInside(area)) {
                permissionGrantedArea = true;
            }
        }

        boolean permissionGrantedTime = schedules.checkIfInSchedule(moment);

        System.out.println("LOCAL :"+permissionGrantedArea);
        System.out.println("TEMPO : "+permissionGrantedTime);

        return (permissionGrantedArea && permissionGrantedTime);

    }



}
