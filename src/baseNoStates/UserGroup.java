package baseNoStates;

import java.util.List;

public class UserGroup {
    
    private String nameOfTheGroup;
    private Permission permission;
    
    public UserGroup(String nameOfTheGroup,Permission permission){
        this.nameOfTheGroup = nameOfTheGroup;
        this.permission = permission;
    }

    public Permission getPermission() {
        return permission;
    }
}
