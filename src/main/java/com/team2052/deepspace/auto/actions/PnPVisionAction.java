package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.auto.PurePursuitPathFollower;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.VisionPath;
import com.team2052.deepspace.subsystems.VisionController;
import edu.wpi.first.wpilibj.Timer;

public class PnPVisionAction implements Action{

    private PurePursuitPathFollower purePursuitPathFollower;
    private VisionController visionController;
    private VisionPath visionPath;
    private Timer timer;
    private double timeLimit;

    public PnPVisionAction(double timeLimit){
        purePursuitPathFollower  = PurePursuitPathFollower.getInstance();
        visionController = VisionController.getInstance();
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
