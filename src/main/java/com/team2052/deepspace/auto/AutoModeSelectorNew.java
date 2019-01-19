package com.team2052.deepspace.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutoModeSelectorNew {
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

    public enum PositionSelection
    {
        LEFT("StartLeft","SL"),
        Center("StartCenter","SC"),
        Right("StartRight","SR");

        private String name, codeName;
        PositionSelection(String name, String codeName){
            this.name = name;
            this.codeName = codeName;
        }
    }

    public enum FirstTargetSelection
    {
        CLHATCH("Center Left Hatch","CLH"),
        CRHATCH("Center Right Hatch","CRH");

        private String name, codeName;
        FirstTargetSelection(String name, String codeName){
            this.name = name;
            this.codeName = codeName;
        }
    }

    public enum SecondTargetSelection
    {
        CLHATCH("Center Left Hatch","CLH"),
        CRHATCH("Center Right Hatch","CRH");

        private String name, codeName,flag;
        SecondTargetSelection(String name, String codeName){
            this.name = name;
            this.codeName = codeName;
        }
    }
}
