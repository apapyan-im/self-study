package com.learn.im.matrix

fun multiply(matrixA: List<List<Int>>, matrixB: List<List<Int>>): MutableList<List<Int>> {
    val result = mutableListOf<List<Int>>()
    for (i in matrixA.indices){
        val row = mutableListOf<Int>()
        for (j in matrixA.indices){
            var res = 0
            for (k in matrixB.indices){
                res += matrixA[i][k] * matrixB[k][j]
            }
            row.add(res)
        }
        result.add(row)
    }
    return result
}


// mb to be continued