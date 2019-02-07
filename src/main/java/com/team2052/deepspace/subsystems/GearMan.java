package com.team2052.deepspace.subsystems;

import edu.wpi.first.wpilibj.Solenoid;

public class GearMan {
    private Solenoid Solenoid1 = null;
    private Solenoid Solenoid2 = null;
    public GearMan() {
        Solenoid1 = new Solenoid(1);
        Solenoid2 = new Solenoid(2);
    }
    public boolean setSolenoid1 = false;
    public boolean setSolenoid2 = true;
    private void setOpen (boolean open){
        

    }
}
