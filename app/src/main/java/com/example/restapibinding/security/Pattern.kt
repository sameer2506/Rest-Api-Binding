package com.example.restapibinding.security

import java.util.regex.Pattern

fun phoneNumberPattern(text: String) : Boolean {
    val p = Pattern.compile("[0-9]{10}")
    val m = p.matcher(text)
    return m.matches()
}