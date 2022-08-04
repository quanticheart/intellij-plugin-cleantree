package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.data

fun cleanArchDataModelRequest(
    featurePackage: String,
    modelRequestName: String
) = """
package $featurePackage

import com.google.gson.annotations.SerializedName

class $modelRequestName {
    @SerializedName("idInService")
    var idInService = 0
}
"""

fun cleanArchDataModelResponse(
    featurePackage: String,
    modelResponseName: String
) = """
package $featurePackage

import com.google.gson.annotations.SerializedName

class $modelResponseName {
    @SerializedName("statusFromService")
    val statusFromService = true
}
"""
