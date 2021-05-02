package bloc.generator.components

import  bloc.generator.BlocGenerator

class BlocStateGenerator(
    name: String,
    useEquatable: Boolean
) : BlocGenerator(name, useEquatable, templateName = "bloc_state") {
    override fun fileName() = "${snakeCase()}_state.${fileExtension()}"
}