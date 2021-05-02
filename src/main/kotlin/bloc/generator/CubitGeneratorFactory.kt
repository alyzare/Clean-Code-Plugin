package bloc.generator

import bloc.generator.components.CubitGenerator
import bloc.generator.components.CubitStateGenerator

object CubitGeneratorFactory {
    fun getCubitGenerators(name: String, useEquatable: Boolean): List<bloc.generator.CubitGenerator> {
        val cubit = CubitGenerator(name, useEquatable)
        val state = CubitStateGenerator(name, useEquatable)
        return listOf(cubit, state)
    }
}