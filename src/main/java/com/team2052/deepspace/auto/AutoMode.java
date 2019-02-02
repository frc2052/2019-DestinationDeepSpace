package com.team2052.deepspace.auto;

import com.team2052.deepspace.Constants;

public abstract class AutoMode extends AutoModeBase{

    protected StartDirection startDirection = Constants.Autonomous.defaultStartDirection;
    protected StartPosition startPosition = StartPosition.CENTER;

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

    public StartPosition getStartPosition(){
        return startPosition;
    }
    public enum StartPosition{
        LEFT(Constants.Autonomous.kStartLeftInchOffset),
        RIGHT(Constants.Autonomous.kStartRightInchOffset),
        CENTER(0.0)
        ;

        public final double lateralOffset;

        StartPosition(double lateralOffset){
            this.lateralOffset = lateralOffset;
        }
    }
}
