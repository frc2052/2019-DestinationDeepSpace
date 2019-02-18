package com.team2052.deepspace.auto;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.actions.Action;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.modes.CenterStart.*;
import com.team2052.deepspace.auto.modes.DontMove;
import com.team2052.deepspace.auto.modes.LeftStart.*;
import com.team2052.deepspace.auto.modes.RightStart.*;
import com.team2052.deepspace.auto.modes.Test;
import com.team2052.lib.Autonomous.Position2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Arrays;

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
        return getFirstSelectedAutomode().autoMode.getStartDirection().isForward;
    }

    public static Action getSelectedAction(){
        AutoModeDefinition firstSelected = getFirstSelectedAutomode();
        AutoModeDefinition secondSelected = getSecondSelectedAutomode();
        Action autoAction = null;
        try {
            //System.out.println("Selected" + selected);

            if(sendableChooserSecondTarget.getSelected().name.equals("none")) {
                autoAction = firstSelected.autoMode.action;
            }else{
                autoAction = new SeriesAction(Arrays.asList(
                        firstSelected.autoMode.getAction(),
                        secondSelected.autoMode.getAction()
                ));
            }
            SmartDashboard.putBoolean("Does AutoMode Exist?", true);
        } catch (Exception e) {
            SmartDashboard.putBoolean("Does AutoMode Exist?", false);
        }
        return autoAction;

    }

    private static AutoModeDefinition getFirstSelectedAutomode() {
        AutoModeDefinition selectedAuto = null;

        try {
            String selected = sendableChooserPosition.getSelected().name + sendableChooserFirstTarget.getSelected().name;
            selected = selected.replaceAll("Hab2", "");
            selectedAuto = AutoModeDefinition.valueOf(selected);
        } catch (Exception e) {
            System.out.println("ERROR with first selection");
        }
        return selectedAuto;
    }

    private static AutoModeDefinition getSecondSelectedAutomode() {
        AutoModeDefinition selectedAuto = null;
        try {
            selectedAuto = AutoModeDefinition.valueOf(sendableChooserFirstTarget.getSelected().name + sendableChooserSecondTarget.getSelected().name);
        } catch (Exception e) {
            System.out.println("ERROR with second selection");
        }
        return selectedAuto;
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
        LHATCH("CenterLeftHatch"),
        RHATCH("CenterRightHatch"),
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

    public enum AutoModeDefinition {
        DontMove(new DontMove()),

        testtest(new Test()),
        //Single path AMDs
        StartLeftLeftFarHatch(new LeftToLeftFar()),
        StartLeftLeftMiddleHatch(new LeftToLeftMiddle()),
        StartLeftLeftCloseHatch(new LeftToLeftClose()),
        StartLeftCenterLeftHatch(new LeftToCenterLeft()),

        StartRightCenterRightHatch(new RightToRightMiddle()),
        StartRightRightFarHatch(new RightToRightFar()),
        StartRightRightMiddleHatch(new RightToRightClose()),
        StartRightRightCloseHatch(new RightToCenterRight()),

        StartCenterCenterLeftHatch(new CenterToCenterLeft()),
        StartCenterCenterRightHatch(new CenterToCenterRight());


        public final AutoMode autoMode;

        AutoModeDefinition(AutoMode autoMode) {
            this.autoMode = autoMode;
        }
    }
}

