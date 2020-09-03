package am.learn.im.map

import kotlin.math.abs

data class Entry<K : Any, V : Any>(var key: K, var value: V)

class Map<K : Any, V : Any> {

    val uid = Math.random()

    var map: Array<Entry<K, V>?> = arrayOfNulls(DEFAULT_SIZE)

    private var collisions: Map<K, V>? = null

    private var size = 0

    val values
        get(): List<V> = map.mapNotNull {
            it?.value
        } + (collisions?.values ?: listOf())

    val keys
        get(): List<K> = map.mapNotNull {
            it?.key
        } + (collisions?.keys ?: listOf())

    fun size(): Int = size + (collisions?.size() ?: 0)

    fun arraySize(): Int = map.size + (collisions?.arraySize() ?: 0)

    private val nextLoadFactor: Double
        get() = ((size + 1) / map.size).toDouble()

    operator fun set(key: K, value: V): Boolean {
        if (nextLoadFactor > LOAD_FACTOR) resize()
        val position: Int = positionOf(key)
        val entry = map[position]
        return when {
            entry == null -> {
                map[position] = Entry(key, value).also { size++ }; true
            }
            entry.key == key -> {
                entry.value = value; false
            }
            else -> {
                settleCollision(key, value)
            }
        }
    }

    private fun settleCollision(key: K, value: V): Boolean {
        if (collisions == null) {
            collisions = Map()
        }
        return collisions!!.set(key, value)
    }

    operator fun get(key: K): V? {
        val position = positionOf(key)
        val entry = map[position]
        return when {
            entry == null -> null
            entry.key == key -> entry.value
            else -> collisions?.get(key)
        }
    }

    fun delete(key: K): V? {
        val position = positionOf(key)
        val entry = map[position]
        return when {
            entry == null -> null
            entry.key == key -> entry.value.also { map[position] = null }
            else -> collisions?.delete(key)
        }
    }

    operator fun contains(key: K): Boolean {
        return get(key) != null
    }

    private fun positionOf(key: K): Int {
        val index = abs(key.hashCode()) % map.size
        var newIndex = index
        var entry: Entry<K, V>? = map[index] ?: return index
        for (i in index until map.size) {
            entry = map[i]
            if (entry?.key != key) {
                newIndex = i; break
            }
        }
        return if (entry?.key != key) index else newIndex
    }

    private fun resize() {
        val old = map
        map = arrayOfNulls<Entry<K, V>?>((old.size * EXTENSIBILITY_FACTOR).toInt()).also { size = 0 }
        for (i in old.indices) {
            if (map[i] != null) {
                map[i] = old[i]
            }
        }
    }

    companion object {
        const val DEFAULT_SIZE = 10
        const val LOAD_FACTOR = 0.77
        const val EXTENSIBILITY_FACTOR = 1.2

        fun <K : Any, V : Any> mapOf(pairs: List<Pair<K, V>> = listOf()): Map<K, V> {
            return Map<K, V>().apply {
                pairs.forEach { (key, value) ->
                    this[key] = value
                }
            }
        }
    }
}