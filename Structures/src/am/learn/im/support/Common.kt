package am.learn.im.support

import java.time.Duration
import java.util.concurrent.TimeUnit


fun checkRuntimeSpeedOf(functionName: String, timeUnit: TimeUnit = TimeUnit.MILLISECONDS, function: () -> Unit) {
    val start = System.nanoTime()
    function()
    val end = System.nanoTime()
    println("To execute ($functionName) we have spent [${timeUnit.convert(Duration.ofNanos(end - start))}] ${timeUnit.name}")
}