package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.domain.model

fun cleanArchModelRequest(
    featurePackage: String,
    modelRequestName: String
) = """
package $featurePackage

class $modelRequestName {
    var id = 0
}
"""

fun cleanArchModelResponse(
    featurePackage: String,
    modelResponseName: String
) = """
package $featurePackage

class $modelResponseName {
    var status = false
}
"""
