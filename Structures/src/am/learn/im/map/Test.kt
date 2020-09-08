package am.learn.im.map

import am.learn.im.map.Map.Companion.mapOf
import am.learn.im.support.checkRuntimeSpeedOf
import am.learn.im.support.empty
import am.learn.im.support.generate
import java.util.concurrent.TimeUnit

const val MAP_ELEMENTS_COUNT = 200000
val STRING_GENERATION_RANGE = 10..20

fun main() {

    val elements = (0..MAP_ELEMENTS_COUNT).map {Pair(String.generate(STRING_GENERATION_RANGE.random()), String.generate(STRING_GENERATION_RANGE.random()))}
    var key = String.empty
    var value = String.empty
    val notExistentKey = String.generate(STRING_GENERATION_RANGE.random())

    val customMap = mapOf<String, String>()
    val defaultMap = mutableMapOf<String, String>()



    checkRuntimeSpeedOf("constructing custom map") {
        elements.forEach {(k, v) ->
            customMap[k] = v; key = k; value = v;
        }
    }
    checkRuntimeSpeedOf("checking if contains the key [${key}]", TimeUnit.NANOSECONDS){
        key in customMap
    }
    checkRuntimeSpeedOf("checking if not contains the key [${notExistentKey}]", TimeUnit.NANOSECONDS){
        notExistentKey in customMap
    }

    println(customMap.arraySize())

    println("--------------------------------------------")
    println("--------------------------------------------")
    println("--------------------------------------------")

    checkRuntimeSpeedOf("constructing default kotlin map") {
        elements.forEach {(k, v) ->
            defaultMap[k] = v; key = k; value = v;
        }
    }
    checkRuntimeSpeedOf("checking if contains the key [${key}]", TimeUnit.NANOSECONDS){
        key in defaultMap
    }
    checkRuntimeSpeedOf("checking if not contains the key [${notExistentKey}]", TimeUnit.NANOSECONDS){
        notExistentKey in defaultMap
    }



}