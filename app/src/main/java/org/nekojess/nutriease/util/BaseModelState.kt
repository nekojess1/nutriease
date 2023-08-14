package org.nekojess.nutriease.util

import org.nekojess.nutriease.domain.dto.RecipesListDto

data class BaseModelState(val data: RecipesListDto?, val status: STATUS, val error: Throwable? = null) {
    enum class STATUS {
        LOADING, SUCCESS, ERROR
    }

    companion object {
        fun success(data: RecipesListDto) =
            BaseModelState(
                data,
                STATUS.SUCCESS
            )

        fun error(error: Throwable) =
            BaseModelState(
                null, STATUS.ERROR,
                error
            )

        fun loading() =
            BaseModelState(null, STATUS.LOADING)
    }
}