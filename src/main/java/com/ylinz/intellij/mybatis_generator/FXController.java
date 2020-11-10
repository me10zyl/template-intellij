package com.ylinz.intellij.mybatis_generator;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class FXController implements Initializable {

    private Project project;
    private ToolWindow toolWindow;

    public FXController(Project project, ToolWindow toolWindow) {
        this.project = project;
        this.toolWindow = toolWindow;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
