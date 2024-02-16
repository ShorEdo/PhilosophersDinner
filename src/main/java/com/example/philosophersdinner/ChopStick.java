package com.example.philosophersdinner;
/**
 * Class ChopStick represents the chopstick the philosopher needs to acquire, so he can eat the food.
 * this class works like a monitor for the shared object.
 */
import javafx.scene.image.ImageView;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {
    private final ImageView name;
    private boolean occupied = false;
    private final Lock chopStickLock = new ReentrantLock();

    /**
     * Constructor for the chopstick object, assigns the image id of the chopstick to the object.
     * @param name the chopstick image id.
     */
    public ChopStick(ImageView name) {
        this.name = name;
    }
    public ImageView getName() {
        return this.name;
    }

    /**
     * Method that resembles tryLock from class Lock, the philosopher(thread) will only try to acquire the chopstick,
     * and he will not wait for the chopstick.
     * @return true if chopstick object is not occupied, false if the chopstick object is occupied.
     */
    public synchronized boolean tryToTakeStick() {
        if (occupied) {
            return false;
        } else {
            occupied = true;
            return true;
        }
    }

    /**
     * The philosopher(thread) will try to acquire the chopstick if he fails he will wait until the philosopher that
     * holds the same chopstick releases it and notify him.
     * @return true if the chopstick object isn't occupied  by another philosopher and the philosopher acquired it.
     * false if it is occupied by another philosopher and didn't get to acquire the chopstick.
     * @throws InterruptedException wait has been interrupted.
     */
    public synchronized boolean waitForStick() throws InterruptedException {
        if (occupied) {
            wait();
            return false;
        } else {
            occupied = true;
            return true;
        }
    }

    /**
     * The philosopher releases the chopstick that is in his occupation and notify's and other philosopher that is
     * waiting for the chopstick.
     * @return true if the chopstick is occupied, false if it's not.
     */
    public synchronized boolean releaseStick() {
        if (occupied) {
            occupied = false;
            notify();
            return true;
        } else {
            return false;
        }
    }
}
