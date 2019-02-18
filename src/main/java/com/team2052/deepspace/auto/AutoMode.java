package com.team2052.deepspace.auto;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.actions.Action;
import com.team2052.lib.Autonomous.Position2d;

/**
 * This is for game specific code
 */
public abstract class AutoMode{

    protected Position2d startingPos;
    private Action action = null;

    public AutoMode(Position2d startPos)  {
        this.startingPos = startPos;
    }

    protected abstract void init();

    public Action getAction(){
        System.out.println("is action ! null in automode: " + (action == null));
        if(action == null){
            init();
            System.out.println("AFTER INIT");
        }
        return action;
    }

    protected void setAction(Action action){
        this.action = action;
    }


    private StartDirection startDirection = Constants.Autonomous.defaultStartDirection;

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

}
