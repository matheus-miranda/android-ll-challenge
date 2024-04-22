package com.luizalabs.registration.presentation.detail.helper

import com.luizalabs.registration.R
import com.luizalabs.registration.domain.error.NetworkError

fun NetworkError.asUiText(): UiText {
    return when (this) {
        NetworkError.NO_INTERNET -> UiText.StringResource(R.string.no_internet)
        NetworkError.CONNECTION_ERROR -> UiText.StringResource(R.string.connection_error)
        NetworkError.REQUEST_TIMEOUT -> UiText.StringResource(R.string.request_timeout)
        NetworkError.PAYLOAD_TOO_LARGE -> UiText.StringResource(R.string.payload_too_large)
        NetworkError.PARSE_ERROR -> UiText.StringResource(R.string.parse_error)
        NetworkError.UNKNOWN_ERROR -> UiText.StringResource(R.string.error_try_again_later)
    }
}

fun DetailScreenError.asUiText() : UiText {
    return when (this) {
        DetailScreenError.EMPTY_FIELDS -> UiText.StringResource(R.string.fill_all_fields)
        DetailScreenError.SUBMIT_ERROR -> UiText.StringResource(R.string.save_failed)
    }
}

enum class DetailScreenError {
    EMPTY_FIELDS,
    SUBMIT_ERROR
}

