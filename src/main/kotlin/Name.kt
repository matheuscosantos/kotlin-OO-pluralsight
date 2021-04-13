package com.rsk

import java.lang.IllegalArgumentException

class Name(val name: String) {
    init {
        if(name.isBlank()) throw IllegalArgumentException()
    }

    override fun toString(): String {
        return name
    }
}