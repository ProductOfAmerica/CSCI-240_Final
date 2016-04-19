package CodeWorlds;

public interface Body extends Iterable<Brick> {
    Rectangle getBounds();
    Body clone(Vector offset);  // Make a copy, with locations offset by |offset|
}