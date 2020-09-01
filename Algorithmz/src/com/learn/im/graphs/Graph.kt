package com.learn.im.graphs

import java.lang.Exception
import java.lang.RuntimeException


enum class State{
    VISITED,
    NOT_VISITED
}

class Vertex(val name: String) {
    val edges = mutableSetOf<Edge>()
    var previous: Vertex? = null
    var distance = Int.MAX_VALUE

    private var state: State = State.NOT_VISITED

    fun connect(vararg edges: Edge) = this.also {
        this.edges.addAll(edges.map(this::verifyEdge))
    }

    fun isConnectedWith(edge: Edge) = edges.contains(edge)

    fun ifNotVisited(execute: (Vertex) -> Unit){
        if(state == State.NOT_VISITED) execute(this)
    }

    fun visit() = this.also { state = State.VISITED }
    fun leave() = this.also { state = State.NOT_VISITED }

    fun resetDistance(){
        distance = Int.MAX_VALUE
        previous = null
    }

    private fun verifyEdge(edge: Edge):Edge{
        assert(edge.weight >= 0) {
            "Unsupported edge $name -> ${edge.destination.name} with invalid value ${edge.weight}"
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

class PathIsNotFoundException(from: Vertex, to: Vertex): RuntimeException("Path from ${from.name} to ${to.name} is not found")