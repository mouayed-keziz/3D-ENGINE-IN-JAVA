package Renderer;
import Renderer.entity.EntityManager;
import Renderer.input.ClickType;
import Renderer.input.Mouse;
import Renderer.point.Point3D;
import Renderer.point.PointConverter;
import Renderer.shapes.Cuboid;
import Renderer.shapes.Tetrahedron;
import Renderer.shapes.myPolygon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;

public class Display extends Canvas implements Runnable  {
    @Serial
    private static final long serialVersionUID = 1L;

    public static class Camera {
        public static double x = 15;
    }

    private Thread thread;
    public JFrame frame;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    int fps = 0;
    private static final String title = "3D renderer";
    public static boolean running = false;

    private EntityManager entityManager;

    private Mouse mouse;

    public Display() {
        this.frame = new JFrame();
        Dimension size = new Dimension(WIDTH,HEIGHT);
        this.setPreferredSize(size);

        this.entityManager = new EntityManager();

        this.mouse = new Mouse();
        this.addMouseListener(this.mouse);
        this.addMouseMotionListener(this.mouse);
        this.addMouseWheelListener(this.mouse);
    }

    public static void main(String[] args) {
        Display display = new Display();
        display.frame.setTitle(title);
        display.frame.add(display);
        display.frame.pack();
        display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.frame.setResizable(false);
        display.frame.setLocationRelativeTo(null);
        display.frame.setVisible(true);
        display.start();
    }

    @Override
    public void run() {
        //game loop
        long lastTime = System.nanoTime();
        double maxFps = 60;
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / maxFps;
        double delta = 0;
        int FramesPerSec = 0;

        this.entityManager.init();
        while(running) {
            long now = System.nanoTime();
            delta  = delta + (now - lastTime) / ns;//ooo
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
                render();
                FramesPerSec++;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                fps = FramesPerSec;
                this.frame.setTitle(title + " | " + FramesPerSec + " fps");
                FramesPerSec = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            System.out.println("buffer strategy  is null so i will instantiate it");
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.white);
        g.fillRect(0,0,WIDTH,HEIGHT);

        this.entityManager.render(g);

        g.setColor(Color.black);
        g.setFont(new Font("Cascadia Code",Font.PLAIN,20));
        g.drawString("fps : "+ fps,20,20);

        g.dispose();
        bs.show();
    }

    public synchronized void start() {
        running = true;
        this.thread = new Thread(this,"Display");
        this.thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        this.entityManager.update(this.mouse);
    }


}
