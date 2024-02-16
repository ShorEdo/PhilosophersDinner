package com.example.philosophersdinner;
/**
 * Class Philosopher has the run method for the threads, this class uses the ChopStick and PhilosophersDinnerController
 * classes to acquire a shared resource and update the gui app.
 */
import javafx.scene.image.ImageView;
import java.security.SecureRandom;

public class Philosopher extends Thread{
    private final ImageView name;
    private final ChopStick leftChopStick;
    private final ChopStick rightChopStick;
    private final PhilosophersDinnerController controller;
    private final SecureRandom random = new SecureRandom();
    private final int MAXIMUM_SLEEP_TIME = 7000;
    private final int MINIMUM_SLEEP_TIME = 4500;
    private boolean run = true;

    /**
     * Constructor of Philosopher, assigns image id of the philosopher, assigns the chopsticks objects and
     * the controller of the app.
     * @param name image id of philosopher.
     * @param left left chopstick object to try and acquire.
     * @param right right chopstick object to try and acquire.
     * @param c controller of the app.
     */
    public Philosopher(ImageView name, ChopStick left, ChopStick right, PhilosophersDinnerController c) {
        this.name = name;
        this.leftChopStick = left;
        this.rightChopStick = right;
        this.controller = c;
    }

    /**
     * philosopher(thread) acquires both chopsticks then starts to eat for some time after that he stops eating and
     * releases the chopsticks thinks for a while and starts all over again, stops when the method stopLoop is called.
     */
    @Override
    public void run() {
        while(isRun()) {
            try {
                takeChopSticks(this.leftChopStick, this.rightChopStick);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.controller.startEating(this.name, this.leftChopStick.getName(), this.rightChopStick.getName());
            try {
                sleep(random.nextInt(MINIMUM_SLEEP_TIME,MAXIMUM_SLEEP_TIME));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.controller.stopEating(this.name, this.leftChopStick.getName(), this.rightChopStick.getName());
            releaseChopSticks(this.leftChopStick, this.rightChopStick);
            try {
                sleep(random.nextInt(MINIMUM_SLEEP_TIME * 2,MAXIMUM_SLEEP_TIME * 2));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Method that releases the chopsticks that are occupied by the philosopher(thread).
     * @param leftChopStick chopstick object that is to the left of the philosopher in the window.
     * @param rightChopStick chopstick object that is to the right of the philosopher in the window
     */
    private void releaseChopSticks(ChopStick leftChopStick, ChopStick rightChopStick) {
        leftChopStick.releaseStick();
        rightChopStick.releaseStick();
    }

    /**
     * Method that takes both chopsticks for the philosopher loops till acquires both chopsticks.
     * @param leftChopStick chopstick object to the left of the philosopher.
     * @param rightChopStick chopstick object to the right of the philosopher.
     * @throws InterruptedException wait method in method waitForStick of object chopstick is interrupted.
     */
    private void takeChopSticks(ChopStick leftChopStick, ChopStick rightChopStick) throws InterruptedException {
        boolean eating = false;
        while(!eating) {
            if(leftChopStick.waitForStick()) {
                if(rightChopStick.tryToTakeStick()) {
                    eating = true;
                }else {
                    leftChopStick.releaseStick();
                }
            }else if(leftChopStick.tryToTakeStick()){
                if(rightChopStick.tryToTakeStick()) {
                    eating = true;
                } else {
                    leftChopStick.releaseStick();
                }
            }
        }
    }
    private synchronized  boolean isRun() {
        return run;
    }
    public synchronized void stopLoop() {
        run = false;
    }
}
