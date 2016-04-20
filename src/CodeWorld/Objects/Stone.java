package CodeWorld.Objects;

import CodeWorld.Objects.Shapes.Brick;
import CodeWorld.Objects.Shapes.Rectangle;
import CodeWorld.Objects.Shapes.Vector;

import java.awt.Color;
import java.awt.Image;
import java.util.Iterator;

public class Stone extends Brick {
    private static int imgSize;
    private static Image img;

    private Vector loc;

    public Stone(Vector loc) {this.loc = loc;}

    @Override
    public Vector getLoc()  {return loc;}

    @Override
    public String toString() {return "Stone";}

    @Override
    public Image getImage(int size) {
        if (size != imgSize)
            img = makeStippleImage(size, new Color(128, 110, 90), new Color(64, 55, 45), 13, 11);

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
        return new Stone(loc.plus(offset));
    }

    @Override
    public Iterator<Brick> iterator() {
        return null;
    }
    /**
     * TODO: FIX THIS CODE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
}