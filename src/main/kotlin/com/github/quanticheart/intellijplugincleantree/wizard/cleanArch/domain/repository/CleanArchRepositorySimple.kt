package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.domain.repository

fun cleanArchRepositorySimple(
    featurePackage: String,
    featureName: String,
    repositoryName: String
) = """
package $featurePackage

interface $repositoryName {
    suspend fun get$featureName(): Result<Boolean>
}
"""
