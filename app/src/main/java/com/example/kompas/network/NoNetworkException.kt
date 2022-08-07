package com.example.kompas.network

import java.io.IOException

class NoNetworkException: IOException() {
    override val message: String?
        get() = "No internet connection, please try again later"
}