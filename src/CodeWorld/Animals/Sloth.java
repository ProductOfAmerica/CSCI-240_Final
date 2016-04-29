package CodeWorld.Animals;

import CodeWorld.Objects.Shapes.Brick;
import CodeWorld.Objects.Body;
import CodeWorld.Objects.Shapes.Rectangle;
import CodeWorld.Objects.Shapes.Vector;

import java.awt.*;
import java.awt.geom.GeneralPath;
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
        Graphics2D grp;

        if (size != imgSize) {
            img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            grp = img.createGraphics();
            grp.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

            grp.setColor(Color.DARK_GRAY);
            grp.fillOval(3*size/10, 3*size/10, size/5, size/5);

            grp.fillOval(3*size/10, 3*size/10, size/10, size/10);



            grp.dispose();
        }

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



    public static GeneralPath pathFrom(int[] xs, int[] ys) {
        GeneralPath path = new GeneralPath();
        path.moveTo(xs[0], ys[0]);
        for(int i = 1; i < xs.length; i++) {
            path.lineTo(xs[i], ys[i]);
        }     path.closePath();
        return path;
    }

    public static final GeneralPath polygon1 = pathFrom(
            new int[]{458,467,460,455},
            new int[]{169,177,180,174}
    );
    public static final GeneralPath polygon2 = pathFrom(
            new int[]{443,449,441,435},
            new int[]{168,172,179,175}
    );
    public static final GeneralPath polygon3 = pathFrom(
            new int[]{465,471,478,490,506,521,537,545,551,552,554,549,539,511,496,480,470},
            new int[]{181,192,200,204,210,210,210,210,198,189,177,166,161,162,165,170,173}
    );
}