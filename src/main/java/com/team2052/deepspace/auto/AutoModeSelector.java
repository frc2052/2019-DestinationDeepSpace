package com.team2052.deepspace.auto;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.modes.CenterStart.BackwardCenterToCenterRight;
import com.team2052.deepspace.auto.modes.CenterStart.ForwardCanterToCenterLeft;
import com.team2052.deepspace.auto.modes.CenterStart.ForwardCenterToCenterRight;
import com.team2052.deepspace.auto.modes.DontMove;
import com.team2052.deepspace.auto.modes.LeftHatchPickUp.LeftSecondHatchBackUp;
import com.team2052.deepspace.auto.modes.LeftHatchPickUp.LeftSecondHatchCenterLeft;
import com.team2052.deepspace.auto.modes.LeftHatchPickUp.LeftSecondHatchLeftClose;
import com.team2052.deepspace.auto.modes.LeftHatchPickUp.LeftSecondHatchLeftMiddle;
import com.team2052.deepspace.auto.modes.LeftStart.*;
import com.team2052.deepspace.auto.modes.RightHatchPickUp.RightSecondHatchBackUp;
import com.team2052.deepspace.auto.modes.RightHatchPickUp.RightSecondHatchCenterRight;
import com.team2052.deepspace.auto.modes.RightHatchPickUp.RightSecondHatchRightClose;
import com.team2052.deepspace.auto.modes.RightHatchPickUp.RightSecondHatchRightMiddle;
import com.team2052.deepspace.auto.modes.RightStart.*;
import com.team2052.deepspace.auto.modes.Test;
import com.team2052.deepspace.auto.modes.WaitToStart;
import com.team2052.lib.Autonomous.Position2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoModeSelector {
    private static SendableChooser<PositionSelection> sendableChooserPosition;
    private static SendableChooser<FirstTargetSelection> sendableChooserFirstTarget;
    private static SendableChooser<SecondTargetSelection> sendableChooserSecondTarget;

    public static void putToShuffleBoard() {
        sendableChooserPosition = new SendableChooser<PositionSelection>();
        sendableChooserFirstTarget = new SendableChooser<FirstTargetSelection>();
        sendableChooserSecondTarget = new SendableChooser<SecondTargetSelection>();

        for (int i = 0; i < PositionSelection.values().length; i++) {
            PositionSelection mode = PositionSelection.values()[i];
            if (i == 0) {
                sendableChooserPosition.setDefaultOption(mode.name, mode);
            } else {
                sendableChooserPosition.addOption(mode.name, mode);
            }
        }

        for (int i = 0; i < FirstTargetSelection.values().length; i++) {
            FirstTargetSelection mode = FirstTargetSelection.values()[i];
            if (i == 0) {
                sendableChooserFirstTarget.setDefaultOption(mode.name, mode);
            } else {
                sendableChooserFirstTarget.addOption(mode.name, mode);
            }
        }

        for (int i = 0; i < SecondTargetSelection.values().length; i++) {
            SecondTargetSelection mode = SecondTargetSelection.values()[i];
            if (i == 0) {
                sendableChooserSecondTarget.setDefaultOption(mode.name, mode);
            } else {
                sendableChooserSecondTarget.addOption(mode.name, mode);
            }
        }
        SmartDashboard.putData("Start Position", sendableChooserPosition);
        SmartDashboard.putData("First Target", sendableChooserFirstTarget);
        SmartDashboard.putData("Second Target", sendableChooserSecondTarget);
        SmartDashboard.putBoolean("Wait To Start?", waitForStart);
    }

    private static PositionSelection lastPosition = null;
    private static FirstTargetSelection lastFirst = null;
    private static SecondTargetSelection lastSecond = null;
    private static AutoMode selectedAuto = null;
    private static AutoMode secondAuto  = null;
    private static boolean waitForStart = false;

    public static AutoMode getSelectedAutoMode() {
        waitForStart = SmartDashboard.getBoolean("Wait To Start?", false);
        PositionSelection position = sendableChooserPosition.getSelected();
        FirstTargetSelection first = sendableChooserFirstTarget.getSelected();
        SecondTargetSelection second = sendableChooserSecondTarget.getSelected();

        //set defaults if none selected
        if (position == null){
            position = PositionSelection.CENTER;
        }
        if (first == null) {
            first = FirstTargetSelection.NONE;
        }
        if (second == null) {
            second = SecondTargetSelection.NONE;
        }

        //TODO: REVIEW - comment this logic
        if (selectedAuto == null || selectedAuto.isActionFinished() || position != lastPosition || first != lastFirst || second != lastSecond) {
            lastPosition = position;
            lastFirst = first;
            lastSecond = second;
            //System.out.println("pos: "+ lastPosition.name + " " + lastPosition + " first: " + lastFirst.name + " " + lastFirst);
            switch (position) {
                case TEST:
                    System.out.println("selected test");
                    selectedAuto = new Test(position.startPos);
                    secondAuto = new DontMove();
                    break;
                case LEFT:
                case LEFTHAB2:
                    switch (first) {

                        case BCLHATCH:
                            selectedAuto = new BackwardLeftToCenterLeft(position.startPos);
                            break;
                        case BLCHATCH:
                            selectedAuto = new BackwardLeftToLeftClose(position.startPos);
                            break;
                            /*
                        case BLMHATCH:
                            selectedAuto = new BackwardLeftToLeftMiddle(position.startPos);
                            break;
                        case BLFHATCH:
                            selectedAuto = new BackwardLeftToLeftFar(position.startPos);
                            break;
                            */
                        case FCLHATCH:
                            selectedAuto = new ForwardLeftToCenterLeft(position.startPos);
                            break;
                        case FLCHATCH:
                            selectedAuto = new ForwardLeftToLeftClose(position.startPos);
                            break;
                            /*
                        case FLMHATCH:
                            selectedAuto = new ForwardLeftToLeftMiddle(position.startPos);
                            break;
                        case FLFHATCH:
                            selectedAuto = new ForwardLeftToLeftFar(position.startPos);
                            break;

                        case BLSRFHATCH: // does notwork
                            selectedAuto = new BackwardLeftToRocketFar(position.startPos);
                            break;
                            */
                        case FLSRCHATCH:
                            selectedAuto = new ForwardLeftToRocketClose(position.startPos);
                            break;
                        default:
                            selectedAuto = null;
                    }

                    switch (second){
                        case BACKUP:
                            secondAuto = new LeftSecondHatchBackUp(position.startPos);
                            break;
                        case LHATCH:
                            secondAuto = new LeftSecondHatchCenterLeft(position.startPos);
                            break;
                        case LCHATCH:
                            secondAuto = new LeftSecondHatchLeftClose(position.startPos);
                            break;
                        case LMHATCH:
                            secondAuto = new LeftSecondHatchLeftMiddle(position.startPos);
                            break;
                        case LFHATCH:
                                secondAuto = null;
                            break;
                        case NONE:
                            secondAuto = new DontMove();
                            break;
                        default:
                            secondAuto = null;
                            break;
                    }
                    break;
                case RIGHT:
                case RIGHTHAB2:
                    switch (first) {

                        case BRCHATCH:
                            selectedAuto = new BackwardRightToRightClose(position.startPos);
                            break;
                        case BCRHATCH:
                            selectedAuto = new BackwardCenterToCenterRight(position.startPos);
                            break;
                            /*
                        case BRMHATCH:
                            selectedAuto = new BackwardRightToRightMiddle(position.startPos);
                            break;
                        case BRFHATCH:
                            selectedAuto = new BackwardRightToRightFar(position.startPos);
                            break;

                            */
                        case FCRHATCH:
                            selectedAuto = new ForwardRightToCenterRight(position.startPos);
                            break;
                        /*
                            case FRMHATCH:
                            selectedAuto = new ForwardRightToRightMiddle(position.startPos);
                            break;
                        case FRFHATCH:
                            selectedAuto = new ForwardRightToRightFar(position.startPos);
                            break;
                            */
                        case FRCHATCH:
                            selectedAuto = new ForwardRightToRightClose(position.startPos);
                            break;
                        default:
                            selectedAuto = null;
                    }

                    switch (second){
                        case BACKUP:
                            secondAuto = new RightSecondHatchBackUp(position.startPos);
                            break;
                        case RHATCH:
                            secondAuto = new RightSecondHatchCenterRight(position.startPos);
                            break;
                        case RCHATCH:
                            secondAuto = new RightSecondHatchRightClose(position.startPos);
                            break;
                        case RMHATCH:
                            secondAuto = new RightSecondHatchRightMiddle(position.startPos);
                            break;
                        case RFHATCH:
                            secondAuto = null;
                            break;
                        case NONE:
                            secondAuto = new DontMove();
                            break;
                        default:
                            secondAuto = null;
                            break;
                    }

                    break;
                case CENTER:
                    switch (first) {
                        /*
                        case BCLHATCH:
                            selectedAuto = new BackwardCenterToCenterLeft(position.startPos);
                            break;
                        case BCRHATCH:
                            selectedAuto = new BackwardCenterToCenterLeft(position.startPos);
                            break;
                            */
                        case FCLHATCH:
                            selectedAuto = new ForwardCanterToCenterLeft(position.startPos);
                            break;
                        case FCRHATCH:
                            selectedAuto = new ForwardCenterToCenterRight(position.startPos);
                            break;
                        default:
                            selectedAuto = null;
                    }

                    switch (second){
                        case NONE:
                            secondAuto = new DontMove();
                            break;
                        default:
                            secondAuto = null;
                            break;
                    }
                    break;
                case NONE:
                default:
                    selectedAuto = null; //set null because we check if its null on line for smart dashboard
            }
            if(selectedAuto != null && secondAuto != null){
                System.out.println("INITING: "+selectedAuto.getClass().getSimpleName());
                selectedAuto.init();
                System.out.println("INITING SECOND: "+secondAuto.getClass().getSimpleName());
                secondAuto.init();

                if(waitForStart){
                    AutoMode delayStart = new WaitToStart(position.startPos);
                    delayStart.init();
                    delayStart.appendAction(selectedAuto.getAction());
                    delayStart.appendAction(secondAuto.getAction());
                    selectedAuto = delayStart;
                }else {
                    selectedAuto.appendAction(secondAuto.getAction());
                }
            }
        }

        if (selectedAuto != null && secondAuto != null) {
            SmartDashboard.putBoolean("Does AutoMode Exist?", true);

            return selectedAuto;
        } else {
            SmartDashboard.putBoolean("Does AutoMode Exist?", false);
            return new DontMove();
        }
    }

    public static Position2d getStartingPos() {
        return sendableChooserPosition.getSelected().startPos;
    }

    public enum PositionSelection {
        NONE("Select Start", new Position2d(0, 0)),
        LEFT("StartLeft", new Position2d(0, Constants.Autonomous.kStartLeftInchOffset)),
        CENTER("StartCenter", new Position2d(0, 0)),
        RIGHT("StartRight", new Position2d(0, Constants.Autonomous.kStartRightInchOffset)),
        LEFTHAB2("startLeftHab2", new Position2d(Constants.Autonomous.kStartHab2Offset, Constants.Autonomous.kStartLeftInchOffset)),
        RIGHTHAB2("startRightHab2", new Position2d(Constants.Autonomous.kStartHab2Offset, Constants.Autonomous.kStartRightInchOffset)),
        TEST("test", new Position2d(0, 0));

        public String name;
        public Position2d startPos;

        PositionSelection(String name, Position2d startPos) {
            this.name = name;
            this.startPos = startPos;
        }
    }

    public enum FirstTargetSelection {
        NONE("Select Target One"),
        FCLHATCH("ForwardCenterLeftHatch"),
        FCRHATCH("ForwardCenterRightHatch"),
        FLCHATCH("ForwardLeftCloseHatch"),
        //FLMHATCH("ForwardLeftMiddleHatch"),
        //FLFHATCH("ForwardLeftFarHatch"),
        FRCHATCH("ForwardRightCloseHatch"),
        //FRMHATCH("ForwardRightMiddleHatch"),
        //FRFHATCH("ForwardRightFarHatch"),
        FLSRCHATCH("ForwardLeftRocketClose"),
        //BLSRFHATCH("BackwardLeftRocketFar"),

        BCLHATCH("BackCenterLeftHatch"),
        BLCHATCH("BackLeftCloseHatch"),
        BCRHATCH("BackCenterRightHatch"),
        BRCHATCH("BackRightCloseHatch");
            /*
        BLMHATCH("BackLeftMiddleHatch"),
        BLFHATCH("BackLeftFarHatch"),

        BRMHATCH("BackRightMiddleHatch"),
        BRFHATCH("BackRightFarHatch")*/

        public String name;

        FirstTargetSelection(String name) {
            this.name = name;
        }
    }

    //ADD All POSSIBLE COMBONATIONS KEYWORDS  CREATE ALL CLASSES!!!
    public enum SecondTargetSelection {
        NONE("None"),
        BACKUP("Backup"),
        LHATCH("CenterLeftHatch"),
        RHATCH("CenterRightHatch"),
        LFHATCH("LeftFarHatch"),
        RFHATCH("RightFarHatch"),
        LMHATCH("LeftMiddleHatch"),
        RMHATCH("RightMiddleHatch"),
        LCHATCH("LeftCloseHatch"),
        RCHATCH("RightCloseHatch");


        public String name;

        SecondTargetSelection(String name) {
            this.name = name;
        }
    }

    public static void nullSelectedAutoMode() {
        selectedAuto = null;
    }
}

