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
import java.util.Scanner;

public class CodeWorlds {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            try {
                if (args.length < 1 || args.length > 2 || !args[0].equals("D") && !args[0].equals("G"))
                    throw new CWSException("Usage: CodeWorlds (D|G) [entityFile]");

                Body world = new InputStreamWorldFactory(args.length == 2 ? new FileInputStream(args[1]) : System.in).build().getWorld();

                Rectangle bounds = world.getBounds();
                System.out.printf("Bounds %s\n", bounds);

                Display dsp = args[0].equals("G") ? new GraphicsFrame(bounds.getRight(), bounds.getBottom()).getPnl() : new DumpDisplay();

                for (Brick brk : world) {
                    dsp.addDisplayable(brk);
                }
            } catch (IOException | CWSException err) {
                Logger.getLogger().log("Error: %s\n", err.getMessage());
                in.close();
            }
        }
    }
}