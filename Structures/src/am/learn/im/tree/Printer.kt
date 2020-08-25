package am.learn.im.tree

import java.util.*
import kotlin.math.max
import kotlin.math.pow

internal object Printer {
    fun <T: Comparable<T>> printNode(root: Node<T>?) {
        val maxLevel = maxLevel(root)
        printNodeInternal(listOf(root), 1, maxLevel)
    }

    private fun <T: Comparable<T>> printNodeInternal(nodes: List<Node<T>?>, level: Int, maxLevel: Int) {
        if (nodes.isEmpty() || areAllElementsNull(nodes)) return
        val floor = maxLevel - level
        val edgeLines = 2.0.pow(max(floor - 1, 0).toDouble()).toInt()
        val firstSpaces = 2.0.pow(floor.toDouble()).toInt() - 1
        val betweenSpaces = 2.0.pow((floor + 1).toDouble()).toInt() - 1
        printWhitespaces(firstSpaces)
        val newNodes: MutableList<Node<T>?> = ArrayList()
        for (node in nodes) {
            if (node != null) {
                print(node.value)
                newNodes.add(node.left)
                newNodes.add(node.right)
            } else {
                newNodes.add(null)
                newNodes.add(null)
                print(" ")
            }
            printWhitespaces(betweenSpaces)
        }
        println("")
        for (i in 1..edgeLines) {
            for (j in nodes.indices) {
                printWhitespaces(firstSpaces - i)
                if (nodes[j] == null) {
                    printWhitespaces(edgeLines + edgeLines + i + 1)
                    continue
                }
                if (nodes[j]!!.left != null) print("/") else printWhitespaces(1)
                printWhitespaces(i + i - 1)
                if (nodes[j]!!.right != null) print("\\") else printWhitespaces(1)
                printWhitespaces(edgeLines + edgeLines - i)
            }
            println("")
        }
        printNodeInternal(newNodes, level + 1, maxLevel)
    }

    private fun printWhitespaces(count: Int) {
        for (i in 0 until count) print(" ")
    }

    private fun <T: Comparable<T>> maxLevel(node: Node<T>?): Int {
        return if (node == null) 0 else maxLevel(node.left).coerceAtLeast(maxLevel(node.right)) + 1
    }

    private fun <T> areAllElementsNull(list: List<T>): Boolean {
        for (element in list) {
            if (element != null) return false
        }
        return true
    }
}