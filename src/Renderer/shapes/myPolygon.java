package Renderer.shapes;

import Renderer.point.Point3D;
import Renderer.point.PointConverter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class myPolygon {

    public Point3D[] points;
    public Color color;

    public myPolygon(Color color,Point3D... P) {
        this.color = color;
        this.points = new Point3D[P.length];
        for (int i = 0; i < P.length; i++) {
            this.points[i] = new Point3D(P[i].x,P[i].y,P[i].z);
        }
    }

    public myPolygon(Point3D... P) {
        this.color = Color.WHITE;
        this.points = new Point3D[P.length];
        for (int i = 0; i < P.length; i++) {
            this.points[i] = new Point3D(P[i].x,P[i].y,P[i].z);
        }
    }
    
    public void render(Graphics g) {
        Polygon poly = new Polygon();
        for (int i = 0; i < this.points.length; i++) {
            Point p = PointConverter.convertPoint(this.points[i]);
            poly.addPoint(p.x,p.y);
        }
        g.setColor(this.color);
        g.fillPolygon(poly);
        //g.setColor(Color.white);
        //g.drawPolygon(poly);
    }

    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees){
        for(Point3D p : points) {
            PointConverter.rotateAxisX(p,CW,xDegrees);
            PointConverter.rotateAxisY(p,CW,yDegrees);
            PointConverter.rotateAxisZ(p,CW,zDegrees);
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getAverageX() {
        double sum = 0;
        for (Point3D p : this.points) {
            sum = sum + p.x;
        }
        return sum / this.points.length;
    }

    public static myPolygon[] sortPolygons(myPolygon[] polygons) {
        List<myPolygon> PolygonList = new ArrayList<myPolygon>();
        for (myPolygon poly : polygons) {
            PolygonList.add(poly);
        }

        Collections.sort(PolygonList, new Comparator<myPolygon>() {
            @Override
            public int compare(myPolygon p1, myPolygon p2) {
                return p1.getAverageX() - p2.getAverageX() < 0 ? 1 : -1;
            }
        });
        for (int i = 0; i < polygons.length; i++) {
            polygons[i] = PolygonList.get(i);
        }
        return polygons;
    }
}
