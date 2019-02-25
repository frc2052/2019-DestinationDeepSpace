package com.team2052.deepspace.auto;

import com.team2052.deepspace.auto.actions.SeriesAction;
import edu.wpi.first.wpilibj.Timer;


public class AutoModeRunner {
    private AutoRunnable autoRunnable;
    private Timer timer = new Timer();
    private SeriesAction action;
    private Thread thread = null;
    private boolean hasStarted = false;

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

    //TODO: REVIEW - should we create a master action?
    //TODO: REVIEW - maybe we should not set the action, then run it later.  Perhaps start should have an action parameter
    public void setAction(SeriesAction action){
        //System.out.println("setting action");
        this.action = action;
    }

    public void start() {//Initializes auto mode
        //TODO: REVIEW - Should we make sure the action has never been started before we try to (continue) run it
        //TODO: REVIEW - maybe se should call stop to be sure nothing is already running?

        //System.out.println("is action ! null: " + (action != null));
        if(action != null) {
            //TODO: REVIEW - what is this timer for?
            timer.reset();
            timer.start();
            autoRunnable = new AutoRunnable(action);
            //In java, a thread will run only as long as the Runnable it is created with is running
            //as soon as the runnable exits is run() method, the thread dies
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

    //TODO: REVIEW - Rename to capitalize Done, perhaps rename to IsFinished to match other places we check to see if something is done?
    public boolean isAutodone(){
        try {
            return !autoRunnable.isRunning();
        }catch(Exception e){
            return true;
        }
    }
}
