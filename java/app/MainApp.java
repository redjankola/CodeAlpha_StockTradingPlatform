package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.DashboardView;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        DashboardView dashboard = new DashboardView();

        Scene scene = new Scene(dashboard.getRoot(), 1400, 850);

       /* scene.getStylesheets().add(
                getClass().getResource("/styles.css").toExternalForm()
        );*/

        stage.setTitle("Professional Stock Trading Platform");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}