package baseNoStates;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
public class Clock extends Observable {
    private Timer timer;
    //private ArrayList<UnlockedShortly> SubscribedDoors;
    public Clock(Clock c){
        timer = c.timer;
    }
    public Clock(){
        timer = new Timer();
    }

    public void startClock(){
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tick();
            }
        }, (long) 0, (long) 1000);
    }

    public void stopClock(){
        timer.cancel();
    }
    private void tick(){
        setChanged();
        notifyObservers();
    }
}
