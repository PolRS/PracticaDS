package baseNoStates;

/**
 * Class that represensts a User.
 */
public class User {
  private final String name;
  private final String credential;
  private UserGroup group;

  public User(String name, String credential, UserGroup group) {
    this.name = name;
    this.credential = credential;
    this.group = group;
  }

  //returns the credential of the user
  public String getCredential() {
    return credential;
  }

  //turns the user into a string for JSON parsing
  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }

  //returns the group of the user
  public UserGroup getUserGroup(){
    return this.group;
  }
}
