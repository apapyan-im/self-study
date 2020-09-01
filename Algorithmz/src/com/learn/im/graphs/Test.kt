package com.learn.im.graphs


fun main() {

    val countOfVertices = 50
    val countOfEdges = (countOfVertices * 5)

    val vertices = (1..countOfVertices).map { Vertex("V${it}") }
    val edges = (1..countOfEdges).map { Edge(vertices.random()/*, (0..1000).random()*/) }

    edges.forEach { edge ->
        val current = vertices.random()
        if(!current.isConnectedWith(edge)) {
            current.connect(edge)
        }
    }

    runCatching {
        val first = vertices.random()
        val second = vertices.random()
        println("Shortest path from ${first.name} to ${second.name} is " + first.shortestPath2(second))
    }.onFailure {
        if(it is PathIsNotFoundException){
            println("${it.message}")
        }
        else throw it
    }
    println("------------------------------------------------")
//    from.dump().forEach { t, u ->
//        println("${t.name} $u")
//    }

}