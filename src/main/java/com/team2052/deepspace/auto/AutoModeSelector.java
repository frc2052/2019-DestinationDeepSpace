package com.team2052.deepspace.auto;

import com.team2052.deepspace.auto.modes.CenterStart.*;
import com.team2052.deepspace.auto.modes.LeftStart.*;
import com.team2052.deepspace.auto.modes.RightStart.*;
import com.team2052.deepspace.auto.modes.Test;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoModeSelector {
    private static SendableChooser<PositionSelection> sendableChooserPosition;
    private static SendableChooser<FirstTargetSelection> sendableChooserFirstTarget;
    private static SendableChooser<SecondTargetSelection> sendableChooserSecondTarget;


    public static void putToShuffleBoard(){
        sendableChooserPosition = new SendableChooser<PositionSelection>();
        sendableChooserFirstTarget = new SendableChooser<FirstTargetSelection>();
        sendableChooserSecondTarget = new SendableChooser<SecondTargetSelection>();

        for (int i = 0; i < PositionSelection.values().length; i++){
            PositionSelection mode = PositionSelection.values()[i];
            if(i == 0){
                sendableChooserPosition.setDefaultOption(mode.name,mode);
            }else{
                sendableChooserPosition.addOption(mode.name, mode);
            }
        }

        for (int i = 0; i < FirstTargetSelection.values().length; i++){
            FirstTargetSelection mode = FirstTargetSelection.values()[i];
            if(i == 0){
                sendableChooserFirstTarget.setDefaultOption(mode.name,mode);
            }else{
                sendableChooserFirstTarget.addOption(mode.name, mode);
            }
        }

        for (int i = 0; i < SecondTargetSelection.values().length; i++){
            SecondTargetSelection mode = SecondTargetSelection.values()[i];
            if(i == 0){
                sendableChooserSecondTarget.setDefaultOption(mode.name,mode);
            }else{
                sendableChooserSecondTarget.addOption(mode.name, mode);
            }
        }
        SmartDashboard.putData("Auto Start Pos", sendableChooserPosition);
        SmartDashboard.putData("First Target", sendableChooserFirstTarget);
        SmartDashboard.putData("Second Target", sendableChooserSecondTarget);
    }

    public static AutoModeDefinition getSelectedAutomode(){
        String selected = sendableChooserPosition.getSelected().name + sendableChooserFirstTarget.getSelected().name
                + (sendableChooserSecondTarget.getSelected().name.equals("None") ? "" : sendableChooserSecondTarget.getSelected().name);
        AutoModeDefinition selectedMode = null;
        try{
            //System.out.println("Selected" + selected);
            selectedMode = AutoModeDefinition.valueOf(selected);
            SmartDashboard.putBoolean("Does AutoMode Exist?", true);
        }catch(Exception e){
            SmartDashboard.putBoolean("Does AutoMode Exist?", false);
        }
        return selectedMode;
    }

    public enum PositionSelection
    {
        LEFT("StartLeft"),
        CENTER("StartCenter"),
        RIGHT("StartRight"),
        TEST("test");

        public String name;
        PositionSelection(String name){
            this.name = name;
        }
    }

    public enum FirstTargetSelection
    {
        CLH("LeftCenterHatch"),
        CRH("RightCenterHatch"),
        SL("LeftSide"),
        SR("RightSide"),
        CLHT("LeftCenterHatchTo"),
        CRHT("RightCenterHatchTo"),
        SLT("LeftSideHatchTo"),
        SRT("RightSideHatchTo"),
        SLMHT("LeftSideMiddleHatchTo"),
        SLCHT("LeftSideCloseHatchTo"),
        SLFHT("LeftSideFarHatchTo"),
        SRCHT("RightSideCloseHatchTo"),
        SRMHT("RightSideMiddleHatchTo"),
        SRFHT("RightSideFarHatchTo"),
        TEST("test");

        public String name;
        FirstTargetSelection(String name){
            this.name = name;
        }
    }

    //ADD All POSSIBLE COMBONATIONS KEYWORDS TO CREATE ALL CLASSES!!!
    public enum SecondTargetSelection
    {


        NONE("None"),
        LHATCH("LeftHatch"),
        RHATCH("RightHatch"),
        LFHATCH("LeftFarHatch"),
        RFHATCH("RightFarHatch"),
        LMHATCH("LeftMiddleHatch"),
        RMHATCH("RightMiddleHatch"),
        LCHATCH("LeftCloseHatch"),
        RCHATCH("RightCloseHatch");


        public String name;
        SecondTargetSelection(String name){
            this.name = name;
        }
    }

    public enum AutoModeDefinition
    {

        testtest(Test.class),
        //Single path AMDs
        StartLeftLeftSideLeftFarHatch(LeftStartSideLeftFarHatch.class),
        StartLeftLeftSideLeftMiddleHatch(LeftStartSideLeftMiddleHatch.class),
        StartLeftLeftSideLeftCloseHatch(LeftStartSideLeftCloseHatch.class),
        StartLeftCenterLeftCenterHatch(LeftStartCenterLeftCenterHatch.class),

        StartRightCenterRightCenterHatch(RightStartCenterRightCenterHatch.class),
        StartRightRightSideRightFarHatch(RightStartSideRightFarHatch.class),
        StartRightRightSideRightMiddleHatch(RightStartSideRightMiddleHatch.class),
        StartRightRightSideRightCloseHatch(RightStartSideRightCloseHatch.class),

        StartCenterLeftCenterHatch(CenterStartCenterLeftHatch.class),
        StartCenterRightCenterHatch(CenterStartCenterRightHatch.class),

        //Double path AMDs

        StartCenterLeftCenterHatchToLeftFarHatch(CenterStartCenterLeftHatchToLeftFarHatch.class),
        StartCenterLeftCenterHatchToLeftMiddleHatch(CenterStartCenterLeftHatchToLeftMiddleHatch.class),
        StartCenterLeftCenterHatchToLeftCloseHatch(CenterStartCenterLeftHatchToLeftCloseHatch.class),

        StartCenterRightCenterHatchToRightFarHatch(CenterStartCenterRightHatchToRightFarHatch.class),
        StartCenterRightCenterHatchToRightMiddleHatch(CenterStartCenterRightHatchToRightMiddleHatch.class),
        StartCenterRightCenterHatchToRightCloseHatch(CenterStartCenterRightHatchToRightCloseHatch.class),


        StartLeftLeftSideFarHatchToLeftCloseHatch(LeftStartSideLeftFarHatchToLeftCloseHatch.class),
        StartLeftLeftSideFarHatchToLeftMiddleHatch(LeftStartSideLeftFarHatchToLeftMiddleHatch.class),

        StartLeftSideLeftMiddleHatchToLeftFarHatch(LeftStartSideLeftMiddleHatchToLeftFarHatch.class),
        StartLeftSideLeftMiddleHatchToLeftCloseHatch(LeftStartSideLeftFarHatchToLeftCloseHatch.class),

        StartLeftSideLeftSideCloseHatchToLeftMiddleHatch(LeftStartSideLeftCloseHatchToLeftMiddleHatch.class),
        StartLeftSideLeftSideCloseHatchToLeftFarHatch(LeftStartSideLeftCloseHatchToLeftFarHatch.class),

        StartLeftLeftCenterHatchToLeftCloseHatch(LeftStartCenterLeftHatchToLeftCloseHatch.class),
        StartLeftLeftCenterHatchToLeftMiddleHatch(LeftStartCenterLeftHatchToLeftMiddleHatch.class),
        StartLeftLeftCenterHatchToLeftFarHatch(LeftStartCenterLeftHatchToLeftFarHatch.class),


        StartRightRightCenterHatchToRightCloseHatch(RightStartCenterRightHatchToRightCloseHatch.class),
        StartRightRightCenterHatchToRightFarHatch(RightStartCenterRightHatchToRightFarHatch.class),
        StartRightRightCenterHatchToRightMiddleHatch(RightStartCenterRightHatchToRightMiddleHatch.class),


        StartRightRightSideCloseHatchToRightFarHatch(RightStartSideRightCloseHatchToRightFarHatch.class),
        StartRightRightSideCloseHatchToRightMiddleHatch(RightStartSideRightCloseHatchToRightMiddleHatch.class),


        StartRightRightSideMiddleHatchToRightFarHatch(RightStartSideRightMiddleHatchToRightFarHatch.class),
        StartRightRightSideMiddleHatchToRightCloseHatch(RightStartSideRightMiddleHatchToRightCloseHatch.class),

        StartRightRightSideFarHatchToRightMiddleHatch(RightStartSideRightFarHatchToRightMiddleHatch.class),
        StartRightRightSideFarHatchToRightCloseHatch(RightStartSideRightFarHatchToRightCloseHatch.class);


        private final Class<? extends AutoMode> clazz;

        AutoModeDefinition(Class<? extends  AutoMode> clazz){
            this.clazz = clazz;
        }

        public AutoModeBase getInstance() { //gets the instance of the AutoModeBase
            AutoModeBase instance;
            try {
                instance = clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                System.out.println("CREATION OF AUTOMODE FAILED");
                return null;
            }
            return instance;
        }
    }
}
