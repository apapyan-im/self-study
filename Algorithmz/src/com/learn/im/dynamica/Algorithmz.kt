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

// s = "" || s1 == "" -> ""
// s.last = s1.last -> s.last + f(s - 1, s1 - 1)
// if len s > s1 -> f(s - 1, s1) else -> f(s, s1 - 1)
fun String.longestCommonSubsequence(string: String): String {
    val f = Array(this.length + 1) {
        IntArray(string.length + 1) { 0 }
    }
    for (i in 1..this.length){
        for(j in 1..string.length){
            if(this[i - 1] == string[j - 1]){
                f[i][j] = 1 + f[i - 1][j - 1]
            }
            else{
                f[i][j] = f[i - 1][j].coerceAtLeast(f[i][j - 1])
            }
        }
    }
    var result = ""
    for (i in 1 until f.last().size ){
        if(f.last()[i] != f.last()[i - 1]){
            result += string[i - 1]
        }
    }
    return result
}

fun String.has(substring: String): Boolean {
    if(substring.length > this.length) return false
    val full = "${substring}&${this}"
    val prefix = IntArray(full.length)
    var p = 0
    for (i in 1 until full.length){
        if(substring.length == p){
            return true
        }
        while(true){
            if(full[p] == full[i]){
                ++p; break
            }
            if(p == 0){
                break
            }
            p = prefix[p]
        }
        prefix[i - 1] = p
    }
    return false
}

data class BackpackItem(val weight: Int, val value: Int, val id: Double = Math.random()){
    override fun toString(): String = "[V(${value}) W(${weight})]"
}

fun maxBackpackValue(items: List<BackpackItem>, maxWeight: Int): List<BackpackItem> {
    val f = Array(maxWeight + 1) {
        IntArray(items.size + 1)
    }
    for (i in 1..items.size){
        val weight = items[i - 1].weight
        val value = items[i - 1].value

        for (j in 1 until weight) {
            if(j < maxWeight){
                f[j][i] = f[j][i - 1]
            }
        }
        for (j in weight..maxWeight){
            f[j][i] = f[j][i - 1].coerceAtLeast(value + f[j - weight][i - 1])
        }
    }

    val listOfTaken = mutableListOf<BackpackItem>()
    var remainingWeight = maxWeight
    var currentValue = f.last().last()
    for (i in items.size downTo 1){
        if (currentValue != f[remainingWeight][i - 1]){
            currentValue -= items[i-1].value
            remainingWeight -= items[i-1].weight
            listOfTaken.add(items[i-1])
        }
        if(remainingWeight < 0) break
    }

    return listOfTaken
}

fun String.hazZ(string: String):Boolean{
    TODO()
}

fun String.getZ(): IntArray {
    TODO()
}