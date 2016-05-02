package CodeWorld.Objects.Shapes;

import CodeWorld.Objects.Body;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Calculus on 5/1/2016.
 */
public class CompositeBody implements Body {
    private List<Body> children = new LinkedList<>();
    private Rectangle bounds;

    @Override
    public Rectangle getBounds() {
        bounds = null;
        for (Body body : children) {
            if (bounds == null) {
                bounds = new Rectangle(body.getBounds());
                continue;
            }
            bounds.unionBy(body.getBounds());
        }
        return bounds;
    }

    @Override
    public Body clone(Vector offset) {
        CompositeBody body = new CompositeBody();
        for(Body b : children){
            body.add(b.clone(offset));
        }
        return body; //No need
    }

    @Override
    public Iterator<Brick> iterator() {
        return new Brickerator();
    }

    public boolean add(Body body){
        for (Brick b : body) {
            for (Brick temp : this) {
                if (b.getClass() == temp.getClass() || !b.getLoc().equals(temp.getLoc()))
                    continue;
                return false;
            }
        }

        children.add(body);

        if (bounds == null) {
            bounds = body.getBounds();
        } else {
            bounds.unionBy(body.getBounds());
        }

        return true;
    }


    private class Brickerator implements Iterator<Brick>{
        Iterator<Brick> brickIterator;
        Iterator<Body> bodyIterator;

        Brickerator() {
            bodyIterator = CompositeBody.this.children.iterator();
        }

        @Override
        public boolean hasNext() {
            step();
            return (brickIterator != null) && brickIterator.hasNext();
        }

        @Override
        public Brick next() {
            step();
            return brickIterator.next();
        }

        @Override
        public void remove() {
            brickIterator.remove();
        }

        private void step(){
            while(bodyIterator.hasNext() && (brickIterator == null || !brickIterator.hasNext()))
                brickIterator = bodyIterator.next().iterator();
        }
    }
}