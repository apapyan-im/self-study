package am.learn.im.tree

// TODO Generify <V : Comparable<V>>
class Tree <T: Comparable<T>> {

    private var root: Node<T>? = null

    fun insert(value: T) {
        root = insert(root, Node(value))
    }

    operator fun contains(value: T): Boolean {
        return contains(root, value)
    }

    fun delete(value: Int) {
        TODO("When nerever budet vparyadke")
    }

    private fun insert(node: Node<T>?, newNode: Node<T>): Node<T> {
        when {
            node == null -> return newNode
            node.value < newNode.value -> {
                node.left = insert(node.left, newNode)
            }
            node.value > newNode.value -> {
                node.right = insert(node.right, newNode)
            }
            else -> {
                node.value = newNode.value
            }
        }
        return node.balance()
    }

    private fun contains(node: Node<T>?, value: T): Boolean{
        if(node == null){
            return false
        }
        return when {
            node.value < value -> contains(node.left, value)
            node.value > value -> contains(node.right, value)
            else -> true
        }
    }

    fun dump(){
        Printer.printNode(root)
    }

    companion object {
        fun <T: Comparable<T>> treeOf(elements: List<T>): Tree<T> = Tree<T>().apply { elements.forEach { insert(it) } }
    }

}