package com.team2052.deepspace.auto;

public class AutoModeFactory {
    private static AutoModeSelector.AutoModeDefinition loadedDefinition = AutoModeSelector.AutoModeDefinition.DontMove;
    private static AutoMode loadedMode = null;

    public static AutoMode getAutoMode(AutoModeSelector.AutoModeDefinition requestedDefinition) {
        if (loadedDefinition != requestedDefinition) {
            loadedDefinition = requestedDefinition;
            loadedMode = requestedDefinition.createInstance();
        }
        return loadedMode;
    }

}
