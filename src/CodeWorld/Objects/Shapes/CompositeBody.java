package CodeWorld.Objects.Shapes;

import CodeWorld.Objects.Body;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Calculus on 5/1/2016.
 */
public class CompositeBody implements Body {
    private List<Body> children = new LinkedList<Body>();

    @Override
    public Rectangle getBounds() {
        Rectangle bounds = null;
        for (Body child : children) {
            if (bounds == null) {
                bounds = new Rectangle(child.getBounds());
                continue;
            }
            bounds.unionBy(child.getBounds());
        }
        return bounds;
    }

    @Override
    public Body clone(Vector offset) {
        return null; //No need
    }

    @Override
    public Iterator<Brick> iterator() {
        return null;
    }
}
