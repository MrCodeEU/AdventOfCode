package Day15

import java.io.File
import java.util.Timer
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
        Pair(Pair(sX, sY), Pair(bX, bY))
    }

    val beacons = input.map { it.second }
    val sensors = input.map { it.first }
    val maxX = max(input.maxOf { it.first.second }, input.maxOf { it.second.second })
    val maxY = max(input.maxOf { it.first.first }, input.maxOf { it.second.first })
    val minX = min(input.minOf { it.first.second }, input.minOf { it.second.second })
    val minY = min(input.minOf { it.first.first }, input.minOf { it.second.first })
    val beaconDistances = mutableMapOf<Pair<Int, Int>, Int>()
    val sum = mutableSetOf<Pair<Int, Int>>()
    for (sensor in sensors) {
        //println("Checking Sensor ${i+1} / ${sensors.size}")
        // first calculate every Manhattan distance to beacon
        val allDistances = mutableMapOf<Pair<Int, Int>, Int>()
        for (beacon in beacons) {
            allDistances[beacon] = beacon.manhattanDistance(sensor)
        }
        beaconDistances[sensor] = allDistances.minOf { it.value }
    }
    for ((i, sensor) in sensors.withIndex()) {
        println("Checking Sensor ${i + 1} / ${sensors.size}")
        // second we try to check every sensor range of found beacon plus 1
        // this allows us to check every Point not in range of a sensor, and we do
        // this for every sensor and see if a Point of a sensor would be in range
        // of another sensor by checking if the Manhattan distance to that point from another
        // sensor is smaller than the distance of the beacon
        val outOfRange = manhattanCoordinates(sensor.first, sensor.second, beaconDistances.getOrDefault(sensor, 0) + 1)
        sum.addAll(outOfRange)
        for (c in outOfRange) {
            if (!(c.first in 0..4000000 && c.second in 0..4000000)) sum.remove(c)
            else for (s in sensors) {
                if (s.manhattanDistance(c) <= beaconDistances.getOrDefault(s, 0)) sum.remove(c)
            }
        }
    }
    sum.removeAll(beacons.toSet())
    sum.removeAll(sensors.toSet())
    println(sum)
    println(sum.first().first.toLong()*4000000L+sum.first().second.toLong() )
}

fun manhattanCoordinates(x1: Int, y1: Int, n: Int): Set<Pair<Int, Int>> {
    val coordinates = mutableSetOf<Pair<Int, Int>>()
    for (i in -n..n) {
        coordinates.add(Pair(x1 + i, y1 + n - abs(i)))
        coordinates.add(Pair(x1 + i, y1 - n + abs(i)))
    }
    return coordinates
}