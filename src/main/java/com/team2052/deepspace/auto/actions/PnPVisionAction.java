package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.auto.PurePursuitPathFollower;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.VisionPath;
import com.team2052.deepspace.subsystems.PnPVisionController;
import edu.wpi.first.wpilibj.Timer;

public class PnPVisionAction implements Action{

    private PurePursuitPathFollower purePursuitPathFollower;
    private PnPVisionController visionController;
    private VisionPath visionPath;
    private Timer timer;
    private double timeLimit;

    public PnPVisionAction(double timeLimit){
        purePursuitPathFollower  = PurePursuitPathFollower.getInstance();
        visionController = PnPVisionController.getInstance();
        timer = new Timer();
        this.timeLimit = timeLimit;
    }

    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return timer.get() > timeLimit || purePursuitPathFollower.isPathComplete();
    }

    @Override
    public void start() {
        visionController.getValues();
        visionPath = new VisionPath(Path.Direction.FORWARD,visionController.getTargetPos());
        //visionPath = new VisionPath(Path.Direction.FORWARD, new Position2d(50,0,Math.PI));
        purePursuitPathFollower.start(visionPath);
    }

    @Override
    public void update() {
        visionController.getValues();

        if(visionController.getIsTarget()) {
            visionPath = new VisionPath(Path.Direction.FORWARD, visionController.getTargetPos());
            purePursuitPathFollower.OverrideCurrentPath(visionPath);
        }

        purePursuitPathFollower.update();
    }
}
