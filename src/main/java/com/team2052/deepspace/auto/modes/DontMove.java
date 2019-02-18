package com.team2052.deepspace.auto.modes;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.WaitAction;

/**
 * Starts: Anywhere
 * Desc:
 * Ends: Where it starts
 */
public class DontMove extends AutoMode{
    @Override
    protected void init() {
        setAction(new WaitAction(0));
    }
}
