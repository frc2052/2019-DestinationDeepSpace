package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.RobotState;
import com.team2052.deepspace.subsystems.*;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.DriveSignal;

public class LineUpAction implements Action{
    private LineFollowerControllerBase lineFollower = null;
    private DriveTrainController driveTrain = null;
    private VisionController visionController = null;
    private RobotState robotState = null;
    private Position2d startPos;
    
    private double targetDistance;


    public LineUpAction(double targetDistance, boolean isForward){
        this.targetDistance = targetDistance;
        if(isForward){
            lineFollower = new LineFollowerController();
        }else {
            lineFollower = new BackLineFollowerController();
        }
    }

    public LineUpAction(){
        targetDistance = 12;
    }

    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return targetDistance < Math.sqrt(Math.pow(startPos.getForward() - robotState.getLatestPosition().getForward(), 2) + Math.pow(startPos.getLateral() - robotState.getLatestPosition().getLateral(), 2));
    }

    @Override
    public void start() {
        lineFollower = LineFollowerController.getInstance();
        driveTrain = DriveTrainController.getInstance();
        robotState = RobotState.getInstance();
        startPos = robotState.getLatestPosition();
    }

    @Override
    public void update() {
        if(lineFollower.getLineSensed()){
            driveTrain.drive(lineFollower.getLightSensorMotorTurn(Constants.LineFollower.kLightSensorMotorSpeed));
        }else {
            //driveTrain.drive(visionController.getMotorOutput(.5));
            driveTrain.drive(new DriveSignal(.3,.3));
        }

    }
}
