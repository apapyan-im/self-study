package com.learn.im.graphs

import java.lang.Exception
import java.util.*


enum class State{
    VISITED,
    NOT_VISITED
}

class Vertex(val name: String) {
    var edges = mutableSetOf<Edge>()
    var previous: Vertex? = null
    var distance = Int.MAX_VALUE
    private var state: State = State.NOT_VISITED

    fun connect(vararg edges: Edge): Vertex {
        this.edges.addAll(edges.map(this::verifyEdge))
        return this
    }

    fun isConnectedWith(edge: Edge) = edges.contains(edge)

    fun ifVisited(execute: Vertex.() -> Unit){
        if(state == State.VISITED) execute()
    }

    fun ifNotVisited(execute: Vertex.() -> Unit){
        if(state == State.NOT_VISITED) execute()
    }

    fun visit(): Vertex{
        state = State.VISITED
        return this
    }
    fun leave(): Vertex {
        state = State.NOT_VISITED
        return this
    }

    private fun verifyEdge(edge: Edge):Edge{
        if(edge.weight < 0){
            throw Exception("Unsupported edge $name -> ${edge.destination.name} with invalid value ${edge.weight}")
        }
        return edge
    }

    override fun toString(): String {
        return name
    }
}

data class Edge(var destination: Vertex, var weight: Int = 1){
    override fun toString(): String {
        return "(${destination.name})"
    }

    override fun equals(other: Any?): Boolean {
        if(other !is Edge) return false
        return destination == other.destination
    }

    override fun hashCode(): Int {
        var result = destination.hashCode()
        result = 31 * result + weight
        return result
    }
}


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

fun Vertex.shortestPath(to: Vertex): List<Vertex> {
    distance = 0

    fun Vertex.fixDistances(){
        edges.forEach { edge ->
            val next = edge.destination
            val distance = distance + edge.weight
            if (distance < next.distance) {
                next.previous = this
                next.distance = distance
            }
        }
    }

    fixDistances()
    breadthFirstSearch {
        fixDistances()
    }

    val path = mutableListOf<Vertex>()
    var vertex: Vertex? = to
    while (vertex != null) {
        path.add(vertex)
        vertex = vertex.previous
    }
    // FIXME (MAKE ALL DISTANCES Int.MaxValue) HERE
    return path.reversed().also {
        if(path.last().name != this.name){
            throw Exception("Path is not found")
        }
    }
}

fun Vertex.depthFirstSearch(handle: Vertex.() -> Unit){
    val handled = mutableListOf<Vertex>()
    fun dfsInternal(start: Vertex){
        start.edges.forEach {
            it.destination.ifNotVisited {
                handle(visit())
                handled.add(this)
                dfsInternal(this)
            }
        }
    }
    dfsInternal(this)
    handled.forEach { it.leave() }
}

fun Vertex.breadthFirstSearch(handle: Vertex.() -> Unit){
    val handled = mutableListOf<Vertex>()
    val queue: Queue<Vertex> = LinkedList()
    queue.add(this)
    while (!queue.isEmpty()) {
        val current = queue.poll()
        handled.add(current)
        current.edges.forEach { edge ->
            edge.destination.ifNotVisited{
                queue.add(this)
                visit().handle()
            }
        }
    }
    handled.forEach { it.leave() }
}