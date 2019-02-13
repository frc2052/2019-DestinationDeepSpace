package com.team2052.deepspace.auto;

import com.team2052.deepspace.Constants;

/**
 * This is for game specific code
 */
public abstract class AutoMode extends AutoModeBase{
    private StartDirection startDirection = Constants.Autonomous.defaultStartDirection;
    private StartPosition startPosition = StartPosition.CENTER;

    public void setStartDirection(StartDirection startDirection){this.startDirection = startDirection;}
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

    public void setStartPosition(StartPosition startPosition){this.startPosition = startPosition;}
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
