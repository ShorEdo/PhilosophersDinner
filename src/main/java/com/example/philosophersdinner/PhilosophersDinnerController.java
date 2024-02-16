package com.example.philosophersdinner;
/**
 * Controller class for the app, used as a bridge between the processing of Philosopher and the gui's app.
 */
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PhilosophersDinnerController {

    @FXML private ImageView Nietzsche;
    @FXML private ImageView Descartes;
    @FXML private ImageView Sartre;
    @FXML private ImageView Schopenhauer;
    @FXML private ImageView Camus;
    @FXML private ImageView ChopStick5;
    @FXML private ImageView ChopStick1;
    @FXML private ImageView ChopStick4;
    @FXML private ImageView ChopStick3;
    @FXML private ImageView ChopStick2;

    /**
     * Method that changes the image of the philosopher that acquired both chopsticks to the image of him holding two
     * chopsticks indicating that he is eating, and disabling the chopsticks that he acquired.
     * @param philosopher imageView of the philosopher to use to get the eating image.
     * @param leftStick left chopstick object of the philosopher.
     * @param rightStick right chopstick object of the philosopher.
     */
    public synchronized void startEating(ImageView philosopher, ImageView leftStick, ImageView rightStick) {
        String path = philosopher.getImage().getUrl();
        path = path.substring(0, path.lastIndexOf('.')).concat(" Eating.png");
        philosopher.setImage(new Image(path));
        leftStick.setVisible(false);
        rightStick.setVisible(false);
    }

    /**
     * Method that changes the image of the philosopher eating to an image of him not eating ,
     * and set the chopsticks that he will release.
     * @param philosopher imageView of the philosopher to use to get the normal image.
     * @param leftStick left chopstick object of the philosopher.
     * @param rightStick right chopstick object of the philosopher.
     */
    public synchronized void stopEating(ImageView philosopher, ImageView leftStick, ImageView rightStick) {
        String path = philosopher.getImage().getUrl();
        path = path.substring(0, path.lastIndexOf('E') - 1).concat(".png");
        philosopher.setImage(new Image(path));
        leftStick.setVisible(true);
        rightStick.setVisible(true);
    }
    public ImageView getNietzsche() {
        return Nietzsche;
    }

    public ImageView getDescartes() {
        return Descartes;
    }

    public ImageView getSartre() {
        return Sartre;
    }

    public ImageView getSchopenhauer() {
        return Schopenhauer;
    }

    public ImageView getCamus() {
        return Camus;
    }

    public ImageView getChopStick5() {
        return ChopStick5;
    }

    public ImageView getChopStick1() {
        return ChopStick1;
    }

    public ImageView getChopStick4() {
        return ChopStick4;
    }

    public ImageView getChopStick3() {
        return ChopStick3;
    }

    public ImageView getChopStick2() {
        return ChopStick2;
    }
}
