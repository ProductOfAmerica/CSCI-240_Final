package CodeWorld.Animals;

import CodeWorld.Drivers.Helpers.OtherIterator;
import CodeWorld.Objects.Shapes.Brick;
import CodeWorld.Objects.Body;
import CodeWorld.Objects.Shapes.Rectangle;
import CodeWorld.Objects.Shapes.Vector;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Sloth extends Animal {
    private static int imgSize;
    private static BufferedImage img;

    public Sloth(Vector loc) {super(loc, new Vector(0,0));}

    @Override
    public String getLabel() {return "CodeWorld.Animals.Sloth";}

    @Override
    public void step() {
        step();

        vlc.scaleBy(.5);  // Sloths just move more and more slowly
    }

    @Override
    public Image getImage(int size) {
        Graphics2D g;

        if (size != imgSize) {
            img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            g = img.createGraphics();
            g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

            g.setColor(new Color(60, 41, 34));
            g.fillOval((int) (size/2.4), size/4, (int) (size/2.1), (int) (size/2.1));

            g.setColor(new Color(163, 114, 92));
            g.fillOval(size/2, size/20, (int) (size/3.5), (int) (size/3.5));

            g.setColor(Color.black);
            g.fillOval((int) (size/1.8), size/8, (int) (size/18), (int) (size/18));
            g.fillOval((int) (size/1.46), size/8, (int) (size/18), (int) (size/18));
            g.fillOval((int) (size/1.63), (int) (size/4.5), (int) (size/18), (int) (size/18));

            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(size/20f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10.0f, new float[]{1}, 0.0f));
            g.drawLine(size/2, (int) (size/1.6), size/3, (int) (size/1.4));
            g.drawLine((int) (size/1.3), (int) (size/1.6), (int) (size/1.1), (int) (size/1.4));

            g.dispose();
        }

        return img;
    }


    @Override
    public Rectangle getBounds() {
        return new Rectangle(loc, 1, 1);
    }

    @Override
    public Body clone(Vector offset) {
        return new Sloth(getLoc().plus(offset));
    }

    @Override
    public Iterator<Brick> iterator() {
        return new OtherIterator<>(this);
    }
}