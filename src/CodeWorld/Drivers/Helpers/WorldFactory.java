package CodeWorld.Drivers.Helpers;

import CodeWorld.Objects.Body;

public interface WorldFactory {
    Body getWorld();               // Retrieve the World w/o rebuilding
    WorldFactory build() throws CWSException;     // Build the World
}