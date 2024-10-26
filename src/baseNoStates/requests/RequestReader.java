package baseNoStates.requests;

import baseNoStates.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class RequestReader implements Request {
  private final String credential; // who
  private final String action;     // what
  private final LocalDateTime now; // when
  private final String doorId;     // where
  private String userName;
  private boolean authorized;
  private final ArrayList<String> reasons; // why not authorized
  private String doorStateName;
  private boolean doorClosed;

  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    this.credential = credential;
    this.action = action;
    this.doorId = doorId;
    reasons = new ArrayList<>();
    this.now = now;
  }

  public void setDoorStateName(String name) {
    doorStateName = name;
  }

  public String getAction() {
    return action;
  }

  public boolean isAuthorized() {
    return authorized;
  }

  public void forceAuthorization(){
    this.authorized = true;
  }
  public void addReason(String reason) {
    reasons.add(reason);
  }


  @Override
  public String toString() {
    if (userName == null) {
      userName = "unknown";
    }
    return "Request{"
            + "credential=" + credential
            + ", userName=" + userName
            + ", action=" + action
            + ", now=" + now
            + ", doorID=" + doorId
            + ", closed=" + doorClosed
            + ", authorized=" + authorized
            + ", reasons=" + reasons
            + "}";
  }

  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("authorized", authorized);
    json.put("action", action);
    json.put("doorId", doorId);
    json.put("closed", doorClosed);
    json.put("state", doorStateName);
    json.put("reasons", new JSONArray(reasons));
    return json;
  }

  // see if the request is authorized and put this into the request, then send it to the door.
  // if authorized, perform the action.
    public void process() {
    User user = DirectoryUserGroups.findUserByCredential(credential);
    assert user != null : "user " + credential + " not found";
    Door door = DirectoryAreas.findDoorById(doorId);
    assert door != null : "door " + doorId + " not found";

    authorize(user, door);

    // this sets the boolean authorize attribute of the request
    door.processRequest(this);
    // even if not authorized we process the request, so that if desired we could log all
    // the requests made to the server as part of processing the request
    doorClosed = door.isClosed();
  }

  // the result is put into the request object plus, if not authorized, why not,
  // only for testing
  private void authorize(User user, Door door) {
    if (user == null) {
      authorized = false;
      addReason("user doesn't exists");
    } else {

      Area from = door.getFrom();
      Area to = door.getTo();

      assert from != null: door.getId()+" has no FROM space";
      assert to != null: door.getId()+" has no TO space";

      LocalDateTime time = now;


      boolean permission1 = user.getUserGroup().getPermission().checkPermission(from, time);
      boolean permission2 = user.getUserGroup().getPermission().checkPermission(to, time);

      boolean permissionToUnlock = user.getUserGroup().getPermission().canLockAndUnlock();

      if(this.getAction().equals(Actions.LOCK) || this.getAction().equals(Actions.UNLOCK)){
        authorized = permission1 && permission2 && permissionToUnlock;
      }else {
        authorized = permission1 && permission2;
      }


      }
  }
}

