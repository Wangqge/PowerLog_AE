package socialite.async.atomic;

import java.util.concurrent.atomic.AtomicLong;

public class MyAtomicLong extends AtomicLong implements Cloneable{
    /**
     * Creates a new AtomicLong with the given initial value.
     *
     * @param initialValue the initial value
     */
    public MyAtomicLong(long initialValue) {
        super(initialValue);
    }

    /**
     * Creates a new AtomicLong with initial value {@code 0}.
     */
    public MyAtomicLong() {
        super();
    }
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
