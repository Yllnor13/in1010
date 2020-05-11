import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.BeforeClass;

public class BasicStart extends Application {

    @BeforeClass
    public static void initJFX() {
        Thread t = new Thread("JavaFX Init Thread") {
            @Override
            public void run() {
                Application.launch(BasicStart.class, new String[0]);
            }
        };
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // noop
    }
}