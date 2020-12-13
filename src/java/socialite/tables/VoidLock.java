package socialite.tables;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class VoidLock implements ReadWriteLock {

    static class EmptyLock implements Lock {
        @Override
        public void lock() { }
        @Override
        public void lockInterruptibly() throws InterruptedException {
        }
        @Override
        public boolean tryLock() {
            return true;
        }
        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return true;
        }
        @Override
        public void unlock() {
        }

        @NotNull
        @Override
        public Condition newCondition() {
            throw new UnsupportedOperationException();
        }
    }
    EmptyLock lock = new EmptyLock();

    @NotNull
    @Override
    public Lock readLock() {
        return lock;
    }

    @NotNull
    @Override
    public Lock writeLock() {
        return lock;
    }
}
