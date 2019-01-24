package com.team2052.deepspace.subsystems;

import com.team2052.lib.DriveOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionController {
    private static VisionController visionControllerInstance = new VisionController();
    public static VisionController getInstance() { return visionControllerInstance; }

    private double yaw;
    private double height;
    private double width;
    private double x;
    private double y;

    private int cameraW = 416;
    private int cameraH = 240;

    public DriveOutput getMotorOutput(){
        yaw =SmartDashboard.getNumber("yawToTarget",0);
        height = SmartDashboard.getNumber("targetHeight",0)/cameraH;
        width = SmartDashboard.getNumber("targetWidth",0)/cameraW;
        x = SmartDashboard.getNumber("targetX",0)/cameraW;
        y = SmartDashboard.getNumber("targetY",0)/cameraH;

        //opposite of height is how fast we turn aka the farther away the slower the turn to help get the robot in front of the target before we turn
        double turnReduction = .5-height; //constant for % away from target
        double left;
        double right;
        //we dont want it negative
        if(turnReduction < 0){
            turnReduction = 0;
        }
        if(x > .5){
            left = x + turnReduction;
            right = (1-x);
        }else{
            left = x;
            right = (1-x) + turnReduction;
        }
        return new DriveOutput(left,right);
    }
}
