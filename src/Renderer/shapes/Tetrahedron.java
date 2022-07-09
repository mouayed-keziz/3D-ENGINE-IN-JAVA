package Renderer.shapes;

import Renderer.point.Point3D;

import java.awt.*;


public class Tetrahedron {
    public myPolygon[] polygons;
    public Color color;
    public boolean visible;

    public Tetrahedron(Color color, myPolygon...polygons) {
        this.color = color;
        this.polygons = polygons;
        setPolygonColor();
        this.setVisible(true);
    }

    public Tetrahedron(myPolygon...polygons) {
        this.color = Color.white;
        this.polygons = polygons;
        this.setVisible(true);
    }

    public void render(Graphics g) {
        if (this.visible){
            for (myPolygon poly : this.polygons) {
                poly.render(g);
            }
            this.sortPolygons();
        }
    }

    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {
        for (myPolygon poly : this.polygons) {
            poly.rotate(CW,xDegrees,yDegrees,zDegrees);
        }
        this.sortPolygons();
    }

    private void sortPolygons() { myPolygon.sortPolygons(this.polygons); }


    public void setPolygonColor() {
        for (myPolygon poly : this.polygons) {
            poly.setColor(this.color);
        }
    }

    public void setRandomPolygonLightning() {
        for (myPolygon poly : this.polygons) {
            poly.setColor(this.color);
        }
    }

    public void setVisible(boolean visibility) {
        this.visible = visibility;
    }

}


