package am.learn.im.tree

// TODO Generify <V : Comparable<V>>
class Tree <T: Comparable<T>> {

    private var root: Node<T>? = null

    val height get() = heightOf(root)

    fun insert(value: T) {
        root = insert(root, Node(value))
    }

    operator fun contains(value: T): Boolean {
        return contains(root, value)
    }

    private fun insert(node: Node<T>?, newNode: Node<T>): Node<T> {
        when {
            node == null -> return newNode
            node.value < newNode.value -> node.left = insert(node.left, newNode)
            node.value > newNode.value -> node.right = insert(node.right, newNode)
            else -> node.value = newNode.value
        }
        return node.balance()
    }

    private fun contains(node: Node<T>?, value: T): Boolean = when {
            node == null -> false
            node.value < value -> contains(node.left, value)
            node.value > value -> contains(node.right, value)
            else -> true
        }


    private fun heightOf(node: Node<T>?): Int {
        fun heightInternal(node: Node<T>?): Int {
            return when {
                node == null -> 0
                node.left != null -> 1 + heightOf(node.left)
                node.right != null -> 1 + heightOf(node.right)
                else -> 1
            }
        }

        return maxOf(heightInternal(node?.left), heightInternal(node?.right)) + 1
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