package CodeWorld.Drivers.Helpers;

import java.util.Iterator;

public class SingleIterator<T> implements Iterator<T> {
  private T item;
  private boolean done;

  public SingleIterator(T t) {
    this.item = t;
  }

  @Override
  public boolean hasNext() {
    return !this.done;
  }

  @Override
  public T next() {
    this.done = true;return this.item;
  }

  @Override
  public void remove() { //Tom said to not use this operation.
    throw new UnsupportedOperationException();
  }
}
