package Renderer.entity;

import Renderer.shapes.Tetrahedron;

import java.awt.*;
import java.util.List;

public class Entity implements IEntity{

    private List<Tetrahedron> tetrahedrons;

    public Entity(List<Tetrahedron> tetrahedrons) {
        this.tetrahedrons = tetrahedrons;
    }

    @Override
    public void render(Graphics g) {
        for(Tetrahedron tetra: this.tetrahedrons) {
            tetra.render(g);
        }
    }

    @Override
    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {
        for(Tetrahedron tetra: this.tetrahedrons){
            tetra.rotate(CW,xDegrees,yDegrees,zDegrees);
        }
    }
}
