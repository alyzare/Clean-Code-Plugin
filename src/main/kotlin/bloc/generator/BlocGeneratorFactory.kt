package bloc.generator

import bloc.generator.components.BlocEventGenerator
import bloc.generator.components.BlocGenerator
import bloc.generator.components.BlocStateGenerator

object BlocGeneratorFactory {
    fun getBlocGenerators(name: String, useEquatable: Boolean): List<bloc.generator.BlocGenerator> {
        val bloc = BlocGenerator(name, useEquatable)
        val event = BlocEventGenerator(name, useEquatable)
        val state = BlocStateGenerator(name, useEquatable)
        return listOf(bloc, event, state)
    }
}