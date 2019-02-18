package com.team2052.deepspace.auto;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.actions.Action;
import com.team2052.deepspace.auto.modes.CenterStart.BackwardCenterToCenterLeft;
import com.team2052.deepspace.auto.modes.CenterStart.BackwardCenterToCenterRight;
import com.team2052.deepspace.auto.modes.CenterStart.ForwardCanterToCenterLeft;
import com.team2052.deepspace.auto.modes.CenterStart.ForwardCenterToCenterRight;
import com.team2052.deepspace.auto.modes.DontMove;
import com.team2052.deepspace.auto.modes.LeftStart.LeftToCenterLeft;
import com.team2052.deepspace.auto.modes.LeftStart.LeftToLeftClose;
import com.team2052.deepspace.auto.modes.LeftStart.LeftToLeftFar;
import com.team2052.deepspace.auto.modes.LeftStart.LeftToLeftMiddle;
import com.team2052.deepspace.auto.modes.RightStart.RightToRightClose;
import com.team2052.deepspace.auto.modes.RightStart.RightToRightFar;
import com.team2052.deepspace.auto.modes.RightStart.RightToRightMiddle;
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

    public static boolean getStartDirection(){
        AutoMode selected = getSelectedAutoMode();
        if (selected != null) {
            return selected.getStartDirection().isForward;
        } else {
            return true;
        }
    }

    public static Action getSelectedAction() {
        AutoMode selected = getSelectedAutoMode();
        if (selected != null) {
            return selected.getAction();
        } else {
            return null;
        }
    }

    private static PositionSelection lastPosition = null;
    private static FirstTargetSelection lastFirst = null;
    private static SecondTargetSelection lastSecond = null;
    private static AutoMode selectedAuto = null;
    public static AutoMode getSelectedAutoMode() {
//        Action autoAction = new DontMove().getAction();
//        AutoModeDefinition firstSelected = getFirstSelectedAutomode();
//        AutoModeDefinition secondSelected = getSecondSelectedAutomode();
//        try {
//            SmartDashboard.putString("selected2ndgoal",sendableChooserSecondTarget.getSelected().name + " " + sendableChooserSecondTarget.getSelected().name.equals("None"));
//            if(sendableChooserSecondTarget.getSelected().name.equals("None")) {
//                //System.out.println("ONLY FIrST");
//                autoAction = firstSelected.autoMode.getAction();
//            }else{
//                //System.out.println("ONLY SECOND");
//                autoAction = new SeriesAction(Arrays.asList(
//                        firstSelected.autoMode.getAction(),
//                        secondSelected.autoMode.getAction()
//                ));
//            }
//            SmartDashboard.putBoolean("Does AutoMode Exist?", true);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            SmartDashboard.putBoolean("Does AutoMode Exist?", false);
//        }

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

        if (selectedAuto == null || position != lastPosition || first != lastFirst || second != lastSecond) {
            lastPosition = position;
            lastFirst = first;
            lastSecond = second;
            switch (position) {
                case TEST:
                    selectedAuto = new Test(position.startPos);
                    break;
                case LEFT:
                case LEFTHAB2:
                    switch (first) {
                        case BCLHATCH:
                            selectedAuto = new LeftToCenterLeft(position.startPos);
                            break;
                        case LCHATCH:
                            selectedAuto = new LeftToLeftClose(position.startPos);
                            break;
                        case LMHATCH:
                            selectedAuto = new LeftToLeftMiddle(position.startPos);
                            break;
                        case LFHATCH:
                            selectedAuto = new LeftToLeftFar(position.startPos);
                            break;
                    }
                    break;
                case RIGHT:
                case RIGHTHAB2:
                    switch (first) {
                        case BCRHATCH:
                            selectedAuto = new BackwardCenterToCenterRight(position.startPos);
                            break;
                        case RMHATCH:
                            selectedAuto = new RightToRightMiddle(position.startPos);
                            break;
                        case RFHATCH:
                            selectedAuto = new RightToRightFar(position.startPos);
                            break;
                        case RCHATCH:
                            selectedAuto = new RightToRightClose(position.startPos);
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
                    selectedAuto = new DontMove();
            }
        }

        if (selectedAuto != null) {
            SmartDashboard.putBoolean("Does AutoMode Exist?", true);
//            selectedAuto.init();
            return selectedAuto;
        } else {
            SmartDashboard.putBoolean("Does AutoMode Exist?", false);
            return null;
        }
    }
//
//    private static AutoModeDefinition getFirstSelectedAutomode() {
//        AutoModeDefinition selectedAuto = null;
//
//        try {
//            String selected = sendableChooserPosition.getSelected().name + sendableChooserFirstTarget.getSelected().name;
//            selected = selected.replaceAll("Hab2", "");
//            selectedAuto = AutoModeDefinition.valueOf(selected);
//        } catch (Exception e) {
//            System.out.println("ERROR with first selection");
//        }
//        return selectedAuto;
//    }
//
//    private static AutoModeDefinition getSecondSelectedAutomode() {
//        AutoModeDefinition selectedAuto = null;
//        try {
//            selectedAuto = AutoModeDefinition.valueOf(sendableChooserFirstTarget.getSelected().name + sendableChooserSecondTarget.getSelected().name);
//        } catch (Exception e) {
//            //System.out.println("ERROR with second selection");
//        }
//        return selectedAuto;
//    }

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
        LFHATCH("LeftFarHatch"),
        RFHATCH("RightFarHatch"),
        LMHATCH("LeftMiddleHatch"),
        RMHATCH("RightMiddleHatch"),
        LCHATCH("LeftCloseHatch"),
        RCHATCH("RightCloseHatch"),
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
//
//    public enum AutoModeDefinition {
//        DontMove(new DontMove()),
//
//        testtest(new Test()),
//        //Single path AMDs
//        StartLeftLeftFarHatch(new LeftToLeftFar()),
//        StartLeftLeftMiddleHatch(new LeftToLeftMiddle()),
//        StartLeftLeftCloseHatch(new LeftToLeftClose()),
//        StartLeftCenterLeftHatch(new LeftToCenterLeft()),
//
//        StartRightCenterRightHatch(new RightToRightMiddle()),
//        StartRightRightFarHatch(new RightToRightFar()),
//        StartRightRightMiddleHatch(new RightToRightClose()),
//        StartRightRightCloseHatch(new RightToCenterRight()),
//
//        StartCenterCenterLeftHatch(new CenterToCenterLeft()),
//        StartCenterCenterRightHatch(new CenterToCenterRight());
//
//
//        public final AutoMode autoMode;
//
//        AutoModeDefinition(AutoMode autoMode) {
//            this.autoMode = autoMode;
//        }
//    }
}

