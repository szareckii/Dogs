package com.zareckii.dogs.utils

interface Now {

    fun time(): Long

    class Base : Now {
        override fun time(): Long = System.currentTimeMillis()
    }

}
