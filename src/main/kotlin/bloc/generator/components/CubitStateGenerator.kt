package bloc.generator.components

import  bloc.generator.CubitGenerator

class CubitStateGenerator(
    name: String,
    useEquatable: Boolean
) : CubitGenerator(name, useEquatable, templateName = "cubit_state") {
    override fun fileName() = "${snakeCase()}_state.${fileExtension()}"
}