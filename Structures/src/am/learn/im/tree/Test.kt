package am.learn.im.tree

import am.learn.im.tree.Tree.Companion.treeOf

fun main() {
    val tree = treeOf((0..50).toList())

    tree.dump()

    println(55 in tree)
    println(5 in tree)
    println(555 in tree)

}