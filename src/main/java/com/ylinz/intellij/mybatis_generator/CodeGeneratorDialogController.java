package com.ylinz.intellij.mybatis_generator;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ContentIterator;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileFilter;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiJavaFile;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CodeGeneratorDialogController implements Initializable {

    private Project project;
    private AnActionEvent e;

    @FXML
    private Label labelService;
    @FXML
    private ChoiceBox choiceMapper;
    @FXML
    private Label labelMapper;




    public CodeGeneratorDialogController(Project project, AnActionEvent e) {
        this.project = project;
        this.e = e;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Editor editor = PlatformDataKeys.EDITOR.getData(e.getDataContext());
        //PsiElement psiElement = e.getData(CommonDataKeys.PSI_ELEMENT);
        PsiJavaFile psiFile = (PsiJavaFile) e.getData(CommonDataKeys.PSI_FILE);
        PsiClass[] classes = psiFile.getClasses();
        PsiClass psiClass = classes[0];
        List<String> fieldsList = new ArrayList<>();
        PsiField[] allFields = psiClass.getAllFields();
        for (PsiField allField : allFields) {
            if(allField.hasAnnotation("Autowired") || allField.hasAnnotation("Resource")){
                fieldsList.add(allField.getName());
            }
        }
        choiceMapper.setItems(new ObservableListWrapper(fieldsList));
        choiceMapper.setValue(fieldsList.get(0));
        this.labelService.setText(psiClass.getQualifiedName());
        this.labelMapper.textProperty().bind(new ReadOnlyObjectWrapper());
        ProjectFileIndex fileIndex = ProjectRootManager.getInstance(project).getFileIndex();
        fileIndex.iterateContent(new ContentIterator() {
            @Override
            public boolean processFile(@NotNull VirtualFile fileOrDir) {

                return true;
            }
        }, new VirtualFileFilter() {
            @Override
            public boolean accept(VirtualFile file) {
                return file.getName().endsWith(".xml");
            }
        });
        //Module moduleForFile = fileIndex.getModuleForFile(psiFile.getVirtualFile());
        //ResourceFileUtil.findResourceFileInProject(e.getProject(), "*.xml");
    }
}
