package com.team2052.deepspace.subsystems;

import com.team2052.deepspace.DriveSignal;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionController {
    private static VisionController visionControllerInstance = new VisionController();
    public static VisionController getInstance() { return visionControllerInstance; }

    private double yaw;
    private double height;
    private double width;
    private double x;
    private double y;

    private int cameraW;
    private int cameraH;

    public DriveSignal getMotorOutput(){
        getValues();
        return new DriveSignal(x,1-x);
    }

    private void getValues(){
        cameraH = (int)SmartDashboard.getNumber("cameraHeight",0);
        cameraW = (int)SmartDashboard.getNumber("cameraWidth",0);
        yaw =SmartDashboard.getNumber("yawToTarget",0);
        height = SmartDashboard.getNumber("targetHeight",0)/cameraH;
        width = SmartDashboard.getNumber("targetWidth",0)/cameraW;
        x = SmartDashboard.getNumber("targetX",0)/cameraW;
        y = SmartDashboard.getNumber("targetY",0)/cameraH;
    }

    public boolean isClose(){
        return height > .8;
    }
}
