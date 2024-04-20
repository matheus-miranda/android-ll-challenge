package com.luizalabs.registration.domain.exception

sealed interface Exception

enum class NetworkException : Exception {
    NO_INTERNET,
    SERVER_ERROR,
    UNKNOWN_ERROR
}
