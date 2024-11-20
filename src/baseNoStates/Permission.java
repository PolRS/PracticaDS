package baseNoStates;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

/**
 * Class that represents a permission attributed to a userGroup.
 * All the permissions are attributed to a userGroup.
 * The permissions are used to check if a userGroup can access a certain area at a certain time.
 * All permissions have a valid Schedule.
 * All permissions have a list of areas that the userGroup can access.
 */
public class Permission {
    // Areas that the userGroup can access
    private List<Area> areasConceded;
    // Schedule of the permission
    private Schedule schedules;
    // If the userGroup can lock and unlock the doors
    private boolean canLockAndUnlock;

    public Permission(List<Area> areasConceded, Schedule schedules, boolean canUnlock) {
        this.areasConceded = areasConceded;
        this.schedules = schedules;
        this.canLockAndUnlock = canUnlock;
    }

    //check if the userGroup has permission to access the area at a certain moment
    public boolean checkPermission(Area area, LocalDateTime moment) {
        boolean permissionGrantedArea = false;
        // for all areas conceded,
        // check if the area belongs inside the area conceded
        for (Area a : areasConceded) {
            System.out.println(area.getId());
            if (a.belongsInside(area)) {
                permissionGrantedArea = true;
            }
        }

        // check if the moment is inside the schedule
        boolean permissionGrantedTime = schedules.checkIfInSchedule(moment);

        // both must be matched
        return (permissionGrantedArea && permissionGrantedTime);

    }

    // check if the userGroup can lock and unlock the doors
    public boolean canLockAndUnlock() {
        return canLockAndUnlock;
    }
}
