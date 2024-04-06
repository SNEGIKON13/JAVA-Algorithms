import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Stack;

public class ConcurrentStackIterator<T> implements Iterator<T> {
    private final Stack<T> stack;
    private final int counter;
    public ConcurrentStackIterator(Stack <T> stack) {
    this.stack = stack;
    this.counter = stack.size();
    }

    @Override
    public boolean hasNext() {

        return stack.iterator().hasNext();
    }

    @Override
    public T next() {
        checkModification();
        return stack.iterator().next();
    }

    private void checkModification() {
        if (counter != stack.size()) {
            throw new ConcurrentModificationException();
        }
    }
}