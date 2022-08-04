package com.github.quanticheart.intellijplugincleantree.codespector.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

/**
 * Project level settings which is persisted.
 **/
@State(
    name = "com.github.quanticheart.intellijplugincleantree.codespector.settings.InspectionSettingState",
    storages = [Storage("Codespector.xml")]
)
class InspectionSettingState: PersistentStateComponent<InspectionSettingState> {

    var parser: Parser = Parser.GSON

    override fun getState(): InspectionSettingState {
        return this
    }

    override fun loadState(state: InspectionSettingState) {
        XmlSerializerUtil.copyBean(state, this)
    }
}

enum class Parser(val annotation: String) {
    GSON("SerializedName"), MOSHI("Json"), KOTLINX_SERIALIZATION("SerialName")
}