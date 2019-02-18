package com.team2052.deepspace.auto.modes;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.WaitAction;
import com.team2052.lib.Autonomous.Position2d;

/**
 * Starts: Anywhere
 * Desc:
 * Ends: Where it starts
 */
public class DontMove extends AutoMode{
    public DontMove()
    {
        super(new Position2d(0,0));
    }

    @Override
    protected void init() {
        setAction(new WaitAction(0));
    }
}
