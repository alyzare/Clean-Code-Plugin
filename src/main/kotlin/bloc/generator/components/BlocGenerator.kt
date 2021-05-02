package bloc.generator.components

import  bloc.generator.BlocGenerator

class BlocGenerator(
    name: String,
    useEquatable: Boolean
) : BlocGenerator(name, useEquatable, templateName = "bloc") {
    override fun fileName() = "${snakeCase()}_bloc.${fileExtension()}"
}