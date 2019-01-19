package com.team2052.deepspace.auto;

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
    }
    public static AutoModeDefinition getSelectedAutomode(){
        String selected = sendableChooserPosition.getSelected().name + sendableChooserFirstTarget.getSelected().name
                + (sendableChooserSecondTarget.getSelected().name.equals("none") ? "" : sendableChooserSecondTarget.getSelected().name);
        AutoModeDefinition selectedMode = null;
        try{
            selectedMode = AutoModeDefinition.valueOf(selected);
            SmartDashboard.putBoolean("Does AutoMode Exist?", true);
        }catch(Exception e){
            SmartDashboard.putBoolean("Does AutoMode Exist?", false);
        }
        return selectedMode;
    }

    public enum PositionSelection
    {
        LEFT("LeftStart"),
        Center("StartCenter"),
        Right("StartRight");

        public String name;
        PositionSelection(String name){
            this.name = name;
        }
    }

    public enum FirstTargetSelection
    {
        CLHATCH("CenterLeftHatch"),
        CRHATCH("CenterRightHatch");

        public String name;
        FirstTargetSelection(String name){
            this.name = name;
        }
    }

    public enum SecondTargetSelection
    {
        NONE("None"),
        CLHATCH("CenterLeftHatch"),
        CRHATCH("CenterRightHatch");

        public String name;
        SecondTargetSelection(String name){
            this.name = name;
        }
    }

    public enum AutoModeDefinition
    {
        LeftStartCenterLeftHatch(Test.class);

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
