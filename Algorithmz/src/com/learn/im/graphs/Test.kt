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

    val from = vertices.random()
    val to = vertices.random()

    println("\n------------------------------------------------")
    print("BFSING...")
    vertices.random().breadthFirstSearch {
        print("$name -> ")
    }
    println("\n------------------------------------------------")
    print("DFSING...")
    vertices.random().depthFirstSearch {
        print("$name -> ")
    }
    println("\n------------------------------------------------")
    runCatching {
        println("Shortest path from ${from.name} to ${to.name} is " + from.shortestPath(to))
    }.onFailure {
        println("Path from ${from.name} to ${to.name} is not found")
    }
    println("------------------------------------------------")
    from.dump().forEach { t, u ->
        println("${t.name} $u")
    }
}