package CodeWorld.Objects;

import CodeWorld.Objects.Shapes.Brick;
import CodeWorld.Objects.Shapes.Rectangle;
import CodeWorld.Objects.Shapes.Vector;

import java.awt.Color;
import java.awt.Image;
import java.util.Iterator;

public class Ore extends Brick {
    private static int imgSize;
    private static Image img;

    private Vector loc;

    public Ore(Vector loc) {this.loc = loc;}

    @Override
    public Vector getLoc()  {return loc;}

    @Override
    public String toString() {return "Ore";}

    @Override
    public Image getImage(int size) {
        if (size != imgSize)
            img = makeStippleImage(size, new Color(168, 80, 80), new Color(100, 50, 50), 11, 7);

        return img;
    }


    /**
     * TODO: FIX THIS CODE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public Body clone(Vector offset) {
        return new Ore(loc.plus(offset));
    }

    @Override
    public Iterator<Brick> iterator() {
        return null;
    }
    /**
     * TODO: FIX THIS CODE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
}