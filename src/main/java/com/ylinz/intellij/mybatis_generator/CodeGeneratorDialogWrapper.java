package com.ylinz.intellij.mybatis_generator;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.util.ui.JBUI;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.IOException;

public class CodeGeneratorDialogWrapper extends DialogWrapper {
    private Project project;
    private AnActionEvent e;

    public CodeGeneratorDialogWrapper(Project project, AnActionEvent e) {
        super(project, true);
        this.project = project;
        this.e = e;
        setOKButtonText("Generate");
        setCancelButtonText("Cancel");
        init();
        setTitle("Code Generate...");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        final JFXPanel fxPanel = new JFXPanel();
        FXMLLoader loader = new FXMLLoader();
        String fxmlDocPath = "/dialog.fxml";
        loader.setLocation(MainUI.class.getResource(fxmlDocPath));
        Pane root = null;
        loader.setController(new CodeGeneratorDialogController(this.project, e));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        fxPanel.setScene(new Scene(root, Color.ALICEBLUE));
        fxPanel.setPreferredSize(JBUI.size(400,300));
        return fxPanel;
    }

    @Override
    protected void doOKAction() {

        super.doOKAction();
    }
}
