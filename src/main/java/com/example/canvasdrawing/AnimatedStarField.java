package com.example.canvasdrawing;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class AnimatedStarField extends AnimatedCanvas {

    AnimatedStarField(Canvas cnvDrawingArea) {
        super(cnvDrawingArea);
        initStarField((int)canvas.getWidth()/2);
    }

    @Override
    protected void paint() {
        starField();
    }

    private class Star{
        int x;
        int y;
        int r;
        Color colour;
        Random rand = new Random();

        Star (int x, int y, int r, Color colour){
            this.x = x;
            this.y = y;
            this.r = r;
            this.colour = colour;
        }
        public void draw(GraphicsContext gc){
            int resize = 0;
            if (rand.nextInt(5)>3) {
                resize = rand.nextInt(r) - (r / 2);
            }
            gc.setFill(colour);
            gc.fillOval(x,y,r+resize,r+resize);
        }
    }
    ArrayList<Star> stars = new ArrayList<>();
    protected void initStarField(int starCount){
        Stage stage = (Stage)(canvas.getScene().getWindow());
        stage.setWidth(1000);
        stage.setHeight(800);
        setFrameRate(10);
        frameLimit = 100;
        resize(false);
        isLooping(true);

        Color[] colours = {Color.WHITE, Color.ANTIQUEWHITE, Color.LIGHTYELLOW, Color.LAVENDER, Color.LIGHTSKYBLUE};
        Random r = new Random();
        for (int i=0; i<starCount; i++){
            stars.add(new Star(r.nextInt((int)canvas.getWidth())
                    ,r.nextInt((int)canvas.getHeight())
                    ,r.nextInt(4)+1
                    ,colours[r.nextInt(5)]));
        }
    }
    protected void starField(){
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        for (Star star: stars){
            star.draw(gc);
        }
    }

}
