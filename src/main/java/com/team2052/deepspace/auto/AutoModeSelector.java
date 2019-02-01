package com.team2052.deepspace.auto;

import com.team2052.deepspace.auto.modes.CenterStart.*;
import com.team2052.deepspace.auto.modes.LeftStart.*;
import com.team2052.deepspace.auto.modes.RightStart.*;
import com.team2052.deepspace.auto.modes.RightStart.RightStartSideRightMiddleHatch;
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
        NONE("Select Start"),
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
        FirstTargetSelection(String name){
            this.name = name;
        }
    }

    //ADD All POSSIBLE COMBONATIONS KEYWORDS  CREATE ALL CLASSES!!!
    public enum SecondTargetSelection
    {


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
        SecondTargetSelection(String name){
            this.name = name;
        }
    }

    public enum AutoModeDefinition
    {

        testtest(Test.class),
        //Single path AMDs
        StartLeftLeftFarHatch(LeftStartSideLeftFarHatch.class),
        StartLeftLeftMiddleHatch(LeftStartSideLeftMiddleHatch.class),
        StartLeftLeftCloseHatch(LeftStartSideLeftCloseHatch.class),
        StartLeftLeftCenterHatch(LeftStartCenterLeftCenterHatch.class),

        StartRightRightCenterHatch(RightStartCenterRightCenterHatch.class),
        StartRightRightFarHatch(RightStartSideRightFarHatch.class),
        StartRightRightMiddleHatch(RightStartSideRightMiddleHatch.class),
        StartRightRightCloseHatch(RightStartSideRightCloseHatch.class),

        StartCenterLeftCenterHatch(CenterStartCenterLeftHatch.class),
        StartCenterRightCenterHatch(CenterStartCenterRightHatch.class),

        //Double path AMDs

        StartCenterCenterLeftHatchLeftFarHatch(CenterStartCenterLeftHatchToLeftFarHatch.class),
        StartCenterCenterLeftHatchLeftMiddleHatch(CenterStartCenterLeftHatchToLeftMiddleHatch.class),
        StartCenterCenterLeftHatchLeftCloseHatch(CenterStartCenterLeftHatchToLeftCloseHatch.class),

        StartCenterCenterRightHatchRightFarHatch(CenterStartCenterRightHatchToRightFarHatch.class),
        StartCenterCenterRightHatchRightMiddleHatch(CenterStartCenterRightHatchToRightMiddleHatch.class),
        StartCenterCenterRightHatchRightCloseHatch(CenterStartCenterRightHatchToRightCloseHatch.class),


        StartLeftLeftFarHatchLeftCloseHatch(LeftStartSideLeftFarHatchToLeftCloseHatch.class),
        StartLeftLeftFarHatchLeftMiddleHatch(LeftStartSideLeftFarHatchToLeftMiddleHatch.class),

        StartLeftLeftMiddleHatchLeftFarHatch(LeftStartSideLeftMiddleHatchToLeftFarHatch.class),
        StartLeftLeftMiddleHatchLeftCloseHatch(LeftStartSideLeftFarHatchToLeftCloseHatch.class),

        StartLeftLeftCloseHatchLeftMiddleHatch(LeftStartSideLeftCloseHatchToLeftMiddleHatch.class),
        StartLeftLeftCloseHatchLeftFarHatch(LeftStartSideLeftCloseHatchToLeftFarHatch.class),

        StartLeftCenterLeftHatchLeftCloseHatch(LeftStartCenterLeftHatchToLeftCloseHatch.class),
        StartLeftCenterLeftHatchLeftMiddleHatch(LeftStartCenterLeftHatchToLeftMiddleHatch.class),
        StartLeftCenterLeftHatchLeftFarHatch(LeftStartCenterLeftHatchToLeftFarHatch.class),


        StartRightCenterRightHatchRightCloseHatch(RightStartCenterRightHatchToRightCloseHatch.class),
        StartRightCenterRightHatchRightFarHatch(RightStartCenterRightHatchToRightFarHatch.class),
        StartRightCenterRightHatchRightMiddleHatch(RightStartCenterRightHatchToRightMiddleHatch.class),


        StartRightRightCloseHatchRightFarHatch(RightStartSideRightCloseHatchToRightFarHatch.class),
        StartRightRightCloseHatchRightMiddleHatch(RightStartSideRightCloseHatchToRightMiddleHatch.class),


        StartRightRightMiddleHatchRightFarHatch(RightStartSideRightMiddleHatchToRightFarHatch.class),
        StartRightRightMiddleHatchRightCloseHatch(RightStartSideRightMiddleHatchToRightCloseHatch.class),

        StartRightRightFarHatchRightMiddleHatch(RightStartSideRightFarHatchToRightMiddleHatch.class),
        StartRightRightFarHatchRightCloseHatch(RightStartSideRightFarHatchToRightCloseHatch.class);


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
