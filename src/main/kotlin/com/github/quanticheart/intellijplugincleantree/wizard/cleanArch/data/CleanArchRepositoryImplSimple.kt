package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.data

fun cleanArchRepositoryImplSimple(
    featurePackage: String,
    repositoryImplName: String,
    endPointsName: String,
) = """
package $featurePackage

class $repositoryImplName(private val service: $endPointsName) {
}
"""
