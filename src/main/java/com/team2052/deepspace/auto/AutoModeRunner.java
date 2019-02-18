package com.team2052.deepspace.auto;

import com.team2052.deepspace.auto.actions.Action;
import edu.wpi.first.wpilibj.Timer;


public class AutoModeRunner {
    private AutoRunnable autoRunnable;
    private Timer timer = new Timer();
    private Action action;
    private Thread thread = null;

    private static AutoModeRunner instance = null;
    public static AutoModeRunner getInstance() {
        if (instance == null) {
            try {
                instance = new AutoModeRunner();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create AutoodeRunner: " + exc.getMessage());
                exc.printStackTrace();
            }
        }
        return instance;
    }

    public void setAction(Action action){
        //System.out.println("setting action");
        this.action = action;
    }

    public void start() {//Initializes auto mode
        //System.out.println("is action ! null: " + (action != null));
        if(action != null) {
            timer.reset();
            timer.start();
            autoRunnable = new AutoRunnable(action);
            thread = new Thread(autoRunnable);
            thread.start();
        }
    }

    public void stop() {//Stops auto mode
        if(autoRunnable != null) {
            autoRunnable.stop();
        }
        autoRunnable = null;
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
