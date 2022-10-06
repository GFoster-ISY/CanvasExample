package com.example.canvasdrawing;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AnimatedFish extends AnimatedDrawing{
    int x = 0;
    int y = 0;
    ArrayList<Bubble> bubbles = new ArrayList<>();
    AnimatedFish(Canvas cnvDrawingArea) {
        super(cnvDrawingArea);
        Stage stage = (Stage)(canvas.getScene().getWindow());
        stage.setWidth(800);
        stage.setHeight(400);

        setFrameRate(30);
        frameLimit = 200;
    }
    @Override
    protected void paint() {
        //setShowGuidePoints(true);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLACK);
        gc.setLineWidth(1);
        drawLine(70+x,18+y,60+x,10+y);
        drawLine(60+x,10+y,60+x,20+y);
        drawLine(60+x,20+y,30+x,32+y);
        drawLine(30+x,32+y,10+x,28+y);
        drawLine(10+x,28+y,20+x,40+y);
        drawLine(20+x,40+y,10+x,52+y);
        drawLine(10+x,52+y,30+x,48+y);
        drawLine(30+x,48+y,60+x,60+y);
        drawLine(60+x,60+y,60+x,70+y);
        drawLine(60+x,70+y,70+x,62+y);
        drawArc(70+x,62+y,90+x,58+y,110+x,40+y);
        drawArc(110+x,40+y,90+x,22+y,70+x,18+y);
        drawArc(90+x, 22+y, 85+x, 40+y,90+x,58+y);
        gc.fillOval(93+x,33+y,6,6);
        x+=2;
        y++;
        if (y % 15 == 0){
            bubbles.add(new Bubble(110+x, 35+y));
        }
        for (Bubble b : bubbles){
            b.draw();
        }
    }

    private class Bubble{
        int x;
        double y;
        double f = 0.3;
        Bubble (int x, int y){
            this.x = x;
            this.y = y;
        }
        void draw(){
            gc.strokeOval(x, y, 5,5);
            y -= f;
        }
    } // end of class Bubble
} // end of class AnimatedFish
