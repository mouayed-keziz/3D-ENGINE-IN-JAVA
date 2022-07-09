package Renderer.entity;

import Renderer.entity.builder.BasicEntityBuilder;
import Renderer.input.ClickType;
import Renderer.input.Mouse;
import Renderer.point.PointConverter;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    List<IEntity> entities;

    public EntityManager() {
        this.entities = new ArrayList<IEntity>();
    }

    public void init() {
        this.entities.add(BasicEntityBuilder.createDiamond(new Color(200,40,150),100 , 0 ,0 ,0));
    }

    int initialX = 0, initialY = 0;
    final double mouseSensitivity = 2.5;
    public void update(Mouse mouse) {
        int x = mouse.getX();
        int y = mouse.getY();
        if (mouse.getButton() == ClickType.leftClick) {
            double xDif = x - initialX;
            double yDif = y - initialY;

            this.rotate(true,0,yDif/mouseSensitivity,xDif/mouseSensitivity);
        }
        else if(mouse.getButton() == ClickType.RightClick) {
            double xDif = x - initialX;

            this.rotate(true,xDif/mouseSensitivity,0,0);
        }
        if(mouse.isScrollingUp()) {
            PointConverter.ZoomIn();
        }
        else if(mouse.isScrollingDown()) {
            if (PointConverter.scale >= 0.05)
                PointConverter.ZoomOut();
        }
        mouse.resetScroll();
        initialX = x;
        initialY = y;
    }

    public void render(Graphics g) {
        for(IEntity entity : this.entities) {
            entity.render(g);
        }
    }

    private void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {
        for(IEntity entity : this.entities) {
            entity.rotate(CW,xDegrees,yDegrees,zDegrees);
        }
    }
}
