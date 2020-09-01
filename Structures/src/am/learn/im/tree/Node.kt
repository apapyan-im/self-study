package am.learn.im.tree

class Node<T: Comparable<T>> (var value: T) {

    var height = 1

    var left: Node<T>? = null

    var right: Node<T>? = null

    private var parent: Node<T>? = null

    private val balanceFactor get() = (left?.height ?: 0) - (right?.height ?: 0)

    private fun rotateRight(): Node<T> {
        val node = left
        left = node!!.right
        node.right = this
        node.parent = parent
        fixHeight()
        node.fixHeight()
        return node
    }

    private fun rotateLeft(): Node<T> {
        val node = right
        right = node!!.left
        node.left = this
        node.parent = parent
        fixHeight()
        node.fixHeight()
        return node
    }

    private fun fixHeight() {
        height = 1 + (left?.height ?: 0) + (right?.height ?: 0)
    }

    fun balance(): Node<T> {
        fixHeight()
        if (balanceFactor > 1) {
            if (left!!.balanceFactor < 0) {
                left = left!!.rotateLeft()
            }
            return rotateRight()
        } else if (balanceFactor < -1) {
            if (right!!.balanceFactor > 0) {
                right = right!!.rotateRight()
            }
            return rotateLeft()
        }
        return this
    }
}