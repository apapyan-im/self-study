package com.learn.im.dynamica

// f(0) = 1 , f(1) = 1, f(n) = f(n - 1) + f(n - 2)
fun fibonacciOf(n: Int): Long {
    val f = LongArray(n + 1)
    f[0] = 0
    f[1] = 1
    for (i in 2..n){
        f[i] = f[i - 1] + f[i - 2]
    }
    return f.last()
}

data class BackpackItem(val weight: Int, val value: Int, val id: Double = Math.random())

// s = "" || s1 == "" -> ""
// s.last = s1.last -> 1 + s.last + f(s - 1, s1 - 1)
// if len s > s1 -> f(s - 1, s1) else -> f(s, s1 - 1)
fun String.longestCommonSubsequence(string: String): Int {
    val resultMatrix = Array(this.length + 1) {
        IntArray(string.length + 1)
    }
    for (i in 1..this.length){
        for(j in 1..string.length){
            if(this[i - 1] == string[j - 1]){
                resultMatrix[i][j] = 1 + resultMatrix[i - 1][j - 1]
            }
            else{
                resultMatrix[i][j] = resultMatrix[i - 1][j].coerceAtLeast(resultMatrix[i][j - 1])
            }
        }
    }
    return resultMatrix.last().last()
}

fun String.has(substring: String): Boolean {
    if(substring.length > this.length) return false
    val full = "${substring}&${this}"
    val prefix = IntArray(full.length)
    var p = 0
    for (i in 1 until full.length){
        while(true){
            if(full[p] == full[i]){  ++p; break  }
            if(p == 0){ break }
            p = prefix[p - 1]
        }
        if(substring.length == p){
            return true
        }
        prefix[i] = p
    }
    return false
}

fun String.hazZ(string: String):Boolean{
    TODO()
}

fun String.getZ(): IntArray {
    TODO()
}

fun maxBackpackValue(items: List<BackpackItem>, maxWeight: Int): Int {
    val values = Array(maxWeight + 1) {
        Array(items.size + 1) { 0 }
    }
    for (i in 1..items.size){
        val weight = items[i - 1].weight
        val value = items[i - 1].value

        for (j in 1 until  weight) {
            if(j <= maxWeight)
                values[j][i] = values[j][i - 1]
        }

        for (j in weight..maxWeight){
            values[j][i] = values[j][i - 1].coerceAtLeast(value + values[j - weight][i - 1])
        }
    }
    return values.last().last()
}