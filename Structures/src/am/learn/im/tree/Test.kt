package am.learn.im.tree

import am.learn.im.tree.Tree.Companion.treeOf

fun main() {

    val tree = treeOf( ((0..10).map { (0..25).random() }).toList() )

    tree.dump()

    println(55 in tree)
    println(5 in tree)
    println(555 in tree)

}