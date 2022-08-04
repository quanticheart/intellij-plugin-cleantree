package com.github.quanticheart.intellijplugincleantree.wizard.cleanArch.data

fun cleanArchDataMapperRequest(
    featurePackage: String,
    mapperRequestName: String,
    dataRequestPackage: String,
    requestName: String,
    modelRequestPackage: String,
    requestModelName: String,
) = """
package $featurePackage

import $modelRequestPackage.$requestModelName
import $dataRequestPackage.$requestName

class $mapperRequestName {
    fun map(source: $requestModelName): $requestName {
        val result = $requestName()
        result.idInService = source.id
        return result
    }
}
"""

fun cleanArchDataMapperResponse(
    featurePackage: String,
    mapperResponseName: String,
    dataResponsePackage: String,
    responseName: String,
    modelResponsePackage: String,
    responseModelName: String,
) = """
package $featurePackage

import $dataResponsePackage.$responseName
import $modelResponsePackage.$responseModelName

class $mapperResponseName {
    fun map(source: $responseName): $responseModelName {
        val result = $responseModelName()
        result.status = source.statusFromService
        return result
    }
}
"""
