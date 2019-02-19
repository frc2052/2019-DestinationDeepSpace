package com.team2052.deepspace.subsystems;

import com.team2052.lib.DriveSignal;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionController {
    private static VisionController instance = null;
    public static VisionController getInstance() {
        if (instance == null) {
            try {
                instance = new VisionController();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create Vision Controller: " + exc.getMessage());
                exc.printStackTrace();
            }
        }
        return instance;
    }

    //Static method so all code access the smart dashboard the same way for camera
    public static void showBackPiCamera(boolean isBack){
        SmartDashboard.putBoolean("Camera Toggle", isBack);
    }

    private double yaw;
    private double height;
    private double width = 256;
    private double x = -1;
    private double xPercent;
    private double y;

    private int cameraW;
    private int cameraH;

    public DriveSignal getMotorOutput(double speed){
        getValues();
        System.out.println("vision L: " + x * speed + " vision R " + (1-x) * speed);
        return new DriveSignal(x * speed,(1-x) * speed);
    }

    public void getValues(){
        //cameraH = (int)SmartDashboard.getNumber("cameraHeight",0);
        //cameraW = (int)SmartDashboard.getNumber("cameraWidth",0);
        yaw =SmartDashboard.getNumber("yawToTarget",0);
        height = SmartDashboard.getNumber("targetHeight",0)/cameraH;
        width = SmartDashboard.getNumber("targetWidth",0)/cameraW;
        x = SmartDashboard.getNumber("targetX",-2);
        y = SmartDashboard.getNumber("targetY",0)/cameraH;
        //System.out.println("x is " + x);
        xPercent = (x-30)/100;
        SmartDashboard.putNumber("xPercent", xPercent);
    }

    public boolean isTarget(){
        getValues();
        return x!=-1;
    }

    public boolean isClose(){
        return height > .8;
    }
}
