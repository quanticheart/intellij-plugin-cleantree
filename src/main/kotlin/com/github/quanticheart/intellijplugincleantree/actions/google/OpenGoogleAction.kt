package com.github.quanticheart.intellijplugincleantree.actions.google

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class OpenGoogleAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        BrowserUtil.browse("https://google.com/")
    }

    override fun update(e: AnActionEvent) {
    }
}