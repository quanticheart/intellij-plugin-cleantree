package com.github.quanticheart.intellijplugincleantree.actions.files.test

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.psi.*
import com.intellij.psi.codeStyle.JavaCodeStyleManager

class CreateTest3FileAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val psiFile = e.dataContext.getData(LangDataKeys.PSI_FILE)
        val entityClass = getEntityClass(psiFile)!!
//        if (entityClass == null) {
//            Messages.showErrorDialog("The current class is not a JPA entity", "Ooops")
//            return
//        }
        var primaryKeyType = "Long"
        var toImport: String? = null
        val idField = getIdField(entityClass)
        if (idField == null) {
            Messages.showInfoMessage("The JPA entity doesn't have a field with @id", "Id Field not found")
        } else {
            val type = idField.type

            //primaryKeyType = type.getPresentableText();
            primaryKeyType = type.canonicalText
            toImport = type.canonicalText
        }
        val directoryService = JavaDirectoryService.getInstance()
        val directory = entityClass.containingFile.containingDirectory
        val interfaceName = entityClass.name + "Repository"
        val repoInterfaceTemplate = "SpringDataRepo.java"
        val props: MutableMap<String, String> = HashMap()
        props["Entity"] = entityClass.name!!
        props["PrimaryKeyType"] = primaryKeyType
        var generatedClass: PsiClass? = null
        val exists = directory.findFile("$interfaceName.java") != null
        if (exists) {
            val ans =
                Messages.showOkCancelDialog("File already exists. Do you want to override?", "File already exist", null)
            if (ans == Messages.YES) {
                directory.findFile("$interfaceName.java")!!.delete()
                generatedClass =
                    directoryService.createClass(directory, interfaceName, repoInterfaceTemplate, true, props)
            }
        } else {
            generatedClass = directoryService.createClass(directory, interfaceName, repoInterfaceTemplate, true, props)
        }
        val project = entityClass.manager.project
        val factory = JavaPsiFacade.getInstance(project).elementFactory
        val repoInter = generatedClass
        val primaryKeyFQN = toImport
        WriteCommandAction.runWriteCommandAction(project) {
            //addImport(project, repoInter, primaryKeyFQN);
            shortenClassReferences(project, repoInter)
        }
    }

    private fun getIdField(entityClass: PsiClass): PsiField? {
        for (field in entityClass.fields) {
            if (field.hasAnnotation("javax.persistence.Id")) {
                return field
            }
        }
        return null
    }

    private fun getEntityClass(psiFile: PsiFile?): PsiClass? {
        val psiJavaFile = psiFile as PsiJavaFile?
        val classes = psiJavaFile?.classes
        if (classes != null) {
            for (aClass in classes) {
                if (aClass.hasAnnotation("javax.persistence.Entity")) {
                    return aClass
                }
            }
        }
        return null
    }

    private fun shortenClassReferences(project: Project, psiClass: PsiClass?) {
        val file = psiClass!!.containingFile as? PsiJavaFile ?: return
        JavaCodeStyleManager.getInstance(project).shortenClassReferences(file)
    }
}