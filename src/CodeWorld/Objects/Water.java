package CodeWorld.Objects;

import CodeWorld.Drivers.Brick;
import CodeWorld.Objects.Shapes.Rectangle;
import CodeWorld.Objects.Shapes.Vector;

import java.awt.Color;
import java.awt.Image;
import java.util.Iterator;

public class Water extends Brick {
    private static int imgSize;
    private static Image img;

    private Vector loc;

    public Water(Vector loc) {this.loc = loc;}

    @Override
    public Vector getLoc()  {return loc;}

    @Override
    public String toString() {return "CodeWorld.Objects.Water";}

    @Override
    public Image getImage(int size) {
        if (size != imgSize)
            img = makeStippleImage(size, new Color(64, 64, 255),
                    new Color(128, 128, 255), 13, 11);

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
        return null;
    }

    @Override
    public Iterator<Brick> iterator() {
        return null;
    }
    /**
     * TODO: FIX THIS CODE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
}