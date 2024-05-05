package org.example;

import javafx.application.Application;
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
        Scene scene = new Scene(root, 400, 300);

        // Set the stage properties
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

        MenuItem aboutItem = new MenuItem("About");

        // Create the menus
        final Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(newItem, openItem, saveItem, new SeparatorMenuItem(), exitItem);

        final Menu editMenu = new Menu("Edit");
        final Menu helpMenu = new Menu("Help");

        helpMenu.getItems().add(aboutItem);


        // Create the menubar
        final MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);

        aboutItem.setOnAction(_ -> showAboutDialog());

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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("My Application");
        alert.setContentText("This is a sample application demonstrating the use of MenuBar, ToolBar, and StatusBar in JavaFX.");

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}