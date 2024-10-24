package baseNoStates;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

// Clock class that makes it possible to 
// create a clock that ticks every second
public class Clock extends Observable {
    private Timer timer;
    public Clock(Clock c){
        timer = c.timer;
    }
    public Clock(){
        timer = new Timer();
    }

    // Starts the clock
    public void startClock(){
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tick();
            }
        }, (long) 0, (long) 1000);
    }

    // Stops the clock
    public void stopClock(){
        timer.cancel();
    }

    // Method that notifies the observers
    private void tick(){
        setChanged();
        notifyObservers();
    }
}
