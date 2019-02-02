package com.team2052.deepspace.auto;

import com.team2052.deepspace.Constants;

public abstract class AutoMode extends AutoModeBase{

    protected StartDirection startDirection = Constants.Autonomous.defaultStartDirection;

    public StartDirection getStartDirection(){
        return startDirection;
    }
    public enum StartDirection{
        FORWARD(true),
        BACKWARD(false)
        ;

        public final boolean isForward;

        StartDirection(boolean isForward){
            this.isForward = isForward;
        }
    }
}
