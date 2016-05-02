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

        bodyMap.put("Cow", new Cow(new Vector())); //Store a new entity in the bodyMap
        bodyMap.put("Ore", new Ore(new Vector())); //Store a new entity in the bodyMap
        bodyMap.put("Horse", new Horse(new Vector())); //Store a new entity in the bodyMap
        bodyMap.put("Sloth", new Sloth(new Vector())); //Store a new entity in the bodyMap
        bodyMap.put("Tree", new Tree(new Vector())); //Store a new entity in the bodyMap
        bodyMap.put("Stone", new Stone(new Vector())); //Store a new entity in the bodyMap
        bodyMap.put("Water", new Water(new Vector())); //Store a new entity in the bodyMap
        bodyMap.put("Unicorn", new Unicorn(new Vector())); //Store a new entity in the bodyMap
    }

    @Override
    public Body getWorld() {
        return world;
    }

    @Override
    public WorldFactory build() throws CWSException {
        while (in.hasNext("\\(")){ //While there's more input block patterns in the file....
            in.next(); //Remove the parenthesis
            bodyMap.put(in.next().trim(), getInput()); //Get the name, and then get all of the method body code
            if(!(in.hasNext() && in.next().contains(")"))) //If there's another block, continue
                throw new CWSException("Missing parenthesis"); //Otherwise, there's a missing block parenthesis
        }

        world = getInput(); //Set the world equal to the Global Variables in the input file
        return this;
    }

    private Body getInput() throws CWSException {
        CompositeBody body = new CompositeBody(); //New pattern body is created
        while (in.hasNext() && !in.hasNext("\\)")){
            String tempName = in.next().trim(); //This will be the object type
            Vector tempVector = new Vector(in.nextInt(), in.nextInt());
            if((bodyMap.containsKey(tempName) && !body.add(bodyMap.get(tempName).clone(tempVector))))//Validate that the name exists in the bodyMap
                throw new CWSException(String.format("Exception: Body's (%s) location (%s) already exists.",
                        tempName, tempVector.toString())); //Else, the classes already equal, or locations equal
        }
        return body;
    }
}