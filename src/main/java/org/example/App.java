package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        UIBuilder uiBuilder = new UIBuilder();

        // Set the stage properties
        primaryStage.setOnCloseRequest(event -> {
            event.consume(); // Consume the event to prevent immediate closing
            uiBuilder.showExitConfirmationDialog();
        });

        primaryStage.setTitle("MenuBar, ToolBar, and StatusBar Example");
        primaryStage.setScene(uiBuilder.createScene());
        primaryStage.show();
    }

    public static void processAppExit() {
        //Do other stuff here.
        Platform.exit();
    }


    public static void main(String[] args) {
        launch(args);
    }
}