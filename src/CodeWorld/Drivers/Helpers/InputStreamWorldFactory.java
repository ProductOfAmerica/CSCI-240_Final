package CodeWorld.Drivers.Helpers;
/**
 * Created by Master Lee on 4/19/2016.
 */

import CodeWorld.Animals.Cow;
import CodeWorld.Animals.Horse;
import CodeWorld.Animals.Sloth;
import CodeWorld.Animals.Unicorn;
import CodeWorld.Objects.*;
import CodeWorld.Objects.Shapes.CompositeBody;
import CodeWorld.Objects.Shapes.Vector;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class InputStreamWorldFactory implements WorldFactory {
    private Map<String, Body> bodyMap = new TreeMap<>();
    private Scanner in;
    private Body world;

    public InputStreamWorldFactory(Object p0) {
        in = new Scanner((InputStream) p0);

        bodyMap.put("Cow", new Cow(new Vector()));
        bodyMap.put("Ore", new Ore(new Vector()));
        bodyMap.put("Horse", new Horse(new Vector()));
        bodyMap.put("Sloth", new Sloth(new Vector()));
        bodyMap.put("Tree", new Tree(new Vector()));
        bodyMap.put("Stone", new Stone(new Vector()));
        bodyMap.put("Water", new Water(new Vector()));
        bodyMap.put("Unicorn", new Unicorn(new Vector()));
    }

    @Override
    public Body getWorld() {
        return world;
    }

    @Override
    public WorldFactory build() throws CWSException {
        while (in.hasNext("\\(")){
            in.next();
            bodyMap.put(in.next().trim(), stringToBody());
            if(in.hasNext() && in.next().contains(")"))
                continue;
            throw new CWSException("Missing parenthesis");
        }

        world = stringToBody();
        return this;
    }

    private Body stringToBody() throws CWSException {
        CompositeBody body = new CompositeBody();
        while (in.hasNext() && !in.hasNext("\\)")){
            String tempName = in.next().trim(); //This will be the object type
            Vector tempVector = new Vector(in.nextInt(), in.nextInt());
            if(bodyMap.containsKey(tempName)){ //Validate that the name exists in the bodyMap
                if(body.add(bodyMap.get(tempName).clone(tempVector))) //Add it, if one does
                    continue;
                throw new CWSException(String.format("Exception: Body's (%s) location (%s) already exists.",
                        tempName, tempVector.toString())); //Else, the classes already equal, or locations equal
            }
        }
        return body;
    }
}