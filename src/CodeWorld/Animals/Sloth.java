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

            g.setColor(Color.DARK_GRAY);
            g.fillOval(3*size/10, 3*size/10, size/5, size/5);

            g.fillOval(3*size/10, 3*size/10, size/10, size/10);



            g.dispose();
        }

        return img;
    }

    /**
     * TODO: FIX THIS CODE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
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
    /**
     * TODO: FIX THIS CODE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
}