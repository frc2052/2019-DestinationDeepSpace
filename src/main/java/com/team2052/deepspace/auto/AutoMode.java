package com.team2052.deepspace.auto;

import com.team2052.deepspace.Constants;

/**
 * This is for game specific code
 */
public abstract class AutoMode extends AutoModeBase{
    private StartDirection startDirection = Constants.Autonomous.defaultStartDirection;
    private LateralStartPosition lateralStartPosition = LateralStartPosition.CENTER;
    private int forwardOffset;

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

    public void setLateralStartPosition(LateralStartPosition lateralStartPosition){this.lateralStartPosition = lateralStartPosition;}
    public LateralStartPosition getLateralStartPosition(){
        return lateralStartPosition;
    }
    public enum LateralStartPosition {
        LEFT(Constants.Autonomous.kStartLeftInchOffset),
        RIGHT(Constants.Autonomous.kStartRightInchOffset),
        CENTER(0.0)
        ;

        public final double lateralOffset;

        LateralStartPosition(double lateralOffset){
            this.lateralOffset = lateralOffset;
        }
    }
    public void setForwardStartOffset(int offset){this.forwardOffset = offset;}
    public int getForwardOffset(){
        return forwardOffset;
    }
}
