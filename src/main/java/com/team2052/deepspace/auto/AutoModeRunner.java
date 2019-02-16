package com.team2052.deepspace.auto;

public class AutoModeRunner {

    private static AutoModeRunner instance = null;
    public static AutoModeRunner getInstance() {
        if (instance == null) {
            try {
                instance = new AutoModeRunner();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create AutoModeRunner: " + exc.getMessage());
                exc.printStackTrace();
            }
        }
        return instance;
    }
    Thread autoThread = null;
    AutoModeBase autoMode;

    public void start(AutoModeBase newMode) {//Initializes auto mode
        if (this.autoMode != null) { //there is already a auto mode, should only happen in testing
            try {
                System.out.println("Existing Automode already in AutomodeRunner");
                System.out.println(this.autoMode.getClass().getName());
                this.autoMode.stop();
            }
            catch (Exception e)
            {
                System.out.println("Failed to stop existing Automode");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        this.autoMode = newMode;
        if (this.autoMode == null) {
            return;
        }
        System.out.println("Starting new automode " + this.autoMode.getClass().getName());
        autoThread = new Thread(() -> this.autoMode.start());
        autoThread.start();
    }

    public void stop() {//Stops auto mode
        if (autoMode != null) {
            autoMode.stop();
            autoMode = null;
        }
        autoThread = null;
    }

    public boolean isAutodone(){
        try {
            return !autoMode.isRunning();
        }catch(Exception e){
            return true;
        }
    }
}
