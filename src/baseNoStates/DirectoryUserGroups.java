package baseNoStates;

import jdk.jshell.execution.DirectExecutionControl;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

// This class is a directory of all userGroups
// and users in the system.
public final class DirectoryUserGroups {
  private static final List<User> users = new ArrayList<>();
  private static final List<UserGroup> userGroups = new ArrayList<>();
  public static void makeUserGroups() {
    //FOR ALL GROUPS :
    // - Create List of Areas
    // - Create a Schedule Object
    // - Use them to create a Permission Object
    // - Create a UserGroup Object with said Permision
    // - Create Users in the group


    // BLANK :
    // users without any privilege, just to keep temporally users instead of deleting them,
    // this is to withdraw all permissions but still to keep user data to give back
    // permissions later
    // Access permissions (areas and schedules)
    List<Area> accessBlank = new ArrayList<>();
    LocalDateTime periodStartBlank = LocalDateTime.of(2000, 1, 1, 0, 0);
    LocalDateTime periodEndBlank = LocalDateTime.of(2000, 1, 1, 0, 0);
    LocalTime timeStartBlank = LocalTime.of(0,0);
    LocalTime timeEndBlank = LocalTime.of(0,0);
    List<DayOfWeek> daysBlank = new ArrayList<>();
    // Empty schedule
    Schedule scheduleBlank = new Schedule(timeStartBlank,timeEndBlank,daysBlank,periodStartBlank,periodEndBlank);
    // Empty permission
    Permission permissionBlank = new Permission(accessBlank, scheduleBlank,false);
    // New Group
    UserGroup blankUserGroup = new UserGroup("blank", permissionBlank);
    userGroups.add(blankUserGroup);
    // Add Users to group
    users.add(new User("Bernat", "12345", blankUserGroup));
    users.add(new User("Blai", "77532", blankUserGroup));


    // EMPLOYEES :
    // Sep. 1 this year to Mar. 1 next year
    // week days 9-17h
    // just shortly unlock
    // ground floor, floor1, exterior, stairs (this, for all), that is, everywhere but the parking
    Area groundFloor = DirectoryAreas.findAreaById("ground floor");
    Area floor1 = DirectoryAreas.findAreaById("floor1");
    Area exterior = DirectoryAreas.findAreaById("exterior");
    Area stairs = DirectoryAreas.findAreaById("stairs");
    List<Area> accessEmployees = new ArrayList<>(Arrays.asList(groundFloor, floor1, exterior, stairs));
    // Schedule
    LocalDateTime periodStartEmployees = LocalDateTime.of(2024, 9, 1, 0, 0);
    LocalDateTime periodEndEmployees = LocalDateTime.of(2025, 3, 1, 0, 0);
    LocalTime timeStartEmployess = LocalTime.of(9,0);
    LocalTime timeEndEmployess = LocalTime.of(17,0);
    List<DayOfWeek> daysEmployees = new ArrayList<>(Arrays.asList(
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY));
    Schedule scheduleEmployees= new Schedule(timeStartEmployess,timeEndEmployess,daysEmployees,periodStartEmployees,periodEndEmployees);
    // Permission
    Permission permissionEmployess = new Permission(accessEmployees, scheduleEmployees,false);
    // UserGroup
    UserGroup employeesUserGroup = new UserGroup("employees", permissionEmployess);
    userGroups.add(blankUserGroup);
    // Add Users to group
    users.add(new User("Ernest", "74984",employeesUserGroup));
    users.add(new User("Eulalia", "43295", employeesUserGroup));

    // MANAGERS :
    // Sep. 1 this year to Mar. 1 next year
    // week days + saturday, 8-20h
    // all actions
    // all spaces
    Area building = DirectoryAreas.findAreaById("building");
    List<Area> accessManagers = new ArrayList<>(Arrays.asList(building));
    // Schedule
    LocalDateTime periodStartManagers = LocalDateTime.of(2024, 9, 1, 0, 0);
    LocalDateTime periodEndManagers = LocalDateTime.of(2025, 3, 1, 0, 0);
    LocalTime timeStartManagers = LocalTime.of(8,0);
    LocalTime timeEndManagers = LocalTime.of(20,0);
    List<DayOfWeek> daysManagers = new ArrayList<>(Arrays.asList(
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY,
        DayOfWeek.SATURDAY,
        DayOfWeek.SUNDAY));
    Schedule scheduleManagers= new Schedule(timeStartManagers, timeEndManagers, daysManagers, periodStartManagers, periodEndManagers);
    // Permission
    Permission permissionManagers = new Permission(accessManagers, scheduleManagers, true);
    UserGroup managerGroup = new UserGroup("managers", permissionManagers);
    // Add Users to group
    users.add(new User("Manel", "95783", managerGroup));
    users.add(new User("Marta", "05827", managerGroup));


    // ADMIN :
    // always=Jan. 1 this year to 2100
    // all days of the week
    // all actions
    // all spaces
    List<Area> accessAdmin = new ArrayList<>(Arrays.asList(building));
    // Schedule
    LocalDateTime periodStartAdmin = LocalDateTime.of(2024, 1, 1, 0, 0);
    LocalDateTime periodEndAdmin = LocalDateTime.of(2100, 1, 1, 0, 0);
    LocalTime timeStartAdmin = LocalTime.of(0, 0);
    LocalTime timeEndAdmin = LocalTime.of(23, 59);
    List<DayOfWeek> daysAdmin = new ArrayList<>(Arrays.asList(
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY,
        DayOfWeek.SATURDAY,
        DayOfWeek.SUNDAY));
    Schedule scheduleAdmin = new Schedule(timeStartAdmin, timeEndAdmin, daysAdmin, periodStartAdmin, periodEndAdmin);
    // Permission
    Permission permissionAdmin = new Permission(accessAdmin, scheduleAdmin, true);
    UserGroup adminGroup = new UserGroup("admin", permissionAdmin);
    users.add(new User("Ana", "11343", adminGroup));
  }

  // Find a user by their credential
  public static User findUserByCredential(String credential) {
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        return user;
      }
    }
    System.out.println("user with credential " + credential + " not found");
    return null; // otherwise we get a Java error
  }

}
