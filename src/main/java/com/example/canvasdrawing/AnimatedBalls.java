package com.example.canvasdrawing;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class AnimatedBalls extends AnimatedCanvas{
    ArrayList<Ball> balls = new ArrayList<>();
    AnimatedBalls(Canvas cnvDrawingArea) {
        super(cnvDrawingArea);
        Random r = new Random();
        Color[] colours = {Color.RED, Color.BLUE, Color.GREEN, Color.PURPLE, Color.ORANGE};
        for(int i=0; i< (int)canvas.getWidth()/10; i++) {
            balls.add(new Ball(r.nextInt((int)canvas.getWidth())
                              ,r.nextInt((int)canvas.getHeight())
                              ,r.nextInt(5,15)
                              ,r.nextInt(-5,5)
                              ,r.nextInt(-5,5)
                              ,colours[r.nextInt(5)]
                              ));
        }
        frameLimit = 100;
        isLooping(true);

    }

    @Override
    protected void paint() {
        // Clear the screen and then redraw all the balls
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Ball ball : balls){
            ball.draw(gc);
        }
    }
    private class Ball{
        int x;
        int y;
        int r;
        int vx;
        int vy;
        Color colour;
        Ball(int x, int y, int r, int vx, int vy, Color colour){
            this.x = x;
            this.y = y;
            this.r = r;
            this.vx = vx;
            if (this.vx == 0){this.vx = 1;}
            this.vy = vy;
            if (this.vy == 0){this.vy = 1;}
            this.colour = colour;
        }
        public void draw(GraphicsContext gc){
            move();
            gc.setFill(colour);
            gc.fillOval(x,y,r,r);
        }
        private void move(){
            x += vx;
            if (x < 0 || x+r > canvas.getWidth()){
                vx *= -1;
                x += vx + vx;
            }
            y += vy;
            if (y < 0 || y+r > canvas.getHeight()){
                vy *= -1;
                y += vy +vy;
            }
        }
    }
}
