package baseNoStates;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;


public class Door {
  private final String id;
  private DoorState doorState;


  public Door(String id) {
    this.id = id;
    doorState = new UnlockedClosed(this);
  }

  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        if(doorState instanceof UnlockedClosed){
          doorState.open();
        }else{
          System.out.println("Door already open or locked.");
        }
        break;

      case Actions.CLOSE:
        if(doorState instanceof UnlockedOpen || doorState instanceof UnlockedShortly || doorState instanceof OpenPropped){
          doorState.close();
        }else{
          System.out.println("Door already closed.");
        }
        break;

      case Actions.LOCK:
        if(doorState instanceof UnlockedClosed){
          doorState.lock();
        }else{
          System.out.println("Door already locked or open.");
        }
        break;

      case Actions.UNLOCK:
        if(doorState instanceof LockedClosed){
          doorState.unlock();
        }else{
          System.out.println("Door already unlocked or open.");
        }
        break;

      case Actions.UNLOCK_SHORTLY:
        if(doorState instanceof LockedClosed){
          doorState.open();
        }else{
          System.out.println("Door already open or unlocked.");
        }
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public boolean isClosed() {
    return this.doorState.isClosed();
  }

  public String getId() {
    return id;
  }

  public String getStateName() {
    return doorState.getName();
  }

  public void setState(DoorState ds){
    this.doorState = ds;
  }


  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + this.isClosed()
        + ", state=" + getStateName()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", isClosed());
    return json;
  }
}
