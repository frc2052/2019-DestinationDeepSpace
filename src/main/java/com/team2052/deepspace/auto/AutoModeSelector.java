package com.team2052.deepspace.auto;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.modes.CenterStart.BackwardCenterToCenterLeft;
import com.team2052.deepspace.auto.modes.CenterStart.BackwardCenterToCenterRight;
import com.team2052.deepspace.auto.modes.CenterStart.ForwardCanterToCenterLeft;
import com.team2052.deepspace.auto.modes.CenterStart.ForwardCenterToCenterRight;
import com.team2052.deepspace.auto.modes.DontMove;
import com.team2052.deepspace.auto.modes.LeftStart.*;
import com.team2052.deepspace.auto.modes.RightStart.*;
import com.team2052.deepspace.auto.modes.Test;
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
    }

    private static PositionSelection lastPosition = null;
    private static FirstTargetSelection lastFirst = null;
    private static SecondTargetSelection lastSecond = null;
    private static AutoMode selectedAuto = null;

    public static void checkSelectedAutoMode(){
        //this method also updates smartdashboard
        getSelectedAutoMode();
    }

    public static AutoMode getSelectedAutoMode() {

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
            switch (position) {
                case TEST:
                    System.out.println("selected test");
                    selectedAuto = new Test(position.startPos);
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
                        case BLMHATCH:
                            selectedAuto = new BackwardLeftToLeftMiddle(position.startPos);
                            break;
                        case BLFHATCH:
                            selectedAuto = new BackwardLeftToLeftFar(position.startPos);
                            break;
                        case FCLHATCH:
                            selectedAuto = new ForwardLeftToCenterLeft(position.startPos);
                            break;
                        case FLCHATCH:
                            selectedAuto = new ForwardLeftToLeftClose(position.startPos);
                            break;
                        case FLMHATCH:
                            selectedAuto = new ForwardLeftToLeftMiddle(position.startPos);
                            break;
                        case FLFHATCH:
                            selectedAuto = new ForwardLeftToLeftFar(position.startPos);
                    }
                    break;
                case RIGHT:
                case RIGHTHAB2:
                    switch (first) {
                        case BCRHATCH:
                            selectedAuto = new BackwardCenterToCenterRight(position.startPos);
                            break;
                        case BRMHATCH:
                            selectedAuto = new BackwardRightToRightMiddle(position.startPos);
                            break;
                        case BRFHATCH:
                            selectedAuto = new BackwardRightToRightFar(position.startPos);
                            break;
                        case BRCHATCH:
                            selectedAuto = new BackwardRightToRightClose(position.startPos);
                            break;
                        case FCRHATCH:
                            selectedAuto = new ForwardCenterToCenterRight(position.startPos);
                            break;
                        case FRMHATCH:
                            selectedAuto = new ForwardRightToRightMiddle(position.startPos);
                            break;
                        case FRFHATCH:
                            selectedAuto = new ForwardRightToRightFar(position.startPos);
                            break;
                        case FRCHATCH:
                            selectedAuto = new ForwardRightToRightClose(position.startPos);
                            break;
                    }
                    break;
                case CENTER:
                    switch (first) {
                        case BCLHATCH:
                            selectedAuto = new BackwardCenterToCenterLeft(position.startPos);
                            break;
                        case BCRHATCH:
                            selectedAuto = new BackwardCenterToCenterLeft(position.startPos);
                            break;
                        case FCLHATCH:
                            selectedAuto = new ForwardCanterToCenterLeft(position.startPos);
                            break;
                        case FCRHATCH:
                            selectedAuto = new ForwardCenterToCenterRight(position.startPos);
                            break;
                    }
                    break;
                case NONE:
                default:
                    selectedAuto = null; //set null because we check if its null on line for smart dashboard
            }
            if(selectedAuto != null){
                selectedAuto.init();
            }
        }

        if (selectedAuto != null) {
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
        BCLHATCH("BackCenterLeftHatch"),
        BCRHATCH("BackCenterRightHatch"),
        FCLHATCH("FrontCenterLeftHatch"),
        FCRHATCH("FrontCenterRightHatch"),
        BLFHATCH("BackLeftFarHatch"),
        FLFHATCH("FrontLeftFarHatch"),
        BRFHATCH("BackRightFarHatch"),
        FRFHATCH("FrontRightFarHatch"),
        BLMHATCH("BackLeftMiddleHatch"),
        FLMHATCH("FrontLeftMiddleHatch"),
        BRMHATCH("BackRightMiddleHatch"),
        FRMHATCH("FrontRightMiddleHatch"),
        BLCHATCH("BackLeftCloseHatch"),
        FLCHATCH("FrontLeftCloseHatch"),
        BRCHATCH("BackRightCloseHatch"),
        FRCHATCH("FrontRightCloseHatch"),
        TEST("test");

        public String name;

        FirstTargetSelection(String name) {
            this.name = name;
        }
    }

    //ADD All POSSIBLE COMBONATIONS KEYWORDS  CREATE ALL CLASSES!!!
    public enum SecondTargetSelection {
        NONE("None"),
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
}

