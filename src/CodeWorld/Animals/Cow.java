package CodeWorld.Animals;

import CodeWorld.Drivers.Helpers.OtherIterator;
import CodeWorld.Objects.Shapes.Brick;
import CodeWorld.Objects.Body;
import CodeWorld.Objects.Shapes.Rectangle;
import CodeWorld.Objects.Shapes.Vector;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Cow extends HerdAnimal {
    private static int imgSize;
    private static BufferedImage img;

    public Cow(Vector loc) {super(loc, new Vector());}

    @Override
    public String getLabel() {return "CodeWorld.Animals.Cow";}  // C for CodeWorld.Animals.Cow

    @Override
    boolean isGoodLeader(HerdAnimal ldr) {
        return ldr instanceof Cow && ldr.loc.equals(loc);
    }

    @Override
    public Image getImage(int size) {
        Graphics2D g;
        Color bodyClr = new Color(210, 210, 210), hoofClr = new Color(80, 60, 40), spotClr = Color.BLACK;

        if (size != imgSize) {
            img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            g = img.createGraphics();
            g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

            // CodeWorld.Objects.Body and head
            g.setColor(bodyClr);
            g.fillRoundRect(3*size/10, 3*size/10, 6*size/10, 4*size/10, size/10,
                    size/10);
            g.fillRoundRect(size/10,  2*size/10,  3*size/10,  2*size/10,
                    size/10, size/10);

            // Legs
            g.fillPolygon(
                    new int[] {size*4/10, size*5/10, size*4/10, size*5/20},
                    new int[] {size/2, size/2, size*17/20, size*17/20}, 4);
            g.fillPolygon(
                    new int[] {size*7/10, size*8/10, size*9/10, size*15/20},
                    new int[] {size/2, size/2, size*17/20, size*17/20}, 4);

            // Hooves
            g.setColor(hoofClr);
            g.fillPolygon(
                    new int[] {size*5/20, size*4/10, size*9/20, size*2/10},
                    new int[] {size*17/20, size*17/20, size*19/20, size*19/20}, 4);
            g.fillPolygon(
                    new int[] {size*15/20, size*9/10, size*19/20, size*7/10},
                    new int[] {size*17/20, size*17/20, size*19/20, size*19/20}, 4);

            // Spots and eye
            g.setColor(spotClr);
            g.fillOval(4*size/10,  4*size/10,  size/10,  size/10);
            g.fillOval(6*size/10,  5*size/10,  size/5,  size/8);
            g.fillOval(3*size/10, 5*size/20, size/40, size/40);

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
        return new Cow(getLoc().plus(offset));
    }

    @Override
    public Iterator<Brick> iterator() {
        return new OtherIterator<>(this);
    }
    /**
     * TODO: FIX THIS CODE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
}
