package com.example.restapibinding.util

import java.io.IOException

class NoInternetException(message: String) : IOException(message)
class SocketTimeoutExceptions(message: String) : IOException(message)
