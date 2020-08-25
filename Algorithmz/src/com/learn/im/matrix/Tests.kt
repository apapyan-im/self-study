package com.learn.im.matrix

fun main(){

    val a = listOf(
            listOf(5, -1, 0),
            listOf(2, 1, 1)
    )

    val b = listOf(
            listOf(0, 2),
            listOf(3, 1),
            listOf(-1, -2)
    )

    multiply(a, b).forEach(::println)
}
