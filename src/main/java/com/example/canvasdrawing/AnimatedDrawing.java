package com.example.canvasdrawing;

import javafx.scene.canvas.Canvas;
import javafx.scene.shape.ArcType;

public abstract class AnimatedDrawing extends AnimatedCanvas{

    boolean showGuidePoints = false;
    AnimatedDrawing(Canvas cnvDrawingArea) {
        super(cnvDrawingArea);
    }

    public void setShowGuidePoints(boolean show){showGuidePoints = show;}

    protected void drawLine(int x1, int y1, int x2, int y2){
        if (showGuidePoints) {
            gc.fillOval(x1 - 2, y1 - 2, 4, 4);
            gc.fillOval(x2 - 2, y2 - 2, 4, 4);
        }
        gc.strokeLine(x1, y1, x2, y2);
    }
    protected void drawArc(int x1, int y1,
                            int x2, int y2,
                            int x3, int y3)
    {
        // Calculate the difference between each point
        int x12 = x1 - x2;
        int x13 = x1 - x3;

        int y12 = y1 - y2;
        int y13 = y1 - y3;

        int y31 = y3 - y1;
        int y21 = y2 - y1;

        int x31 = x3 - x1;
        int x21 = x2 - x1;

        // calculate the square of the distance between points 1, 3 and 1, 2
        double sx13 = Math.pow(x1, 2) - Math.pow(x3, 2);
        double sy13 = Math.pow(y1, 2) - Math.pow(y3, 2);

        double sx21 = Math.pow(x2, 2) - Math.pow(x1, 2);
        double sy21 = Math.pow(y2, 2) - Math.pow(y1, 2);

        double f = ((sx13) * (x12) + (sy13) * (x12) + (sx21) * (x13) + (sy21) * (x13))
                / (2 * ((y31) * (x12) - (y21) * (x13)));
        double g = ((sx13) * (y12) + (sy13) * (y12) + (sx21) * (y13) + (sy21) * (y13))
                / (2 * ((x31) * (y12) - (x21) * (y13)));
        double c = -Math.pow(x1, 2) - Math.pow(y1, 2) -
                2 * g * x1 - 2 * f * y1;

        // eqn of circle be x^2 + y^2 + 2*g*x + 2*f*y + c = 0
        // where centre is (h = -g, k = -f) and radius r
        // as r^2 = h^2 + k^2 - c

        // r is the radius
        double r = Math.sqrt(f*f+g*g-c);
        double leftCoord = -g-r;
        double topCoord = -f-r;
        if (showGuidePoints) {
            gc.fillOval(x1 - 2, y1 - 2, 4, 4);
            gc.fillOval(x2 - 2, y2 - 2, 4, 4);
            gc.fillOval(x3 - 2, y3 - 2, 4, 4);
        }
        double toDegrees = 180/Math.PI;
        // Find the starting angle
        int extra = findExtraAngle((int)-g, (int)-f, x1, y1);
        double angle1 = Math.atan((-f-y1)/(x1+g))*toDegrees+extra;
        // Find the ending angle
        extra = findExtraAngle((int)-g, (int)-f, x3, y3);
        double angle2 = Math.atan((-f-y3)/(x3+g))*toDegrees+extra;
        // Draw the arc.
        gc.strokeArc(leftCoord,topCoord,2*r, 2*r,angle1, angle2-angle1, ArcType.OPEN);
    }

    private int findExtraAngle(int x0, int y0, int x1, int y1){
        // find which quadrant the point (x1, y1) is in relative to (x0, y0)
        if (x1>x0 && y1<y0) {return 0;} // quadrant 1
        else if (x1>x0 && y1>y0) {return 360;} // quadrant 4
        else {return 180;} // either quadrant 2 or 3
    }
}
