package com.github.quanticheart.intellijplugincleantree.wizard.others.mapper

fun mapperTemplate(
    featurePackage: String,
    featureName: String,
    class1: String,
    class2: String
) = """
package $featurePackage

class $featureName : Mapper<$class1, $class2> {
    override fun map(source: $class1): $class2 {
        
    }
}
"""

fun mapperGenericTemplate(
    featurePackage: String
) = """
package $featurePackage

interface Mapper<S, T> {
    fun map(source: S): T
}
"""