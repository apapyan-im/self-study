package am.learn.im.tree

import am.learn.im.support.checkRuntimeSpeedOf
import am.learn.im.tree.Tree.Companion.treeOf

fun main() {

    checkRuntimeSpeedOf("constructing tree"){
        val tree = treeOf((0..50000).toList())

//    tree.dump()

        println(55 in tree)
        println(5 in tree)
        println(555 in tree)

        println(tree.height)
    }


}