package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.subsystems.*;

public class LineUpAction implements Action{
    private LineFollowerControllerBase lineFollower = null;
    private DriveTrainController driveTrain = null;
    private VisionController visionController = null;
    private boolean isForwards;

    public LineUpAction(boolean goingForwards){
        isForwards = goingForwards;
    }
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void start() {
        if(isForwards){
            lineFollower = LineFollowerController.getInstance();
        } else {
            lineFollower = BackLineFollowerController.getInstance();
        }
        driveTrain = DriveTrainController.getInstance();
        visionController = VisionController.getInstance();
    }

    @Override
    public void update() {
        if(lineFollower.getLineSensed()){
            if(isForwards){
                driveTrain.drive(lineFollower.getLightSensorMotorTurn(Constants.LineFollower.kLightSensorMotorSpeed));
            } else{
                driveTrain.drive(lineFollower.getLightSensorMotorTurn(-Constants.LineFollower.kLightSensorMotorSpeed)); //Reverse Speed for backwards.
            }
        }else {
            driveTrain.drive(visionController.getMotorOutput());
        }

    }
}
