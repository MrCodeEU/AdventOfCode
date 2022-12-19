package Day14

import java.io.File
import kotlin.jvm.internal.Intrinsics.Kotlin
import kotlin.math.*

fun main() {
    val input = File("src/main/kotlin/Day14/input1.txt").readLines()
        .map {
            it.split(" -> ").map {
                Pair(it.split(",")[1].toInt(), it.split(",")[0].toInt())
            }
        }
    val maxX = input.flatten().maxOf { it.second } + 5
    val maxY = input.flatten().maxOf { it.first } + 5
    val minX = input.flatten().minOf { it.second } - 5
    val minY = input.flatten().minOf { it.first } - 5
    val grid = Array(maxY - minY + 1) {
        CharArray(maxX - minX + 1) { '.' }
    }
    for (line in input) {
        for (i in 1 until line.size) {
            val first = line[i - 1]
            val second = line[i]
            //println("$first -> $second")
            for (y in if (first.first < second.first) first.first..second.first else second.first..first.first) {
                for (x in if (first.second < second.second) first.second..second.second else second.second..first.second) {
                    //print("$y,$x; ")
                    grid[y - minY][x - minX] = '#'
                }
            }
            //println()
        }
    }

    val s = 500 - minX
    var fallenIntoAbyss = false
    var i = 0
    prettyPrint(grid)
    while (!fallenIntoAbyss) {
        try {
            var lastPos = Pair(0, s)
            var nextPos = Pair(0, s)
            while (true) {
                // do movement of sand grain
                if (grid[min(nextPos.first + 1, grid.size - 1)][max(nextPos.second, 0)] == '.') nextPos =
                    Pair(nextPos.first + 1, nextPos.second)
                else if (grid[min(nextPos.first + 1, grid.size - 1)][max(nextPos.second - 1, 0)] == '.') nextPos =
                    Pair(nextPos.first + 1, nextPos.second - 1)
                else if (grid[min(nextPos.first + 1, grid.size - 1)][max(nextPos.second + 1, 0)] == '.') nextPos =
                    Pair(nextPos.first + 1, nextPos.second + 1)
                //check if movement did not change position of new sand grain
                if (lastPos == nextPos) {
                    break
                }
                grid[lastPos.first][lastPos.second] = '.'
                lastPos = nextPos
                grid[nextPos.first][nextPos.second] = 'o'
            }
            i++
        } catch (e: Exception) {
            fallenIntoAbyss = true
        }
    }
    print(i)
}

fun prettyPrint(grid: Array<CharArray>) {
    for (x in grid) {
        for (y in x) {
            print(y)
        }
        println()
    }
}