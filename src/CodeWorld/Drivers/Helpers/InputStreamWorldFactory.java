package CodeWorld.Drivers.Helpers;
/**
 * Created by Master Lee on 4/19/2016.
 */

import CodeWorld.Objects.Body;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * TODO: FIX THIS CODE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
public class InputStreamWorldFactory implements WorldFactory {
    private FileInputStream fileInputStream;
    private InputStream inputStream;
    public InputStreamWorldFactory(Object p0) {
        if(p0 instanceof FileInputStream)
            fileInputStream = (FileInputStream) p0;
        if(p0 instanceof InputStream)
            inputStream = (InputStream) p0;
    }

    @Override
    public Body getWorld() {
        return null;
    }

    @Override
    public WorldFactory build() throws CWSException {
        return this;
    }
}
/**
 * TODO: FIX THIS CODE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */