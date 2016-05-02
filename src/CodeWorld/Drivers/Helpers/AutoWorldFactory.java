package CodeWorld.Drivers.Helpers;

import CodeWorld.Animals.*;
import CodeWorld.Objects.*;
import CodeWorld.Objects.Shapes.CompositeBody;
import CodeWorld.Objects.Shapes.Vector;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tim on 5/2/2016.
 */
public class AutoWorldFactory implements WorldFactory {
    private ArrayList<Body> bodyList = new ArrayList<>();
    private Body world;

    public AutoWorldFactory() {
        bodyList.add(new Cow(new Vector()));
        bodyList.add(new Ore(new Vector()));
        bodyList.add(new Horse(new Vector()));
        bodyList.add(new Sloth(new Vector()));
        bodyList.add(new Tree(new Vector()));
        bodyList.add(new Stone(new Vector()));
        bodyList.add(new Water(new Vector()));
        bodyList.add(new Unicorn(new Vector()));
    }

    @Override
    public Body getWorld() {
        return world;
    }

    @Override
    public WorldFactory build() throws CWSException {
        CompositeBody world = new CompositeBody();
        int failcount = 0;
        while (failcount < 10) {
            Body randomBody = bodyList.get( new Random().nextInt(8));
            if (!world.add(randomBody.clone(new Vector(new Random().nextInt(50), new Random().nextInt(50)))))
                failcount++;
        }
        this.world = world;
        return this;
    }
}
