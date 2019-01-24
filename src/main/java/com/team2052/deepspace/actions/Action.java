package com.team2052.deepspace.actions;

public interface Action {
    void done();

    boolean isFinished();

    void start();

    void update();
}
