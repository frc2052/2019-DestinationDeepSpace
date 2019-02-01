package com.team2052.deepspace.auto;

public abstract class AutoMode extends AutoModeBase{

    protected StartDirection startDirection = StartDirection.FORWARD;

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
