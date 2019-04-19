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
        SmartDashboard.putBoolean("Camera Toggle", !isBack);
    }

    private double yaw;
    private double height;
    private double width = 256;
    private double x = -1;
    private double xPercent;
    private double y = -1;

    private static double xOffset = 0;
    private static double defaultXOffset = 6.6;

    private boolean isTarget = false;

    public VisionController(){
        //run 20 times to make sure these are put on
        for(int t = 0; t < 20; t++) {
            SmartDashboard.putBoolean("CameraDebug", false);
            SmartDashboard.putBoolean("Camera Toggle", false);
            SmartDashboard.putNumber("centerOffset", 0);
        }
    }

    public DriveSignal getMotorOutput(double speed){
        getValues();

        if(getIsTarget()) {
            xPercent = ((xPercent-.5)*1.0)+.5;
            System.out.println("vision L: " + (xPercent * speed) + " vision R " + ((1 - xPercent) * speed) + " xP: " + xPercent);
            return new DriveSignal(xPercent * speed * 1.1, (1 - xPercent) * speed * 1.1);
        }else{
            return new DriveSignal(.6,.6);
        }
    }

    public void getValues(){
        //OLD WAY
        xOffset = SmartDashboard.getNumber("centerOffset", 0);
        //cameraH = (int)SmartDashboard.getNumber("cameraHeight",0);
        //cameraW = (int)SmartDashboard.getNumber("cameraWidth",0);
        yaw =SmartDashboard.getNumber("yawToTarget",0);
        height = SmartDashboard.getNumber("targetHeight",0)/144;
        width = SmartDashboard.getNumber("targetWidth",0)/256;
        x = SmartDashboard.getNumber("targetX",-1);
        y = SmartDashboard.getNumber("targetY",-1);
        //System.out.println("x is " + x);
        if(x > 0) {
            xPercent = (x + defaultXOffset + xOffset) / 150;
        }
        SmartDashboard.putNumber("xPercent", xPercent);
        //System.out.println("match offset:" + xOffset);

    }

    public boolean getIsTarget(){
        getValues();
        return x!=-1 || isTarget;
    }

    public boolean isClose(){
        return y >= 27.0;
    }

}
