package com.team2052.deepspace.subsystems;

import com.team2052.lib.DriveSignal;
import com.team2052.lib.PIDController;
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
    public void showBackPiCamera(boolean isBack){
        this.isBack = isBack;
        SmartDashboard.putBoolean("Camera Toggle", !isBack);
    }

    private PIDController pidController = new PIDController(10,0,0);

    private double yaw;
    private double height;
    private double width = 256;
    private double x = -1;
    private double xPercent;
    private double y = -1;
    private boolean isBack;

    private static double xOffset = 0;
    private static double defaultXOffset = -34.5;//-13.4;

    private boolean isTarget = false;

    public VisionController(){
        //run 20 times to make sure these are put on
        for(int t = 0; t < 20; t++) {
            SmartDashboard.putBoolean("CameraDebug", false);
            SmartDashboard.putBoolean("Camera Toggle", true);
            SmartDashboard.putNumber("centerOffset", 0);
        }
    }

    public DriveSignal getMotorOutput(double speed){
        getValues();
        speed = speed > .5 ? .5 : speed;
        System.out.println("speed : "  + speed);
        if(getIsTarget()) {
            double correctingRatio = 1.15;
//            System.out.println("vision L: " + ((pidController.getOutput(xPercent, .5))) + " vision R " + pidController.getOutput(xPercent, .5) + " xP: " + xPercent);
//            return new DriveSignal((pidController.getOutput(xPercent, .5)), pidController.getOutput(xPercent, .5) * speed);

            if(!isBack) {
                System.out.println("vision L: " + (xPercent * speed) + " vision R " + ((1 - xPercent) * speed) + " xP: " + xPercent);
                xPercent = ((xPercent - .5) * 1.0) + .5;
                System.out.println("left: " + (xPercent * (1.1 * (xPercent > .5 ? correctingRatio : 1))) + " right: " + ((1 - xPercent) * 1.1 * (xPercent < .5 ? correctingRatio : 1)));
                return new DriveSignal(xPercent * speed * 1.1 * (xPercent > .5 ? correctingRatio : 1), (1 - xPercent) * speed * 1.1  * (xPercent < .5 ? correctingRatio : 1));
                 }else {
                System.out.println("vision L: " + (xPercent * speed) + " vision R " + ((1 - xPercent) * speed) + " xP: " + xPercent);
                xPercent = ((xPercent - .5) * 1.0) + .5;
                return new DriveSignal((1-xPercent) * speed * 1.1, xPercent * speed * 1.1);
            }
        }else{
            if(!isBack) {
                return new DriveSignal(.6, .6);
            }else{
                return new DriveSignal(-.6,-.6);
            }
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
            xPercent = (x + defaultXOffset + xOffset) / 100; ///150
        }
        SmartDashboard.putNumber("xPercent", xPercent);
        //System.out.println("match offset:" + xOffset);

    }

    public boolean getIsTarget(){
        getValues();
        return x!=-1 || isTarget;
    }

    public boolean isClose(){
        if(!isBack) {
            return y >= 31.0;
        }else {
            return y >=55;
        }
    }

}
