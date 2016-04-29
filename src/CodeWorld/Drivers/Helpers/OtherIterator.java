package CodeWorld.Drivers.Helpers;

import java.util.Iterator;

public class OtherIterator<T> implements Iterator<T> {
    private boolean hasNext;
    private T object;

    public OtherIterator(T t) {
        object = t;
    }

    @Override
    public boolean hasNext() {
        return !hasNext;
    }

    @Override
    public T next() {
        hasNext = true;
        return object;
    }

    @Override
    public void remove() { //Tom said to not use this operation.
        throw new UnsupportedOperationException();
    }
}
