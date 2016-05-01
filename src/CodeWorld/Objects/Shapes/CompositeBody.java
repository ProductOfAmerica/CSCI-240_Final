package CodeWorld.Objects.Shapes;

import CodeWorld.Objects.Body;

import java.util.Iterator;

/**
 * Created by Calculus on 5/1/2016.
 */
public class CompositeBody implements Body {
	 private List<Body> children = new LinkedList<Body>();
	
    @Override
    public Rectangle getBounds() {
		Rectangle bounds = null;
        bounds = null;
        for (Body child : this.children) {
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
        return null;
    }

    @Override
    public Iterator<Brick> iterator() {
        return null;
    }
}
