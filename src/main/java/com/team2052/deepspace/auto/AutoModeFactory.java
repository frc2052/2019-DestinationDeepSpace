package com.team2052.deepspace.auto;

public class AutoModeFactory {
    private static AutoModeSelector.AutoModeDefinition loadedDefinition = AutoModeSelector.AutoModeDefinition.DontMove;
    private static boolean loadedStartingHab2;
    private static AutoMode loadedMode = null;

    public static AutoMode getAutoMode(AutoModeSelector.AutoModeDefinition requestedDefinition, boolean startingHab2) {
        if (loadedDefinition != requestedDefinition || startingHab2 != loadedStartingHab2) {
            int forwardOffset = 0;
            if(startingHab2){
                forwardOffset = -48;
            }
            loadedDefinition = requestedDefinition;
            loadedMode = requestedDefinition.createInstance(forwardOffset);
            loadedStartingHab2 = startingHab2;
        }
        return loadedMode;
    }

}
