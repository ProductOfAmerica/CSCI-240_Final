package CodeWorld.Drivers;

import CodeWorld.Animals.Cow;
import CodeWorld.Animals.Sloth;
import CodeWorld.Animals.Unicorn;
import CodeWorld.Drivers.Helpers.CWSException;
import CodeWorld.Drivers.Helpers.InputStreamWorldFactory;
import CodeWorld.Drivers.Helpers.Logger;
import CodeWorld.Drivers.Helpers.WorldFactory;
import CodeWorld.Graphics.Display;
import CodeWorld.Graphics.DumpDisplay;
import CodeWorld.Objects.Body;
import CodeWorld.Objects.Ore;
import CodeWorld.Objects.Shapes.Brick;
import CodeWorld.Objects.Shapes.Rectangle;
import CodeWorld.Objects.Shapes.Vector;
import CodeWorld.Objects.Tree;

import java.io.FileInputStream;
import java.io.IOException;
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



            GraphicsFrame frame = new GraphicsFrame(100, 100);
            GraphicsFrame.SampleDsp dsps[] = new GraphicsFrame.SampleDsp[10];
            int i;

            frame.getPnl().addDisplayable(new Tree(new Vector(5, 8)));
            frame.getPnl().addDisplayable(new Ore(new Vector(6, 8)));
            frame.getPnl().addDisplayable(new Cow(new Vector(7, 8)));
            frame.getPnl().addDisplayable(new Sloth(new Vector(8, 8)));
            frame.getPnl().addDisplayable(new Unicorn(new Vector(9, 8)));

            for (i = 0; i < 10; i++) {
                dsps[i] = new GraphicsFrame.SampleDsp(new Vector(5 + i*10, 5 + i*10 + i%2),
                        new Vector(i%2*2 - 1, 1 - i%2*2));
                frame.getPnl().addDisplayable(dsps[i]);
            }



            fact = new InputStreamWorldFactory(args.length == 2 ? new FileInputStream(args[1]) : System.in);
            fact.build();
            world = fact.getWorld();

            bounds = world.getBounds();
            System.out.printf("Bounds %s\n", bounds);

            dsp = args[0].equals("G") ?
                    new GraphicsFrame(bounds.getRight(), bounds.getBottom()).getPnl() : new DumpDisplay();

            for (Brick brk : world)
                dsp.addDisplayable(brk);

            dsp.redraw(0);
        } catch (IOException | CWSException err) {
            Logger.getLogger().log("Error: %s\n", err.getMessage());
        }
    }
}