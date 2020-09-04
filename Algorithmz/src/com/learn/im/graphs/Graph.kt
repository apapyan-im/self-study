package com.learn.im.graphs

import java.lang.RuntimeException

interface Vertex<T: Vertex<T>>: Visitable<T> {

    val label: String

    val edges: MutableSet<Edge<T>>

    fun addEdge(vararg edges: Edge<T>) = this.edges.addAll(edges)

    fun removeEdge(vararg edges: Edge<T>) = this.edges.removeAll(edges)

    operator fun contains(edge: Edge<T>) = this.edges.contains(edge)
}

data class Edge<T: Vertex<T>>(var destination: T, var weight: Int = 1)

class DijkstraVertex(override val label: String, override val edges: MutableSet<Edge<DijkstraVertex>> = mutableSetOf()): Vertex<DijkstraVertex> {

    override var state: State = State.NOT_VISITED

    var previous: DijkstraVertex? = null
    var distance = Int.MAX_VALUE

    fun resetDistance(){
        distance = Int.MAX_VALUE
        previous = null
    }

    private fun verifyEdge(edge: Edge<DijkstraVertex>) = edge.also {
        assert(edge.weight >= 0) {
            "Unsupported edge $label -> ${edge.destination.label} with invalid 'negative' value ${edge.weight}"
        }
    }

    override fun addEdge(vararg edges: Edge<DijkstraVertex>) = this.edges.addAll(edges.map(this::verifyEdge))

    override fun toString() = label
}

enum class State{
    VISITED,
    NOT_VISITED
}

interface Visitable<T: Visitable<T>>{
    var state: State

    fun ifNotVisited(execute: (T) -> Unit){
        if(state == State.NOT_VISITED) execute(get())
    }
    fun ifVisited(execute: (T) -> Unit){
        if(state != State.NOT_VISITED) execute(get())
    }

    fun visit() { state = State.VISITED }
    fun leave() { state = State.NOT_VISITED }

    private fun get() = this as T;
}

class PathIsNotFoundException(from: Vertex<*>, to: Vertex<*>): RuntimeException("Path from ${from.label} to ${to.label} is not found")