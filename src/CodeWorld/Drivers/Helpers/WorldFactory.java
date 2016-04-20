package CodeWorld.Drivers.Helpers;

import CodeWorld.Objects.Body;

public interface WorldFactory {
    public Body getWorld();               // Retrieve the World w/o rebuilding
    public WorldFactory build() throws CWSException;     // Build the World
}