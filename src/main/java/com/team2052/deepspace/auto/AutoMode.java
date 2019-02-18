package com.team2052.deepspace.auto;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.actions.Action;

/**
 * This is for game specific code
 */
public abstract class AutoMode{
    private Action action = null;
    protected abstract void init();

    public Action getAction(){
        if(action != null){
            init();
        }
        return action;
    }

    protected void setAction(Action action){
        this.action = action;
    }


    private StartDirection startDirection = Constants.Autonomous.defaultStartDirection;
    private AutoModeSelector.PositionSelection startPosition = AutoModeSelector.PositionSelection.CENTER;

    protected void setStartDirection(StartDirection startDirection){this.startDirection = startDirection;}
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

    protected void setStartPosition(AutoModeSelector.PositionSelection startPosition){
        this.startPosition = startPosition;
    }
    public AutoModeSelector.PositionSelection getStartPosition(){
        return startPosition;
    }

}
