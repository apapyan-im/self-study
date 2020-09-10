package am.learn.im.map

import kotlin.math.abs

data class Entry<K : Any, V : Any>(var key: K, var value: V)

class Map<K : Any, V : Any> {

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

    fun size(): Int = size + (collisions?.size ?: 0)

    private val nextLoadFactor: Double
        get() = ((size + 1) / map.size).toDouble()

    operator fun set(key: K, value: V): Boolean {
        return set(Entry(key, value), hashOf(key))
    }

    private fun hashOf(key: K) = abs(key.hashCode())

    private fun set(newEntry: Entry<K, V>, hash: Int): Boolean {
        if (nextLoadFactor > LOAD_FACTOR) resize()
        val position = positionOf(newEntry.key, hash)
        val entry = map[position]
        return when {
            entry == null -> {
                map[position] = newEntry.also {
                    size++
                }; true
            }
            entry.key == newEntry.key -> {
                entry.value = newEntry.value; false
            }
            else -> {
                settleCollision(newEntry, hash)
            }
        }
    }

    private fun settleCollision(entry: Entry<K, V>, hash: Int): Boolean {
        if (collisions == null) {
            collisions = Map()
        }
        return collisions!!.set(entry, hash)
    }

    operator fun get(key: K): V? {
        val position = positionOf(key, hashOf(key))
        val entry = map[position]
        return when {
            entry == null -> null
            entry.key == key -> entry.value
            else -> collisions?.get(key)
        }
    }

    fun remove(key: K): V? {
        val position = positionOf(key, hashOf(key))
        val entry = map[position]
        return when {
            entry == null -> null
            entry.key == key -> entry.value.also {
                map[position] = null
            }
            else -> collisions?.remove(key)
        }
    }

    operator fun contains(key: K): Boolean {
        return get(key) != null
    }

    private fun positionOf(key: K, hash: Int): Int {
        val index = hash % map.size
        var newIndex = index
        var entry: Entry<K, V>? = map[index]
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
        map = arrayOfNulls<Entry<K, V>?>((this.map.size * EXTENSIBILITY_FACTOR).toInt()).also { size = 0 }
        var entry:Entry<K, V>?
        for (i in old.indices) {
            entry = old[i]
            if (entry != null) {
                set(entry.key, entry.value)
            }
        }
    }

    companion object {
        const val DEFAULT_SIZE = 1000
        const val LOAD_FACTOR = 0.8
        const val EXTENSIBILITY_FACTOR = 2

        fun <K : Any, V : Any> mapOf(pairs: List<Pair<K, V>> = listOf()): Map<K, V> {
            return Map<K, V>().apply {
                pairs.forEach { (key, value) ->
                    this[key] = value
                }
            }
        }
    }
}