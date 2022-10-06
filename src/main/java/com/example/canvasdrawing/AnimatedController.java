package com.example.canvasdrawing;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

public class AnimatedController {
    static AnimatedController controller;
    @FXML private Label lblDimensions;
    @FXML private Canvas cnvDrawingArea;
    private AnimatedCanvas timer = null;

    @FXML private void onClickStarField(){
        if (timer != null){timer.stop();}
        timer = new AnimatedStarField(cnvDrawingArea);
        timer.start();
    }
    @FXML private void onClickGrid(){
        if (timer != null){timer.stop();}
        timer = new AnimatedGrid(cnvDrawingArea);
        timer.start();
    }
    @FXML private void onClickBalls(){
        if (timer != null){timer.stop();}
        timer = new AnimatedBalls(cnvDrawingArea);
        timer.start();
    }
    @FXML private void onClickLines(){
        if (timer != null){timer.stop();}
        timer = new AnimatedLines(cnvDrawingArea);
        timer.start();
    }
    @FXML private void onClickNotes(){
        if (timer != null){timer.stop();}
        timer = new AnimatedNotes(cnvDrawingArea);
        timer.start();
    }
    @FXML private void onClickFish(){
        if (timer != null){timer.stop();}
        timer = new AnimatedFish(cnvDrawingArea);
        timer.start();
    }
    private void displayDimensions(){
        lblDimensions.setText((int)cnvDrawingArea.getWidth() + " x " + (int)cnvDrawingArea.getHeight());
    }
    public void setWidth(int newWidth){
        cnvDrawingArea.setWidth(newWidth-200);
        displayDimensions();
        draw();
    }
    public void setHeight(int newHeight){
        cnvDrawingArea.setHeight(newHeight);
        displayDimensions();
        draw();
    }

    public void initialize() {
        controller = this;
        draw();
    }

    private void draw(){
//        GraphicsContext gc = cnvDrawingArea.getGraphicsContext2D();
//        gc.clearRect(0,0,cnvDrawingArea.getWidth(), cnvDrawingArea.getHeight());
//        gc.strokeRect(10,10, 50, 50);
//        gc.strokeRect(cnvDrawingArea.getWidth()-60, 10, 50, 50);
//        gc.strokeRect(10,cnvDrawingArea.getHeight()-60, 50, 50);
//        gc.strokeRect(cnvDrawingArea.getWidth()-60, cnvDrawingArea.getHeight()-60, 50, 50);
    }
}