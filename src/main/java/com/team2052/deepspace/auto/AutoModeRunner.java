package com.team2052.deepspace.auto;

import com.team2052.deepspace.auto.actions.Action;
import edu.wpi.first.wpilibj.Timer;


public class AutoModeRunner {
    private AutoRunnable autoRunnable;
    private Timer timer = new Timer();
    private Action action;
    private Thread thread = null;

    public void setAction(Action action){
        this.action = action;
    }

    public void start() {//Initializes auto mode
        if(action != null) {
            timer.reset();
            timer.start();
            autoRunnable = new AutoRunnable(action);
            thread = new Thread(autoRunnable);
            thread.start();
        }
    }

    public void stop() {//Stops auto mode
        autoRunnable.stop();
        autoRunnable = null;
        action = null;
        thread = null;
    }

    public boolean isAutodone(){
        try {
            return !autoRunnable.isRunning();
        }catch(Exception e){
            return true;
        }
    }
}
