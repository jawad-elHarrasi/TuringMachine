import javafx.application.Application;
import javafx.stage.Stage;
import view.graphicInterface.GraphicInterfaceLauncher;

/**
 * The main class for launching the JavaFX application.
 *
 * <p>This class extends {@link javafx.application.Application} and serves as the entry point for the application.</p>
 *
 * @author Your Name
 * @version 1.0
 */
public class AppFx extends Application {

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The overridden method from {@link javafx.application.Application} that is called when the application is launched.
     *
     * @param stage The main window (Stage) of the application.
     * @throws Exception If an error occurs during application startup.
     */
    @Override
    public void start(Stage stage) throws Exception {
        new GraphicInterfaceLauncher(stage);
    }
}
