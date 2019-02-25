package com.team2052.deepspace.auto;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.lib.Autonomous.Position2d;

/**
 * This is for game specific code
 */
public abstract class AutoMode{

    protected Position2d startingPos;

    //TODO: REVIEW - Should a new "MasterAction" be created, with a HasBeenStarted property and the ability for it to cancel/abort?
    private SeriesAction action = null;

    public AutoMode(Position2d startPos)  {
        this.startingPos = startPos;
    }

    //TODO: REVIEW - Add comments, what should every init override do?
    protected abstract void init();

    public SeriesAction getAction(){
//        System.out.println("is action ! null in automode: " + (action == null));
        if(action == null){
            init();
            //System.out.println("AFTER INIT");
        }
        return action;
    }

    protected void setAction(SeriesAction action){
        this.action = action;
    }

    //TODO: REVIEW - this seems dangerous. Should we force the developer to set a start direction, have a default be "invalid" to catch errors?
    //TODO: REVIEW - maybe we should use the same direction enum as Path
    private StartDirection startDirection = Constants.Autonomous.defaultStartDirection;

    protected void setStartDirection(StartDirection startDirection){this.startDirection = startDirection;}
    public StartDirection getStartDirection(){
        return startDirection;
    }

    //TODO: REVIEW - Consider using Path.Direction enum in place of this enum that has the exact same values
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
