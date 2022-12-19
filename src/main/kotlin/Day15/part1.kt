package Day15

import java.io.File
import kotlin.math.*
import kotlin.system.exitProcess

fun main() {
    val input = File("src/main/kotlin/Day15/input1.txt").readLines().map {
        it.split("=")
    }.map {
        val sX = it[1].split(",")[0].toInt()
        val sY = it[2].split(":")[0].toInt()
        val bX = it[3].split(",")[0].toInt()
        val bY = it[4].toInt()
        Pair(Pair(sY, sX), Pair(bY, bX))
    }

    val beacons = input.map { it.second }
    val sensors = input.map { it.first }
    val maxX = max(input.maxOf { it.first.first }, input.maxOf { it.second.first })
    val maxY = max(input.maxOf { it.first.second }, input.maxOf { it.second.second })
    val minX = min(input.minOf { it.first.first }, input.minOf { it.second.first })
    val minY = min(input.minOf { it.first.second }, input.minOf { it.second.second })
    val sum = mutableSetOf<Pair<Int, Int>>()
    for ((i, sensor) in sensors.withIndex()) {
        println("Sensor ${i+1} / ${sensors.size}")
        if (sensor.first == 2000000) sum.add(sensor)
        var notFound = true
        var n = 1
        while (notFound) {
            println("\tDistance: $n")
            // distance = |x1-x2|+|y1-y1|
            // idea set distance to 1 check all possible x2y2 from x1y1 Sensor
            // if no B found increase distance by 1
            // idea is studip way to slow. change to getting the distance of every beacon
            for (c in manhattanCoordinates(sensor.first, sensor.second, n)) {
                if (beacons.contains(c)) {
                    if (c.first == 2000000) sum.add(c)
                    notFound = false
                    break
                } else {
                    if (c.second in minY..maxY && c.first == 2000000) sum.add(c)
                }
            }
            n++
        }
    }
    println(sum.size)
}

fun manhattanCoordinates(x1: Int, y1: Int, n: Int): Set<Pair<Int, Int>> {
    val coordinates = mutableSetOf<Pair<Int, Int>>()
    for (i in -n..n) {
        coordinates.add(Pair(x1 + i, y1 + n - abs(i)))
        coordinates.add(Pair(x1 + i, y1 - n + abs(i)))
    }
    return coordinates
}