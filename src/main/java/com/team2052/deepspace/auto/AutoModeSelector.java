package com.team2052.deepspace.auto;

import com.team2052.deepspace.auto.modes.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoModeSelector {
    private static SendableChooser<AutoModeDefinition> sendableChooserAutoMode;

    public static void putToSmartDashboard() { //puts the auto modes and delay options to the smart dashboard
        sendableChooserAutoMode = new SendableChooser<AutoModeDefinition>();
        for (int i = 0; i < AutoModeDefinition.values().length; i++) {
            AutoModeDefinition mode = AutoModeDefinition.values()[i];
            if (i == 0) {
                sendableChooserAutoMode.addDefault(mode.name, mode); //a sendeble Chooser is a list
            } else {
                switch (AutoModeDefinition.values()[i]) { //this is to make sure test or failure autos cannot be choosen during compititions
                    case DONT_MOVE:
                    case TEST:
                    case TEST2:
                    default:
                        sendableChooserAutoMode.addObject(mode.name, mode);
                }
            }
        }
        SmartDashboard.putData("auto_modes", sendableChooserAutoMode);  //allows driver to choose auto modes in Smart Dashboard
    }

    public static AutoModeDefinition getAutoDefinition(){
        try {
            return sendableChooserAutoMode.getSelected();
        } catch (Exception exc) {
            System.out.println("FAILED TO GET AUTO DEFINITION! Defaulting to Don't_Move.");
            return  AutoModeDefinition.DONT_MOVE;
        }
    }



    public enum AutoModeDefinition
    {
        TEST("Test", Test.class),
        DONT_MOVE("Don't Move", DontMove.class),
        TEST2("Test2", Test2.class)
        ;

        //create a class variable type and check if it extends AutoMode. the name of this variable is clazz
        private final Class<? extends  AutoMode> clazz;
        public final String name;

        AutoModeDefinition(String name, Class<? extends AutoMode> clazz){
            this.name = name;
            this.clazz = clazz;
        }

        public AutoModeBase getInstance() { //gets the instance of the AutoModeBase
            AutoModeBase instance;
            try {
                instance = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                return null;
            }
            return instance;
        }
    }
    public enum Side{
        RED, BLUE
    }
}
