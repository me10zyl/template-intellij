package com.ylinz.intellij.mybatis_generator;

import com.intellij.ide.util.PackageChooserDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class CodeGeneratorAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        if(e.getProject() == null) return;
        CodeGeneratorDialogWrapper wrapper = new CodeGeneratorDialogWrapper(e.getProject(), e);
        wrapper.show();
    }
}
