package bloc.generator.components

import  bloc.generator.CubitGenerator

class CubitGenerator(
    name: String,
    useEquatable: Boolean
) : CubitGenerator(name, useEquatable, templateName = "cubit") {
    override fun fileName() = "${snakeCase()}_cubit.${fileExtension()}"
}