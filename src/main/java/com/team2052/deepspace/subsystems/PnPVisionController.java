package com.team2052.deepspace.subsystems;

import com.team2052.deepspace.RobotState;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Position3d;
import com.team2052.lib.DriveSignal;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PnPVisionController {

    private static PnPVisionController instance = null;

    public static PnPVisionController getInstance() {
        if (instance == null) {
            try {
                instance = new PnPVisionController();
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
    private static double defaultXOffset = 5.4;

    private boolean isTarget = false;
    private Position3d pos = new Position3d();
    private Position3d rot = new Position3d();
    private Position2d targetPos = new Position2d();

    public PnPVisionController(){
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
            return new DriveSignal(xPercent * speed * 1.0, (1 - xPercent) * speed * 1.0);
        }else{
            return new DriveSignal(.5,.5);
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

        //new vision
        isTarget = SmartDashboard.getBoolean("isVisionTarget", false);

        Position2d temp = new Position2d();
        //in Milimeters
        pos.setX((SmartDashboard.getNumber("TarX",0) + 600) * -0.0393701); //this is actually from the left top corner of the target + 200 to shift it to the center
        pos.setY((SmartDashboard.getNumber("TarY",0) * 0.0393701));
        pos.setZ((SmartDashboard.getNumber("TarZ",0) * 0.0393701)/2);

        //in radians
        rot.setX(SmartDashboard.getNumber("TarRotX",0));
        rot.setY(SmartDashboard.getNumber("TarRotY",0));
        rot.setZ(SmartDashboard.getNumber("TarRotZ",0));

        //point for target: (rf + z*cos(h) + abs(x) * cos(h - sig(x) * pi/2, rl + z*sin(h) + abs(x) * sin(h - sig(x) * pi/2)
        Position2d robotPos = RobotState.getInstance().getLatestPosition();
        temp.setForward(robotPos.getForward() + (pos.getZ()-3.625) * Math.cos(robotPos.getHeading()) + Math.abs(pos.getX()) * Math.cos(robotPos.getHeading() + Math.signum(pos.getX()) * (Math.PI/2)));
        temp.setLateral(robotPos.getLateral() + (pos.getZ()-3.625) * Math.sin(robotPos.getHeading()) + Math.abs(pos.getX()) * Math.sin(robotPos.getHeading() + Math.signum(pos.getX()) * (Math.PI/2)));
        temp.setHeading(Math.PI + rot.getY() + robotPos.getHeading());

        if(Math.abs(temp.getForward()-targetPos.getForward()) < 10 && Math.abs(temp.getLateral()-targetPos.getLateral()) <10){
            targetPos = temp.getClone();
        }
        System.out.println("found target?: " + isTarget + "Target Forward: " + targetPos.getForward() + " Target Lateral: " + targetPos.getLateral() + " Target Angle: " + targetPos.getHeading());
    }

    public boolean getIsTarget(){
        getValues();
        return x!=-1 || isTarget;
    }

    public boolean isClose(){
        return y >= 31.0;
    }

    public Position2d getTargetPos(){
        return targetPos;
    }
}
