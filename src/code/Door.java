package code;

import code.requests.RequestReader;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Door class that represents a door in the system
public class Door {
  private final String id;
  private DoorState doorState;
  private final Space from;
  private final Space to;
  private static final Clock clock = Clock.getClockInstance();
  //LogLevels
  private static final Logger log1 = LoggerFactory.getLogger("firstMilestoneClasses");
  private static final Logger logAC = LoggerFactory.getLogger("allClasses");

  // Constructor (all doors are initially unlocked and closed by default)
  public Door(String id, Space from, Space to) {
    this.id = id;
    this.from = from;
    this.to = to;
    this.from.addDoor(this);
    this.to.addDoor(this);
    doorState = new UnlockedClosed(this);
  }

  // Process a request
  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open

    String action = request.getAction();


    if (request.isAuthorized()) {
      doAction(action);
    } else {
      log1.info("not authorized");
      logAC.info("not authorized");

    }

    // Added some specific unauthorized actions
    // since some actions might be performed without authorization
    if (((this.doorState instanceof UnlockedOpen) && action.equals(Actions.CLOSE))
        || ((this.doorState instanceof UnlockedClosed) && action.equals(Actions.OPEN))
        || ((this.doorState instanceof OpenPropped) && action.equals(Actions.CLOSE))
        || ((this.doorState instanceof UnlockedShortly) && action.equals(Actions.CLOSE))
    ) {
      request.forceAuthorization();
      doAction(action);
      log1.info("action performed with no authorization");
      logAC.info("action performed with no authorization");
    }

    request.setDoorStateName(getStateName());
  }

  // Perform an action on the door
  // possibly changing its state
  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        if (doorState instanceof UnlockedClosed) {
          doorState.open();
        } else {
          log1.info("Door already open or locked.");
          logAC.info("Door already open or locked.");

        }
        break;

      case Actions.CLOSE:
        if (doorState instanceof UnlockedOpen
                || doorState instanceof UnlockedShortly
                || doorState instanceof OpenPropped) {
          doorState.close();
        } else {
          log1.info("Door already closed.");
          logAC.info("Door already closed.");
        }
        break;

      case Actions.LOCK:
        if (doorState instanceof UnlockedClosed) {
          doorState.lock();
        } else {
          log1.info("Door already locked or open.");
          logAC.info("Door already locked or open.");
        }
        break;

      case Actions.UNLOCK:
        if (doorState instanceof LockedClosed) {
          doorState.unlock();
        } else {
          log1.info("Door already unlocked or open.");
          logAC.info("Door already unlocked or open.");
        }
        break;

      case Actions.UNLOCK_SHORTLY:
        if (doorState instanceof LockedClosed) {
          doorState.unlockShortly();
        } else {
          log1.info("Door already open or unlocked.");
          logAC.info("Door already open or unlocked.");

        }
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  //returns if the door is closed
  public boolean isClosed() {
    return this.doorState.isClosed();
  }

  //returns the door id
  public String getId() {
    return id;
  }

  //returns the door state name
  public String getStateName() {
    return doorState.getName();
  }

  //sets the door state
  public void setState(DoorState ds) {
    this.doorState = ds;
  }

  //returns the door "From" area
  public Space getFrom() {
    return from;
  }

  //returns the door "To" area
  public Space getTo() {
    return to;
  }


  public Clock getClock() {
    return clock;
  }

  //returns the door as a string for JSON
  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + this.isClosed()
        + ", state=" + getStateName()
        + "}";
  }

  //returns the door as a JSON object
  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", isClosed());
    return json;
  }
}
