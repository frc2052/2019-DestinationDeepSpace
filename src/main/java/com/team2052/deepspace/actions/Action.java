package com.team2052.deepspace.actions;

public interface Action {
    void done();
    void start();
    void update();
    boolean isFinished();
}
