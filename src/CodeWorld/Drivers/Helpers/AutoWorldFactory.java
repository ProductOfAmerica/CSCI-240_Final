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

        CompositeBody lake = new CompositeBody();
        lake.add(new Water(new Vector(0,0)));
        lake.add(new Water(new Vector(0,1)));
        lake.add(new Water(new Vector(1,0)));
        lake.add(new Water(new Vector(1,1)));
        lake.add(new Water(new Vector(0,2)));
        lake.add(new Water(new Vector(1,2)));
        lake.add(new Water(new Vector(2,0)));
        lake.add(new Water(new Vector(2,1)));
        bodyList.add(lake);

        CompositeBody boulder = new CompositeBody();
        boulder.add(new Stone(new Vector(0,0)));
        boulder.add(new Stone(new Vector(1,0)));
        boulder.add(new Stone(new Vector(1,1)));
        boulder.add(new Stone(new Vector(0,1)));
        bodyList.add(boulder);


        CompositeBody stand = new CompositeBody();
        stand.add(new Tree(new Vector(0, 0)));
        stand.add(new Tree(new Vector(1, 2)));
        stand.add(new Tree(new Vector(3, 3)));
        stand.add(new Tree(new Vector(1, 3)));
        bodyList.add(stand);

        CompositeBody forest = new CompositeBody();
        forest.add(stand.clone(new Vector(0, 0)));
        forest.add(stand.clone(new Vector(0, 4)));
        forest.add(stand.clone(new Vector(4, 0)));
        forest.add(stand.clone(new Vector(4, 4)));
        bodyList.add(forest);

        CompositeBody rockField = new CompositeBody();
        rockField.add(boulder.clone(new Vector(0, 0)));
        rockField.add(new Stone(new Vector(3, 2)));
        rockField.add(new Stone(new Vector(1, 3)));
        bodyList.add(rockField);

        CompositeBody mine = new CompositeBody();
        mine.add(rockField.clone(new Vector(0,0)));
        mine.add(new Ore(new Vector(2, 0)));
        mine.add(new Ore(new Vector(2, 1)));
        mine.add(new Ore(new Vector(1, 2)));
        bodyList.add(mine);

    }

    @Override
    public Body getWorld() {
        return world;
    }

    @Override
    public WorldFactory build() throws CWSException {
        CompositeBody world = new CompositeBody();
        int failcount = 0;
        while (failcount < 50) {
            Body randomBody = bodyList.get( new Random().nextInt(bodyList.size()));
            if (!world.add(randomBody.clone(new Vector(new Random().nextInt(50), new Random().nextInt(50)))))
                failcount++;
        }
        this.world = world;
        return this;
    }
}