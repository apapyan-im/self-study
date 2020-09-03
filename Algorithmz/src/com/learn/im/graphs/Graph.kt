package com.learn.im.graphs

import java.lang.RuntimeException


class Vertex(val label: String): Visitable<Vertex>() {
    val edges = mutableSetOf<Edge>()
    var previous: Vertex? = null
    var distance = Int.MAX_VALUE

    operator fun contains(edge: Edge) = edges.contains(edge)

    fun addEdge(vararg edges: Edge) = this.also {
        this.edges.addAll(edges.map(this::verifyEdge))
    }

    fun resetDistance(){
        distance = Int.MAX_VALUE
        previous = null
    }

    private fun verifyEdge(edge: Edge):Edge{
        assert(edge.weight >= 0) {
            "Unsupported edge $label -> ${edge.destination.label} with invalid 'negative' value ${edge.weight}"
        }
        return edge
    }

    override fun toString() = label
}

data class Edge(var destination: Vertex, var weight: Int = 1){

    override fun equals(other: Any?)
            = if(other !is Edge)  false else destination == other.destination

    override fun hashCode() =  31 * destination.hashCode() + weight

    override fun toString() = "(${destination.label})"
}

enum class State{
    VISITED,
    NOT_VISITED
}

abstract class Visitable<T: Visitable<T>>{
    private var state: State  = State.NOT_VISITED

    fun ifNotVisited(execute: (T) -> Unit){
        if(state == State.NOT_VISITED) execute(get())
    }
    fun ifVisited(execute: (T) -> Unit){
        if(state != State.NOT_VISITED) execute(get())
    }

    fun visit() = get().also { state = State.VISITED }
    fun leave() = get().also { state = State.NOT_VISITED }

    private fun get() = this as T;
}

class PathIsNotFoundException(from: Vertex, to: Vertex): RuntimeException("Path from ${from.label} to ${to.label} is not found")