package com.team2052.deepspace.auto.actions;

public class PickupAction implements Action{

    public PickupAction(Mode mode){

    }
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    public enum Mode{
        IN,
        OUT
    }
}
