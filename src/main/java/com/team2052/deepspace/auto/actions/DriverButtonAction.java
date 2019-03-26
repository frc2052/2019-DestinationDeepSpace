package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.Controls;

public class DriverButtonAction implements Action{

    private Controls controls;
    private int buttonId;
    private Controls.Joysticks joystick;

    public DriverButtonAction(Controls.Joysticks joystick, int buttonId){
        this.buttonId = buttonId;
        this.joystick = joystick;
        controls = Controls.getInstance();
    }

    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return controls.getButtonOnJoyStick(joystick, buttonId);
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }


}
