package com.example.canvasdrawing;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public abstract class AnimatedCanvas extends AnimationTimer {
    protected int frameCount = 0;
    protected int frameLimit;
    private boolean looping = false;
    private int frameRate = 20;
    private int frameInterval = 1000000000/frameRate;
    private long lastTick = 0;
    protected Canvas canvas;
    protected GraphicsContext gc;
    AnimatedCanvas(Canvas cnvDrawingArea){
        canvas = cnvDrawingArea;
        gc = cnvDrawingArea.getGraphicsContext2D();
        frameLimit = 1;
        resize(true);
        isLooping(false);

    }
    @Override public void handle(long l) {
        if (lastTick == 0){lastTick= l;}
        if (l-lastTick > frameInterval) {
            lastTick += frameInterval;
            paint();
            frameCount++;
            if (frameCount >= frameLimit) {
                if (looping){
                    frameCount = 0;
                } else {
                    stop();
                }
            }
        }
    }
    protected abstract void paint ();

    protected void setFrameRate(int fr){
        frameRate = fr;
        frameInterval = 1000000000/frameRate;
    }
    public void isLooping(boolean loop){
        looping = loop;
    }

    protected void resize(boolean resizable){
        Stage stage = (Stage)(canvas.getScene().getWindow());
        stage.setResizable(resizable);
    }
}
