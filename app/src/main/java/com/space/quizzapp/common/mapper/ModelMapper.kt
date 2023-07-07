package com.space.quizzapp.common.mapper

interface ModelMapper<in ModelA, out ModelB> {
    operator fun invoke(model: ModelA): ModelB
}
