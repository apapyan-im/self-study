package com.learn.im.dynamica

fun main(){

    println("helloworld".longestCommonSubsequence("eo2r2ld"))

    println(fibonacciOf(5000))

    println("ABCABCDABCABCABC".has("ABCABCDAB"))
    println("ABCABCDABCABCABC".has("ABCABC"))
    println("ABCABCDABCABCABC".has("ABCAB"))
    println("ABCABCDABCABCABC".has("xx"))
    println("ABCABCDABCABCABC".has("x"))



    println(maxBackpackValue(mutableListOf(
            BackpackItem(4,5),
            BackpackItem(2,8),
            BackpackItem(3,3),
            BackpackItem(4,20),
            BackpackItem(10,1),
            BackpackItem(6,30),
            BackpackItem(1,6)
    ), 5))
}