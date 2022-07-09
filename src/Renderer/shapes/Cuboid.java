package Renderer.shapes;

import Renderer.point.Point3D;

import java.awt.*;
//import java.awt.geom.Point2D;

public class Cuboid extends Tetrahedron{

    public double x;
    public double y;
    public double z;
    private Point3D center;

    public Cuboid(Color color, Point3D p, double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.center = p;
        Point3D p1 = new Point3D(p.x + x/2,p.y - y/2 ,p.z + z/2);
        Point3D p2 = new Point3D(p.x + x/2,p.y + y/2 ,p.z + z/2);
        Point3D p3 = new Point3D(p.x + x/2,p.y + y/2 ,p.z - z/2);
        Point3D p4 = new Point3D(p.x + x/2,p.y - y/2 ,p.z - z/2);
        Point3D p5 = new Point3D(p.x - x/2,p.y - y/2 ,p.z + z/2);
        Point3D p6 = new Point3D(p.x - x/2,p.y + y/2 ,p.z + z/2);
        Point3D p7 = new Point3D(p.x - x/2,p.y + y/2 ,p.z - z/2);
        Point3D p8 = new Point3D(p.x - x/2,p.y - y/2 ,p.z - z/2);

        /*Point3D p1 = new Point3D(p.x ,p.y + y,p.z);
        Point3D p2 = new Point3D(p.x ,p.y ,p.z);
        Point3D p3 = new Point3D(p.x ,p.y ,p.z + z);
        Point3D p4 = new Point3D(p.x ,p.y + y ,p.z + z);
        Point3D p5 = new Point3D(p.x + x,p.y + y ,p.z);
        Point3D p6 = new Point3D(p.x + x,p.y ,p.z );
        Point3D p7 = new Point3D(p.x + x,p.y ,p.z + z);
        Point3D p8 = new Point3D(p.x + x,p.y + y ,p.z + z);*/

        myPolygon[] polys = new myPolygon[6];
        polys[0] = new myPolygon(p1,p2,p3,p4);
        polys[1] = new myPolygon(p5,p6,p7,p8);
        polys[2] = new myPolygon(p1,p5,p6,p2);
        polys[3] = new myPolygon(p4,p8,p7,p3);
        polys[4] = new myPolygon(p3,p7,p6,p2);
        polys[5] = new myPolygon(p4,p8,p5,p1);

        super.polygons = polys;
        super.color = color;
        super.setPolygonColor();
        super.setVisible(true);
    }

    public void morPoints(int level) {
        for (int i = 0; i < level; i++) {
            for (myPolygon poly : super.polygons) {
                double xx= 0;
                double yy = 0;
                double zz = 0;
                for (Point3D p : poly.points) {
                    xx = x + p.x;
                    yy = y + p.y;
                    zz = z + p.z;
                }
                Point3D c = new Point3D(xx/poly.points.length,yy/poly.points.length,zz/poly.points.length);

                double tawila = Math.sqrt((c.x-center.x)*(c.x-center.x) + (c.y-center.y)*(c.y-center.y) + (c.z-center.z)*(c.z-center.z));
                Point3D vector = new Point3D((c.x-center.x)/tawila,(c.y-center.y)/tawila,(c.z-center.z)/tawila);
                int idhafa = 1;
                c.x = c.x + (vector.x)*idhafa;
                c.y = c.y + (vector.y)*idhafa;
                c.z = c.z + (vector.z)*idhafa;
                Point3D[] tempPoints = new Point3D[poly.points.length];
                for (int j = 0; j < tempPoints.length; j++) {
                    tempPoints[i] = poly.points[i];
                }
                poly.points = new Point3D[tempPoints.length + 1];
                for (int j = 0; j < tempPoints.length; j++) {
                    poly.points[i] = tempPoints[i];
                }
                poly.points[tempPoints.length] = c;
            }
        }
    }
}
