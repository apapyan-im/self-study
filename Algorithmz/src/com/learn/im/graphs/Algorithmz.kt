package com.learn.im.graphs

import java.util.*


fun Vertex.shortestPath2(end: Vertex): List<Vertex> {
    distance = 0

    fixDistances4(this)

    breadthFirstSearch {
        fixDistances4(it)
    }

    if(end.previous == null && end != this){
        throw PathIsNotFoundException(this, end)
    }

    return end.go2StartCollecting().also {
        depthFirstSearch {
            it.resetDistance()
        }
    }
}

private fun fixDistances4(vertex: Vertex){
    vertex.edges.forEach { edge ->
        val next = edge.destination
        val distance = vertex.distance + edge.weight
        if (distance < next.distance) {
            next.previous = vertex
            next.distance = distance
        }
    }
}

private fun Vertex?.go2StartCollecting():List<Vertex>{
    val passedVertices = mutableListOf<Vertex>()
    return if(this != null) {
        previous.go2StartCollecting() + passedVertices.also {
            it.add(this)
        }
    } else passedVertices
}


fun Vertex.depthFirstSearch(handle: (Vertex) -> Unit){
    val handled = mutableListOf<Vertex>()
    fun dfsInternal(start: Vertex){
        start.edges.forEach { edge ->
            edge.destination.ifNotVisited {
                handled.add(it.apply {
                    handle(visit())
                })
                dfsInternal(it)
            }
        }
    }
    dfsInternal(this)
    handled.forEach { it.leave() }
}

fun Vertex.breadthFirstSearch(handle: (Vertex) -> Unit){
    val handled = mutableListOf<Vertex>()
    val queue: Queue<Vertex> = LinkedList()
    queue.add(this)
    while (!queue.isEmpty()) {
        val current = queue.poll()
        handled.add(current)
        current.edges.forEach { edge ->
            edge.destination.ifNotVisited{
                queue.add(it.apply {
                    handle(visit())
                })
            }
        }
    }
    handled.forEach { it.leave() }
}

//debug
fun Vertex.dump(): MutableMap<Vertex, MutableSet<Edge>> {
    val map = mutableMapOf<Vertex, MutableSet<Edge>>()
    fun constructMatrix(from: Vertex){
        map[from] = from.edges
        for (edge in from.edges){
            if (edge.destination !in map){
                constructMatrix(edge.destination)
            }
        }
    }
    constructMatrix(this)
    return map
}