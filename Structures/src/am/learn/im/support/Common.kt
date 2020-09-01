package am.learn.im.support

import java.time.Duration
import java.util.concurrent.TimeUnit


fun checkRuntimeSpeedOf(functionName: String, timeUnit: TimeUnit = TimeUnit.MILLISECONDS, function: () -> Unit) {
    val start = System.nanoTime()
    function()
    val end = System.nanoTime()
    println("During ($functionName) we have spent [${timeUnit.convert(Duration.ofNanos(end - start))}] ${timeUnit.name}")
}

enum class Case(val ASCIRange: CharRange) {
    LOWER('a'..'z'), UPPER('A'..'Z')
}

fun String.Companion.generate(length: Int, case: Case = Case.UPPER): String {
    var res = ""
    repeat(length) { res += (case.ASCIRange).random() }
    return res
}

val String.Companion.empty get() = ""

fun range(from: Int, to: Int, step: Int = 1): IntProgression {
    return (from until to step step)
}