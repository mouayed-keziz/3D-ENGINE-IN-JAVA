package Renderer.point;

import Renderer.*;

import java.awt.Point;

public class PointConverter {

    public static double scale = 1;

    public static void ZoomOut() {
        PointConverter.scale -= 0.05;
    }

    public static void ZoomIn() {
        PointConverter.scale += 0.05;
    }
    public static Point convertPoint(Point3D point3d) {
        double x3d = point3d.y * scale;
        double y3d = point3d.z * scale;
        double depth = point3d.x * scale;
        double[] newVal = scale(x3d,y3d,depth);
        int x2d = (int)(Display.WIDTH / 2 + newVal[0]);
        int y2d = (int)(Display.HEIGHT / 2 - newVal[1]);
        return new Point(x2d,y2d);
    }

    private static double[] scale(double x3d, double y3d, double depth) {
        double dist = Math.sqrt((x3d * x3d) + (y3d * y3d));
        double alpha = Math.atan2(y3d,x3d);
        double renderedDepth = Display.Camera.x - depth;
        double localScale = Math.abs((renderedDepth + 1400)/1400);
        dist = dist * localScale;
        double[] newVal = new double[2];
        newVal[0] = dist * Math.cos(alpha);
        newVal[1] = dist * Math.sin(alpha);
        return newVal;
    }

    public static void rotateAxisX(Point3D p, boolean CW, double degrees){
        double radius = Math.sqrt((p.y * p.y) + (p.z * p.z));
        double alpha = Math.atan2(p.z,p.y);
        alpha +=  ((2*Math.PI*degrees) / 360) *(CW?-1:1);
        p.y = radius * Math.cos(alpha);
        p.z = radius * Math.sin(alpha);
    }

    public static void rotateAxisY(Point3D p, boolean CW, double degrees){
        double radius = Math.sqrt((p.x * p.x) + (p.z * p.z));
        double alpha = Math.atan2(p.x,p.z);
        alpha +=  ((2*Math.PI*degrees) / 360) *(CW?-1:1);
        p.z = radius * Math.cos(alpha);
        p.x = radius * Math.sin(alpha);
    }

    public static void rotateAxisZ(Point3D p, boolean CW, double degrees){
        double radius = Math.sqrt((p.y * p.y) + (p.x * p.x));
        double alpha = Math.atan2(p.y,p.x);
        alpha +=  ((2*Math.PI*degrees) / 360) *(CW?-1:1);
        p.x = radius * Math.cos(alpha);
        p.y = radius * Math.sin(alpha);

    }
}
