package am.learn.im.map

import am.learn.im.map.Map.Companion.mapOf
import am.learn.im.support.checkRuntimeSpeedOf
import java.util.concurrent.TimeUnit

fun main() {

    checkRuntimeSpeedOf("constructing custom map") {
        var key = ""
        var value = 0
        val cm = mapOf<String, Int>().apply {
            (0..200000).map {
                key = "K" + (0..200000).random()
                value = (0..200000).random()
                this[key] = value
            }
        }
        checkRuntimeSpeedOf("checking if contains the key [${key}]", TimeUnit.NANOSECONDS){
            println("Value of ${key} is ${cm[key]} was put $value")
        }
    }

    println("--------------------------------------------")
    println("--------------------------------------------")
    println("--------------------------------------------")

    checkRuntimeSpeedOf("constructing default kotlin map") {
        var key = ""
        var value = 0
        val dm = mutableMapOf<String, Int>().apply {
            (0..200000).map {
                key = "K" + (0..200000).random()
                value = (0..200000).random()
                this[key] = value
            }
        }
        checkRuntimeSpeedOf("checking if contains the key [${key}]", TimeUnit.NANOSECONDS){
            println("Value of ${key} is ${dm[key]} was put $value")
        }
    }



}