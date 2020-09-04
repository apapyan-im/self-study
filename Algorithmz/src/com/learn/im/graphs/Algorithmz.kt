package com.learn.im.graphs

import java.util.*


fun DijkstraVertex.shortestPath2(vertex: DijkstraVertex): List<DijkstraVertex> {
    distance = 0
    fixDistances4(this)


    breadthFirstSearch {
        fixDistances4(it)
    }

    if(vertex.previous == null && vertex != this){
        throw PathIsNotFoundException(this, vertex)
    }

    return vertex.go2StartCollecting().also {
        depthFirstSearch {
            it.resetDistance()
        }
    }
}

private fun fixDistances4(vertex: DijkstraVertex){
    vertex.edges.forEach { edge ->
        val next = edge.destination
        val distance = vertex.distance + edge.weight
        if (distance < next.distance) {
            next.previous = vertex
            next.distance = distance
        }
    }
}

private fun DijkstraVertex?.go2StartCollecting():List<DijkstraVertex>{
    val alreadyGenerated = mutableListOf<DijkstraVertex>()
    return if(this != null) {
        previous.go2StartCollecting() + alreadyGenerated.also {
            it.add(this)
        }
    } else alreadyGenerated
}

fun <T : Vertex<T>> T.depthFirstSearch(handle: (T) -> Unit){
    val handled = mutableListOf<T>()
    fun dfsInternal(start: T){
        start.edges.forEach { edge ->
            edge.destination.ifNotVisited {
                it.visit()
                handle(it)
                handled.add(it)
                dfsInternal(it)
            }
        }
    }
    dfsInternal(this)
    handled.forEach { it.leave() }
}

fun  <T : Vertex<T>> T.breadthFirstSearch(handle: (T) -> Unit){
    val handled = mutableListOf<T>()
    val toBeHandled: Queue<T> = LinkedList()
    toBeHandled.add(this)
    while (!toBeHandled.isEmpty()) {
        val current = toBeHandled.poll()
        current.edges.forEach { edge ->
            edge.destination.ifNotVisited{
                it.visit()
                handle(it)
                toBeHandled.add(it)
            }
        }
    }
    handled.forEach { it.leave() }
}

//debug
fun Vertex<*>.dump(): MutableMap<Vertex<*>, Set<Edge<*>>> {
    val map = mutableMapOf<Vertex<*>, Set<Edge<*>>>()
    fun constructMatrix(from: Vertex<*>){
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