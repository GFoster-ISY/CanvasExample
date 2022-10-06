package com.example.canvasdrawing;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class AnimatedGrid extends AnimatedCanvas{
    AnimatedGrid(Canvas cnvDrawingArea) {
        super(cnvDrawingArea);
    }

    protected void paint(){
        setFrameRate(60);
        int step = 5; // The number of dots to draw per animation frame
        int gap = 10; // The gap between each dot
        // Recalculate the frameLimit in case the user has resized the screen
        frameLimit = (int)(canvas.getWidth()/gap * canvas.getHeight()/gap)/step;
        // Clear the screen and then redraw all the dots up to the frame count
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        for (int y = 0; y< canvas.getHeight()/gap; y++){
            for (int x = 0; x< canvas.getWidth()/gap; x++){
                gc.fillOval(gap + x*gap, gap + y*gap, 1,1);
                // exit if the frame count has been reached
                if ((x+y*canvas.getWidth()/gap)/step >= frameCount){return;}
            }
        }
    }

}
