package am.learn.im.map

import am.learn.im.map.Map.Companion.mapOf
import am.learn.im.support.checkRuntimeSpeedOf
import java.util.concurrent.TimeUnit

fun main() {

    checkRuntimeSpeedOf("CUSTOM MAP") {
        var key = ""
        val cm = mapOf<String, Int>().apply {
            (0..200000).map {
                key = "K" + (Math.random())
                val value = (Math.random()).toInt()
                this[key] = value
            }
        }
        checkRuntimeSpeedOf("CONTAINS ${key}", TimeUnit.NANOSECONDS){
            println(key in cm)
        }
    }

    println("--------------------------------------------")
    println("--------------------------------------------")
    println("--------------------------------------------")

    checkRuntimeSpeedOf("DEFAULT MAP") {
        var key = ""
        val dm = mutableMapOf<String, Int>().apply {
            (0..200000).map {
                key = "K" + (Math.random())
                val value = (Math.random()).toInt()
                this[key] = value
            }
        }
        checkRuntimeSpeedOf("CONTAINS ${key}", TimeUnit.NANOSECONDS){
            println(key in dm)
        }
    }



}