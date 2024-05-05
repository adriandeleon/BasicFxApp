package org.example;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConfigWindow {

    public void showAndWait() {
        final Stage stage = new Stage();
        stage.setTitle("Configuration");

        // Create the configuration window layout
        final BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Create the settings tree view
        final TreeView<String> settingsTree = createSettingsTree();
        root.setLeft(settingsTree);

        // Create the settings panel
        final VBox settingsPanel = new VBox(10);
        settingsPanel.setPadding(new Insets(10));
        root.setCenter(settingsPanel);

        // Add event listener to the settings tree view
        settingsTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                String selectedItem = newValue.getValue();
                if (selectedItem.equals("General")) {
                    settingsPanel.getChildren().setAll(createGeneralSettingsPanel());
                } else if (selectedItem.equals("Appearance")) {
                    settingsPanel.getChildren().setAll(createAppearanceSettingsPanel());
                } else if (selectedItem.equals("Editor")) {
                    settingsPanel.getChildren().setAll(createEditorSettingsPanel());
                }
            }
        });

        // Create the buttons
        final Button applyButton = new Button("Apply");
        final Button cancelButton = new Button("Cancel");
        final ButtonBar buttonBar = new ButtonBar();
        buttonBar.getButtons().addAll(applyButton, cancelButton);
        root.setBottom(buttonBar);
        cancelButton.setOnMouseClicked(_ -> stage.close());

        // Create the scene
        final Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private TreeView<String> createSettingsTree() {
        // Create the settings categories
        final TreeItem<String> rootItem = new TreeItem<>("Settings");
        final TreeItem<String> generalItem = new TreeItem<>("General");
        final TreeItem<String> appearanceItem = new TreeItem<>("Appearance");
        final TreeItem<String> editorItem = new TreeItem<>("Editor");

        // Add the categories to the root item
        rootItem.getChildren().addAll(generalItem, appearanceItem, editorItem);

        // Create the settings tree view
        final TreeView<String> settingsTree = new TreeView<>(rootItem);
        settingsTree.setShowRoot(false);

        return settingsTree;
    }

    private VBox createGeneralSettingsPanel() {
        // Create the general settings panel
        final VBox generalSettingsPanel = new VBox(10);
        generalSettingsPanel.setPadding(new Insets(10));

        // Create the general settings
        final Label generalLabel = new Label("General Settings");
        final CheckBox autoSaveCheckBox = new CheckBox("Auto Save");
        final CheckBox showToolbarCheckBox = new CheckBox("Show Toolbar");

        // Add the settings to the panel
        generalSettingsPanel.getChildren().addAll(generalLabel, autoSaveCheckBox, showToolbarCheckBox);

        return generalSettingsPanel;
    }

    private VBox createAppearanceSettingsPanel() {
        // Create the appearance settings panel
        final VBox appearanceSettingsPanel = new VBox(10);
        appearanceSettingsPanel.setPadding(new Insets(10));

        // Create the appearance settings
        final Label appearanceLabel = new Label("Appearance Settings");
        final ComboBox<String> themeComboBox = new ComboBox<>();
        themeComboBox.getItems().addAll("Light", "Dark");

        // Add the settings to the panel
        appearanceSettingsPanel.getChildren().addAll(appearanceLabel, themeComboBox);

        return appearanceSettingsPanel;
    }

    private VBox createEditorSettingsPanel() {
        // Create the editor settings panel
        final VBox editorSettingsPanel = new VBox(10);
        editorSettingsPanel.setPadding(new Insets(10));

        // Create the editor settings
        final Label editorLabel = new Label("Editor Settings");
        final CheckBox lineNumbersCheckBox = new CheckBox("Show Line Numbers");
        final CheckBox codeCompletionCheckBox = new CheckBox("Enable Code Completion");

        // Add the settings to the panel
        editorSettingsPanel.getChildren().addAll(editorLabel, lineNumbersCheckBox, codeCompletionCheckBox);

        return editorSettingsPanel;
    }
}
