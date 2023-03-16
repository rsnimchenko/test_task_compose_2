package com.example.test_task_compose_2.util

fun handleLoadUsers(code: Int): String {
    return when (code) {
        304 -> "Not modified"
        401 -> "Requires authentication"
        403 -> "Forbidden"
        else -> ""
    }
}