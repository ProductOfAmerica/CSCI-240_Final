package CodeWorlds;

public interface WorldFactory {
    public Body getWorld();               // Retrieve the World w/o rebuilding
    public WorldFactory build() throws CWSException;     // Build the World
}