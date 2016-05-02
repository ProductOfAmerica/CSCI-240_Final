package CodeWorld.Drivers;

import CodeWorld.Animals.Animal;
import CodeWorld.Drivers.Helpers.CWSException;
import CodeWorld.Drivers.Helpers.InputStreamWorldFactory;
import CodeWorld.Drivers.Helpers.Logger;
import CodeWorld.Drivers.Helpers.WorldFactory;
import CodeWorld.Graphics.Display;
import CodeWorld.Graphics.DumpDisplay;
import CodeWorld.Objects.Body;
import CodeWorld.Objects.Shapes.Brick;
import CodeWorld.Objects.Shapes.Rectangle;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class CodeWorlds {
    public static void main(String[] args) {
        Display dsp;
        WorldFactory fact;
        Body world;
        Rectangle bounds;
        try (Scanner in = new Scanner(System.in)) {
            if (args.length < 1 || args.length > 2 || !args[0].equals("D") && !args[0].equals("G"))
                throw new CWSException("Usage: CodeWorld.Drivers.CodeWorld (D|G) [entityFile]");


            fact = new InputStreamWorldFactory(args.length == 2 ? new FileInputStream(args[1]) : in);
            fact.build();
            world = fact.getWorld();

            bounds = world.getBounds();
            System.out.printf("Bounds %s\n", bounds);

            dsp = args[0].equals("G") ? new GraphicsFrame(bounds.getRight(), bounds.getBottom()).getPnl() : new DumpDisplay();

            Animal.setRange(world.getBounds()); //Set the size of the world

            for (Brick brk : world)
                dsp.addDisplayable(brk);

            dsp.redraw(0);
        } catch (IOException | CWSException err) {
            System.err.println("Error: " + err.getMessage());
            Logger.getLogger().log("Error: %s\n", err.getMessage());
        }
    }
}