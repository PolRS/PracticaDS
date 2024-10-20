package baseNoStates;

public class User {
  private final String name;
  private final String credential;
  private UserGroup group;

  public User(String name, String credential, UserGroup group) {
    this.name = name;
    this.credential = credential;
    this.group = group;
  }

  public String getCredential() {
    return credential;
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }

  public UserGroup getUserGroup(){
    return this.group;
  }
}
