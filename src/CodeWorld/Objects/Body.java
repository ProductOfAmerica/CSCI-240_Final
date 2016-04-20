package CodeWorld.Objects;

import CodeWorld.Drivers.Brick;
import CodeWorld.Objects.Shapes.Rectangle;
import CodeWorld.Objects.Shapes.Vector;

public interface Body extends Iterable<Brick> {
    Rectangle getBounds();
    Body clone(Vector offset);  // Make a copy, with locations offset by |offset|
}