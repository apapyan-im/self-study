package am.learn.im.map

import am.learn.im.map.Map.Companion.mapOf
import am.learn.im.support.checkRuntimeSpeedOf
import am.learn.im.support.empty
import am.learn.im.support.generate
import java.util.concurrent.TimeUnit

const val MAP_ELEMENTS_COUNT = 200000
val STRING_GENERATION_RANGE = (10..30)

fun main() {

    checkRuntimeSpeedOf("constructing custom map") {
        var key = String.empty
        var value = String.empty
        val cm = mapOf<String, String>().apply {
            (0..MAP_ELEMENTS_COUNT).map {
                key = String.generate(STRING_GENERATION_RANGE.random())
                value = String.generate(STRING_GENERATION_RANGE.random())
                this[key] = value
            }
        }
        checkRuntimeSpeedOf("checking if contains the key [${key}]", TimeUnit.NANOSECONDS){
            println("Value of $key is ${cm[key]} was put $value")
        }
    }

    println("--------------------------------------------")
    println("--------------------------------------------")
    println("--------------------------------------------")

    checkRuntimeSpeedOf("constructing default kotlin map") {
        var key = String.empty
        var value = String.empty
        val dm = mutableMapOf<String, String>().apply {
            (0..MAP_ELEMENTS_COUNT).map {
                key = String.generate(STRING_GENERATION_RANGE.random())
                value = String.generate(STRING_GENERATION_RANGE.random())
                this[key] = value
            }
        }
        checkRuntimeSpeedOf("checking if contains the key [${key}]", TimeUnit.NANOSECONDS){
            println("Value of $key is ${dm[key]} was put $value")
        }
    }



}