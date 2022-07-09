package Renderer.input;

import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {
    private int MouseX = -1;
    private int MouseY = -1;
    private int MouseB = -1;
    private int Scroll = 0;

    public int getX() {
        return MouseX;
    }

    public int getY() {
        return MouseY;
    }

    public boolean isScrollingUp() {
        return this.Scroll == -1;
    }

    public boolean isScrollingDown() {
        return this.Scroll == 1;
    }

    public void resetScroll() {
        this.Scroll = 0;
    }

    public ClickType getButton() {
        switch (this.MouseB) {
            case 1:
                return ClickType.leftClick;
            case 2:
                return ClickType.ScrollClick;
            case 3:
                return ClickType.RightClick;
            default:
                return ClickType.Unknown;
        }
    }

    public void resetButton() {
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        this.MouseX = event.getX();
        this.MouseY = event.getY();
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        this.MouseX = event.getX();
        this.MouseY = event.getY();
    }

    @Override
    public void mouseClicked(MouseEvent event) {
    }

    @Override
    public void mousePressed(MouseEvent event) {
        this.MouseB = event.getButton();

    }

    @Override
    public void mouseReleased(MouseEvent event) {
        this.MouseB = -1;
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {
        this.Scroll = event.getWheelRotation();
    }
}
