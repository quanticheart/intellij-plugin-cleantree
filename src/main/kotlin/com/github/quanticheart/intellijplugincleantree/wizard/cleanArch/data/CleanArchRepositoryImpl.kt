package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.data

fun cleanArchRepositoryImpl(
    featurePackage: String,
    featureName: String,
    repositoryName: String,
    repositoryImplName: String,
    endPointsName: String,
    importRepositoryPackage: String,
    domainModelsPackage: String,
    domainModelRequestName: String,
    domainModelResponseName: String,
    dataMapperRequestPackage: String,
    dataMapperRequestName: String,
    dataMapperResponsePackage: String,
    dataMapperResponseName: String,

) = """
package $featurePackage

import $dataMapperRequestPackage.$dataMapperRequestName
import $dataMapperResponsePackage.$dataMapperResponseName
import $domainModelsPackage.$domainModelResponseName
import $domainModelsPackage.$domainModelRequestName
import ${importRepositoryPackage}.${repositoryName}

class $repositoryImplName(private val service: $endPointsName) : $repositoryName {
    override suspend fun get$featureName(mainStatus: $domainModelRequestName): Result<$domainModelResponseName> {

        val request = $dataMapperRequestName().map(mainStatus)
        val response = service.requestStatus(request)

        val status = $dataMapperResponseName().map(response.body()!!)
        return Result.success(status)
    }
}
"""
