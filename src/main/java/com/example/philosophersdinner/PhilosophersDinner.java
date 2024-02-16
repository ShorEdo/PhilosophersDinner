package com.example.philosophersdinner;
/**
 * The main class that lunches the app Philosophers Dinning, The main javaFX thread lunches the 5 philosophers threads
 * and updates the threads to end when the window is closed with the X button.
 * Made by Edo Shor.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class PhilosophersDinner extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PhilosophersDinner.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Philosophers Dinning");
        stage.setScene(scene);
        stage.show();
        PhilosophersDinnerController c = fxmlLoader.getController();
        ChopStick[] sticks = new ChopStick[5];
        addChopSticks(sticks, c);
        Philosopher[] philosophers = new Philosopher[5];
        addPhilosophers(philosophers, sticks, c);
        for(int i = 0; i < 5; i++) {
            philosophers[i].start();
        }
        // X button pressed tell the threads to finish.
        stage.setOnCloseRequest(event -> {
            System.out.println("Stage is closing");
            for(int i = 0; i < 5; i++) {
                philosophers[i].stopLoop();
            }
        });
    }

    /**
     * Initializes the threads that use the class Philosopher run method, Assigning them with the chopsticks they
     * need to pick up and a controller to the gui of the app.
     * @param philosophers array of Philosopher needs to have a length of 5.
     * @param sticks array of ChopStick needs to have 5 ChopStick objects in places 0 to 4.
     * @param c the controller of the gui for the app.
     */
    private void addPhilosophers(Philosopher[] philosophers, ChopStick[] sticks, PhilosophersDinnerController c) {
        philosophers[0] = new Philosopher(c.getNietzsche(), sticks[0], sticks[4], c);
        philosophers[1] = new Philosopher(c.getSchopenhauer(), sticks[0], sticks[1], c);
        philosophers[2] = new Philosopher(c.getCamus(), sticks[1], sticks[2], c);
        philosophers[3] = new Philosopher(c.getSartre(), sticks[2], sticks[3], c);
        philosophers[4] = new Philosopher(c.getDescartes(), sticks[3], sticks[4], c);
    }

    /**
     * Initializes the Chopstick objects inside the ChopStick array parameter
     * @param sticks array of ChopStick class needs to be length 5.
     * @param c controller of the app.
     */
    private void addChopSticks(ChopStick[] sticks, PhilosophersDinnerController c) {
        sticks[0] = new ChopStick(c.getChopStick1());
        sticks[1] = new ChopStick(c.getChopStick2());
        sticks[2] = new ChopStick(c.getChopStick3());
        sticks[3] = new ChopStick(c.getChopStick4());
        sticks[4] = new ChopStick(c.getChopStick5());
    }

    public static void main(String[] args) {
        launch();
    }
}