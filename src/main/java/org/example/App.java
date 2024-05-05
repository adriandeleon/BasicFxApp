package org.example;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // find more themes in 'atlantafx.base.theme' package
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        //Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

        // Create the menubar
        final MenuBar menuBar = createMenuBar();

        // Create the toolbar
        final ToolBar toolBar = createToolBar();

        // Create the statusbar
        final Label statusLabel = new Label("Status: Ready");

        // Create the main layout
        final BorderPane root = new BorderPane();
        root.setTop(new VBox(menuBar, toolBar));
        root.setCenter(new Label("Main Content Area"));
        root.setBottom(statusLabel);
        root.setPadding(new Insets(0));

        // Create the scene
        Scene scene = new Scene(root, 800, 600);

        // Set the stage properties
        primaryStage.setOnCloseRequest(event -> {
            event.consume(); // Consume the event to prevent immediate closing
            showExitConfirmationDialog();
        });

        primaryStage.setTitle("MenuBar, ToolBar, and StatusBar Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private MenuBar createMenuBar() {
        // Create the menu items
        final MenuItem newItem = new MenuItem("New");
        final MenuItem openItem = new MenuItem("Open");
        final MenuItem saveItem = new MenuItem("Save");
        final MenuItem exitItem = new MenuItem("Exit");
        final MenuItem preferencesItem = new MenuItem("Preferences");

        MenuItem aboutItem = new MenuItem("About");

        // Create the menus
        final Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(newItem, openItem, saveItem,preferencesItem, new SeparatorMenuItem(), exitItem);

        final Menu editMenu = new Menu("Edit");
        final Menu helpMenu = new Menu("Help");

        helpMenu.getItems().add(aboutItem);


        // Create the menubar
        final MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);

        aboutItem.setOnAction(_ -> showAboutDialog());
        exitItem.setOnAction(_ -> showExitConfirmationDialog());
        preferencesItem.setOnAction(_ -> showConfigurationWindow());

        return menuBar;
    }

    private ToolBar createToolBar() {
        // Create the toolbar buttons
        final Button newButton = new Button("New");
        final Button openButton = new Button("Open");
        final Button saveButton = new Button("Save");

        // Create the toolbar
        final ToolBar toolBar = new ToolBar();
        toolBar.getItems().addAll(newButton, openButton, saveButton);

        return toolBar;
    }

    private void showAboutDialog() {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("My Application");
        alert.setContentText("This is a sample application demonstrating the use of MenuBar, ToolBar, and StatusBar in JavaFX.");

        alert.showAndWait();
    }

    private void showExitConfirmationDialog() {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Exit Application");
        alert.setContentText("Are you sure you want to exit the application?");

        final ButtonType yesButton = new ButtonType("Yes");
        final ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == yesButton) {
                Platform.exit();
            }
        });
    }

    private void showConfigurationWindow() {
        ConfigWindow configWindow = new ConfigWindow();
        configWindow.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}