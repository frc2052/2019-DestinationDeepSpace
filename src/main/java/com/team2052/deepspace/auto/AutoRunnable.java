package com.team2052.deepspace.auto;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.actions.SeriesAction;

public class AutoRunnable implements Runnable{
    SeriesAction action;
    boolean running;
    //TODO: REVIEW - Consider only allowing this to run a MasterAction?
    public AutoRunnable(SeriesAction action){
        this.action = action;
    }

    @Override
    public void run() {
        running = true;
        action.start();
        while (running && !action.isFinished()){ //while the action is not done and the automode is running
            action.update();
            try { //can throw an exception so you must check if it does so code doesn't crash
                Thread.sleep(Constants.Autonomous.kloopPeriodMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        action.done();
        running = false;
    }

    public void stop(){
        running = false;
        action.forceStop();
    }

    public boolean isRunning(){
        return running;
    }
}
