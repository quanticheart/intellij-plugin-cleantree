package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.ui

fun cleanArchViewModelSimple(
    featurePackage: String,
    viewModelName: String
) = """
package $featurePackage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class $viewModelName() : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status = _status

    fun getStatus(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                Result.success(true)
            }.onSuccess {
                it.getOrNull()?.let { status ->
                    _status.value = status
                }
            }.onFailure {
            }
        }
    }
}
"""
