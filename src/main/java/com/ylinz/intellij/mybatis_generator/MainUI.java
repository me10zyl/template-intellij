package com.ylinz.intellij.mybatis_generator;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.IOException;

public class MainUI implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        final JFXPanel fxPanel = new JFXPanel();
        JComponent component = toolWindow.getComponent();

        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader();
            // Path to the FXML File
            String fxmlDocPath = "/main.fxml";
            loader.setLocation(MainUI.class.getResource(fxmlDocPath));
            TabPane root = null;
            loader.setController(new FXController(project, toolWindow));
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fxPanel.setScene(new Scene(root, Color.ALICEBLUE));
        });

        component.getParent().add(fxPanel);
    }
}
