package com.team2052.deepspace.actions;

import com.team2052.deepspace.subsystems.GroundIntake;

public class GroundIntakeAction implements Action {
    private GroundIntake groundIntake;
    private boolean finished = false;
    public GroundIntakeAction(){
        start();
    }

    public void done(){
        finished = true;
    }

    public boolean isFinished(){
        return finished;
    }

    public void start(){
        groundIntake.groundIntake();
    }

    public void update(){
        if(groundIntake.groundIntakeState()) {//True by default unless groundintake.groundintake is run
            done();
        }
    }



}
