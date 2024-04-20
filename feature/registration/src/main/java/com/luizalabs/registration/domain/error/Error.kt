package com.luizalabs.registration.domain.error

sealed interface Error

enum class NetworkError : Error {
    NO_INTERNET,
    CONNECTION_ERROR,
    REQUEST_TIMEOUT,
    PAYLOAD_TOO_LARGE,
    PARSE_ERROR,
    UNKNOWN_ERROR
}
