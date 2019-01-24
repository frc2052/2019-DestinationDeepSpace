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
        
        return new DriveOutput(x,1-x);
    }
}
