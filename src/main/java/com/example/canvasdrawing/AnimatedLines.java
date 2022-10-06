package com.example.canvasdrawing;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class AnimatedLines extends AnimatedCanvas{

    ArrayList<Line> lines = new ArrayList<>();

    AnimatedLines(Canvas cnvDrawingArea) {
        super(cnvDrawingArea);
        Random r = new Random();
        Color[] colours = {Color.RED, Color.BLUE, Color.GREEN, Color.PURPLE, Color.ORANGE};
        for(int i=0; i< (int)canvas.getWidth()/10; i++) {
            lines.add(new Line(r.nextInt((int)canvas.getWidth())
                    ,r.nextInt((int)canvas.getHeight())
                    ,r.nextInt((int)canvas.getWidth())
                    ,r.nextInt((int)canvas.getHeight())
                    ,r.nextInt(-5,5)
                    ,r.nextInt(-5,5)
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
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Line line : lines){
            line.draw(gc);
        }
    }

    private class Line{
        int x1;
        int x2;
        int y1;
        int y2;
        int vx1;
        int vx2;
        int vy1;
        int vy2;
        Color colour;
        Line(int x1, int y1, int x2, int y2, int vx1, int vy1, int vx2, int vy2, Color colour){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.vx1 = vx1;
            if (this.vx1 == 0){this.vx1 = 1;}
            this.vy1 = vy1;
            if (this.vy1 == 0){this.vy1 = 1;}
            this.vx2 = vx2;
            if (this.vx2 == 0){this.vx2 = 1;}
            this.vy2 = vy2;
            if (this.vy2 == 0){this.vy2 = 1;}
            this.colour = colour;
        }
        public void draw(GraphicsContext gc){
            move();
            gc.setStroke(colour);
            gc.strokeLine(x1,y1,x2,y2);
        }
        private void move(){
            x1 += vx1;
            if (x1 < 0 || x1 > canvas.getWidth()){
                vx1 *= -1;
                x1 += vx1 + vx1;
            }
            y1 += vy1;
            if (y1 < 0 || y1 > canvas.getHeight()){
                vy1 *= -1;
                y1 += vy1 +vy1;
            }
            x2 += vx2;
            if (x2 < 0 || x2 > canvas.getWidth()){
                vx2 *= -1;
                x2 += vx2 + vx2;
            }
            y2 += vy2;
            if (y2 < 0 || y2 > canvas.getHeight()){
                vy2 *= -1;
                y2 += vy2 +vy2;
            }
        }
    }

}
