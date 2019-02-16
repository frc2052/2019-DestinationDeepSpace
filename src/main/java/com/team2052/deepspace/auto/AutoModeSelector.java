package com.team2052.deepspace.auto;

import com.team2052.deepspace.auto.modes.CenterStart.*;
import com.team2052.deepspace.auto.modes.DontMove;
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
        SmartDashboard.putBoolean("Start Hab 2?",false);
        SmartDashboard.putData("Auto Start Pos", sendableChooserPosition);
        SmartDashboard.putData("First Target", sendableChooserFirstTarget);
        SmartDashboard.putData("Second Target", sendableChooserSecondTarget);
    }
    public static boolean getHab2Start (){
        return SmartDashboard.getBoolean("Start Hab 2?", false);
    }
    public static AutoModeDefinition getSelectedAutomode(){
        String selected = "";
        try {
            selected = sendableChooserPosition.getSelected().name + sendableChooserFirstTarget.getSelected().name
                    + (sendableChooserSecondTarget.getSelected().name.equals("None") ? "" : sendableChooserSecondTarget.getSelected().name);
        }catch (Exception e){
            System.out.println("ERROR AT SELECTED");
        }
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
        DontMove(DontMove.class),

        testtest(Test.class),
        //Single path AMDs
        StartLeftLeftFarHatch(LeftStartSideLeftFarHatch.class),
        StartLeftLeftMiddleHatch(LeftStartSideLeftMiddleHatch.class),
        StartLeftLeftCloseHatch(LeftStartSideLeftCloseHatch.class),
        StartLeftCenterLeftHatch(LeftStartCenterLeftCenterHatch.class),

        StartRightCenterRightHatch(RightStartCenterRightCenterHatch.class),
        StartRightRightFarHatch(RightStartSideRightFarHatch.class),
        StartRightRightMiddleHatch(RightStartSideRightMiddleHatch.class),
        StartRightRightCloseHatch(RightStartSideRightCloseHatch.class),

        StartCenterCenterLeftHatch(CenterStartCenterLeftHatch.class),
        StartCenterCenterRightHatch(CenterStartCenterRightHatch.class),

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

        public AutoMode createInstance(int forwardOffset) { //creates a new instance of the AutoModeBase
            //AutoMode instance = tryCreateWithIntConstructor(forwardOffset);
            //if (instance == null){
                //instance = tryCreateDefaultConstructor();
            //}
            return tryCreateDefaultConstructor();
        }

        private AutoMode tryCreateWithIntConstructor(int forwardOffset) {
            AutoMode instance;
            try {
                //Look for constructer with int parameter
                Class[] carg = new Class[1];
                carg[0] = int.class;
                instance = clazz.getDeclaredConstructor(carg).newInstance(forwardOffset);
                return instance;
            } catch (NoSuchMethodException nsm) {
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("CREATION OF AUTOMODE FAILED");
                return null;
            }
        }

        private AutoMode tryCreateDefaultConstructor() {
            AutoMode instance;
            try {
                System.out.println(clazz.getSimpleName() + "");
                instance = clazz.getDeclaredConstructor().newInstance();
                return instance;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("CREATION OF AUTOMODE FAILED");
                return null;
            }
        }


    }

    public static int getForwardOffset(){
        if(SmartDashboard.getBoolean("Start Hab 2?", false)){
            return -48;
        }else{
            return 0;
        }
    }
}
