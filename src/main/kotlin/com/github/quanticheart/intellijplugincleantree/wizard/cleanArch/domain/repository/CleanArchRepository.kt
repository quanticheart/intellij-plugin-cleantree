package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.domain.repository

fun cleanArchRepository(
    featurePackage: String,
    featureName: String,
    repositoryName: String,
    domainModelPackage: String,
    domainModelRequestName: String,
    domainModelResponseName: String,
) = """
package $featurePackage

import $domainModelPackage.$domainModelResponseName
import $domainModelPackage.$domainModelRequestName

interface $repositoryName {
    suspend fun get$featureName(mainStatus: $domainModelRequestName): Result<$domainModelResponseName>
}
"""
