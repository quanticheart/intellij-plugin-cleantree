package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.ui

fun cleanArchViewModel(
    featurePackage: String,
    viewModelName: String,
    useCaseNamePackage: String,
    useCaseName: String,
    modelPackagePackage: String,
    modelName: String,
) = """
package $featurePackage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import $modelPackagePackage.$modelName
import $useCaseNamePackage.$useCaseName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class $viewModelName(private val useCases: $useCaseName) : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status = _status

    fun getStatus(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val request = $modelName()
            request.id = id
            runCatching {
                useCases(request)
            }.onSuccess {
                it.getOrNull()?.let { status ->
                    _status.value = status.status
                }
            }.onFailure {
            }
        }
    }
}
"""
