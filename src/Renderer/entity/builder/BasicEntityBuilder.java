package Renderer.entity.builder;

import Renderer.entity.Entity;
import Renderer.entity.IEntity;
import Renderer.point.Point3D;
import Renderer.shapes.Tetrahedron;
import Renderer.shapes.myPolygon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BasicEntityBuilder {
    public static IEntity createCube(double size ,double centerX ,double centerY ,double centerZ) {

        Point3D p1 = new Point3D(centerX + size/2,centerY + -size/2,centerZ + size/2);
        Point3D p2 = new Point3D(centerX + size/2,centerY + size/2,centerZ + size/2);
        Point3D p3 = new Point3D(centerX + size/2,centerY + size/2,centerZ + -size/2);
        Point3D p4 = new Point3D(centerX + size/2,centerY + -size/2,centerZ + -size/2);
        Point3D p5 = new Point3D(centerX + -size/2,centerY + -size/2,centerZ + size/2);
        Point3D p6 = new Point3D(centerX + -size/2,centerY + size/2,centerZ + size/2);
        Point3D p7 = new Point3D(centerX + -size/2,centerY + size/2,centerZ + -size/2);
        Point3D p8 = new Point3D(centerX + -size/2,centerY + -size/2,centerZ + -size/2);

        Tetrahedron tetra = new Tetrahedron(
                new myPolygon(Color.red,p1,p2,p3,p4),
                new myPolygon(Color.blue,p5,p6,p7,p8),
                new myPolygon(Color.pink,p1,p5,p6,p2),
                new myPolygon(Color.yellow,p4,p8,p7,p3),
                new myPolygon(Color.white,p1,p5,p8,p4),
                new myPolygon(Color.green,p2,p6,p7,p3)
        );

        List<Tetrahedron> tetras = new ArrayList<Tetrahedron>();
        tetras.add(tetra);
        return new Entity(tetras);
    }

    public static IEntity createDiamond(Color color ,double size, double centerX, double centerY, double centerZ) {
        List<Tetrahedron> tetras = new ArrayList<Tetrahedron>();

        int edges = 10;
        double inFactor = 0.8;
        Point3D bottom = new Point3D(centerX, centerY, centerZ- size/2);
        Point3D[] outPoints = new Point3D[edges];
        Point3D[] innerPoints = new Point3D[edges];
        for (int i = 0; i < edges; i++) {
            double theta = 2 * Math.PI / edges * i;
            double xPos = -Math.sin(theta) * size/2;
            double yPos = Math.cos(theta) * size/2;
            double zPos = size/2;
            outPoints[i] = new Point3D(centerX + xPos ,centerY + yPos ,centerZ + zPos);
            innerPoints[i] = new Point3D(centerX + xPos * inFactor ,centerY + yPos * inFactor ,centerZ + zPos / inFactor);
        }

        myPolygon polygons[] = new myPolygon[2*edges + 1];
        for (int i = 0; i < edges; i++) {
            polygons[i] = new myPolygon(outPoints[i],bottom,outPoints[(i+1)%edges]);
        }
        for (int i = 0; i < edges; i++) {
            polygons[i+edges] = new myPolygon(outPoints[i],outPoints[(i+1)%edges],innerPoints[(i+1)%edges],innerPoints[i]);
        }
        polygons[2*edges] = new myPolygon(innerPoints);

        Tetrahedron tetra = new Tetrahedron(color,polygons);
        tetras.add(tetra);

        return new Entity(tetras);
    }
}
