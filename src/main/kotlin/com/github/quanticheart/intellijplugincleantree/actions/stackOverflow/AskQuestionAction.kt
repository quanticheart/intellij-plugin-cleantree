package com.github.quanticheart.intellijplugincleantree.actions.stackOverflow

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ui.Messages

class AskQuestionAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.getData(PlatformDataKeys.PROJECT)

        val editor = e.getData(CommonDataKeys.EDITOR)
        val selectedText = editor?.selectionModel?.selectedText

        val queryTxt: String?
        if (selectedText != null) {
            queryTxt = Messages.showInputDialog(
                "Type your question",
                "Stack Overflow",
                Messages.getQuestionIcon(),
                selectedText,
                null
            )
        } else {
            queryTxt = Messages.showInputDialog(
                project,
                "Type your question",
                "Stack Overflow",
                Messages.getQuestionIcon()
            )
        }
        if (queryTxt != null) {
            BrowserUtil.browse("https://stackoverflow.com/search?q=$queryTxt")
        }
    }

    override fun update(e: AnActionEvent) {
        val project = e.project
        e.presentation.isEnabledAndVisible = project != null
    }
}