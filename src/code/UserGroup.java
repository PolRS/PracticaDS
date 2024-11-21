package code;

/**
 * Class that represensts a Group of users in the program.
 * This class is used to group users together and assign them different permissions.
 */
public class UserGroup {
    private String nameOfTheGroup;
    private Permission permission;

    public UserGroup(String nameOfTheGroup, Permission permission) {
        this.nameOfTheGroup = nameOfTheGroup;
        this.permission = permission;
    }

    //returns the permission of the group
    public Permission getPermission() {
        return permission;
    }
}
