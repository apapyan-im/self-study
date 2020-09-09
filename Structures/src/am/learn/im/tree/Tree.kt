package am.learn.im.tree

// TODO Generify <V : Comparable<V>>
class Tree <T: Comparable<T>> {

    private var root: Node<T>? = null

    val height get() = height(root)

    private fun height(node: Node<T>?):Int = when (node) {
        null -> 0
        else -> maxOf(height(node.left), height(node.right)) + 1
    }

    fun insert(value: T) {
        fun insert(node: Node<T>?, newNode: Node<T>): Node<T> {
            when {
                node == null -> return newNode
                node.value < newNode.value -> node.left = insert(node.left, newNode)
                node.value > newNode.value -> node.right = insert(node.right, newNode)
                else -> node.value = newNode.value
            }
            return node.balance()
        }

        root = insert(root, Node(value))
    }

    operator fun contains(value: T): Boolean {
        fun contains(node: Node<T>?, value: T): Boolean = when {
            node == null -> false
            node.value < value -> contains(node.left, value)
            node.value > value -> contains(node.right, value)
            else -> true
        }

        return contains(root, value)
    }

    fun dump() = Printer.printNode(root)

    fun delete(value: Int) {
        TODO("When nerever budet vparyadke")
        // find node
        // if has no child - remove
        // if has 1 child - replace child with node
        // if has 2 child - get leftest of right replace with node
        // done
    }

    companion object {
        fun <T: Comparable<T>> treeOf(elements: List<T>): Tree<T> = Tree<T>().apply { elements.forEach { insert(it) } }
    }

}