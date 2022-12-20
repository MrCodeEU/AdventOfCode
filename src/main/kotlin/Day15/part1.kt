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
    for ((i,sensor) in sensors.withIndex()) {
        println("Checking Sensor ${i+1} / ${sensors.size}")
        // first calculate every Manhattan distance to beacon
        val beaconDistances = mutableMapOf<Pair<Int, Int>, Int>()
        for (beacon in beacons) {
            beaconDistances[beacon] = beacon.manhattanDistance(sensor)
        }
        // second get all coords in range of this manhattan distance
        // and get the smallest and biggest coords in row 2000000
        // if there exists coords  add range to set of sum
        val coords = manhattanCoordinatesForX(sensor.first, sensor.second, beaconDistances.minOf { it.value })
        //println("\t$coords")
        sum += coords
        /*val range = coords.filter { it.first == 2000000 }
        if (range.size == 1) {
            sum.add(range[0])
        } else if (range.size == 2) {
            sum.addAll((range[0]..range[1]).filter { it.second in (minY-1) until maxY })
        }*/
    }
    sum.removeAll(beacons.toSet())
    sum.addAll(sensors.filter { it.first == 2000000 })
    print(sum.size)
}

operator fun Pair<Int, Int>.rangeTo(pair: Pair<Int, Int>): Collection<Pair<Int, Int>> {
    val list = mutableListOf<Pair<Int, Int>>()
    for (x in if (this.first < pair.first) this.first..pair.first else pair.first..this.first) {
        for (y in if (this.second < pair.second) this.second..pair.second else pair.second..this.second) {
            list.add(Pair(x, y))
        }
    }
    return list
}

fun Pair<Int, Int>.manhattanDistance(point2: Pair<Int, Int>): Int {
    val xDistance = abs(this.first - point2.first)
    val yDistance = abs(this.second - point2.second)
    return xDistance + yDistance
}

fun manhattanCoordinatesForX(x1: Int, y1: Int, n: Int, x: Int = 2000000): Set<Pair<Int, Int>> {
    val coordinates = mutableSetOf<Pair<Int, Int>>()

    // Calculate the possible y values for the given x value
    val yLower = y1 - n + abs(x - x1)
    val yUpper = y1 + n - abs(x - x1)
    for (y in yLower..yUpper) {
        coordinates.add(Pair(x, y))
    }

    return coordinates
}
