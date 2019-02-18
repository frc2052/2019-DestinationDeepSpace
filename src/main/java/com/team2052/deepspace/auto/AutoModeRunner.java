package com.team2052.deepspace.auto;

import com.team2052.deepspace.auto.actions.Action;
import edu.wpi.first.wpilibj.Timer;


public class AutoModeRunner {
    private AutoThread autoThread;
    private Timer timer = new Timer();
    private Action action;

    public void setAction(Action action){
        this.action = action;
    }

    public void start() {//Initializes auto mode
        if(action != null) {
            timer.reset();
            timer.start();
            autoThread = new AutoThread(action);
            Thread thread = new Thread(autoThread);
            thread.start();
        }
    }

    public void stop() {//Stops auto mode
        autoThread.stop();
        autoThread = null;
    }

    public boolean isAutodone(){
        try {
            return !autoThread.isRunning();
        }catch(Exception e){
            return true;
        }
    }
}
