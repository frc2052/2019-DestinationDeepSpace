package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.subsystems.DriveTrainController;
import com.team2052.deepspace.subsystems.LineFollowerController;
import com.team2052.deepspace.subsystems.VisionController;

public class LineUpAction implements Action{
    private LineFollowerController lineFollower = null;
    private DriveTrainController driveTrain = null;
    private VisionController visionController = null;

    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return visionController.isClose();
    }

    @Override
    public void start() {
        lineFollower = LineFollowerController.getInstance();
        driveTrain = DriveTrainController.getInstance();
        visionController = VisionController.getInstance();
    }

    @Override
    public void update() {
        if(lineFollower.getLineSensed()){
            driveTrain.drive(lineFollower.getLightSensorMotorTurn(Constants.LineFollower.kLightSensorMotorSpeed));
        }else {
            driveTrain.drive(visionController.getMotorOutput());
        }

    }
}
