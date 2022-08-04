package com.github.quanticheart.intellijplugincleantree.codespector

import com.intellij.AbstractBundle
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey

object MessageBundle : AbstractBundle(BUNDLE_NAME) {

    fun get(@PropertyKey(resourceBundle = BUNDLE_NAME) key: String, vararg params: Any?): String {
        return getMessage(key, *params)
    }
}

@NonNls
private const val BUNDLE_NAME = "messages.CodespectorBundle"