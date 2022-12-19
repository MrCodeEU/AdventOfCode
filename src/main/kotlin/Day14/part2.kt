package Day14

import java.io.File
import kotlin.math.*

fun main() {
    val input = File("src/main/kotlin/Day14/input1.txt").readLines()
        .map {
            it.split(" -> ").map {
                Pair(it.split(",")[1].toInt(), it.split(",")[0].toInt())
            }
        }
    val maxX = input.flatten().maxOf { it.second } + 5000
    val maxY = input.flatten().maxOf { it.first } + 2
    val minX = input.flatten().minOf { it.second } - 5000
    val minY = input.flatten().minOf { it.first } - 20
    val grid = Array(maxY - minY + 1) {
        CharArray(maxX - minX + 1) { '.' }
    }
    grid[grid.size-1] = grid[grid.size-1].map { '#' }.toCharArray()
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
    var notAtStart = false
    var i = 0
    grid[0-minY][s] = 'S'
    //prettyPrint(grid)
    start@ while (!notAtStart) {
        try {
            //prettyPrint(grid)
            var lastPos = Pair(0-minY, s)
            var nextPos = Pair(0-minY, s)
            while (true) {
                // do movement of sand grain
                if (grid[min(nextPos.first + 1, grid.size - 1)][max(nextPos.second, 0)] == '.') nextPos =
                    Pair(nextPos.first + 1, nextPos.second)
                else if (grid[min(nextPos.first + 1, grid.size - 1)][max(nextPos.second - 1, 0)] == '.') nextPos =
                    Pair(nextPos.first + 1, nextPos.second - 1)
                else if (grid[min(nextPos.first + 1, grid.size - 1)][max(nextPos.second + 1, 0)] == '.') nextPos =
                    Pair(nextPos.first + 1, nextPos.second + 1)
                //check if movement did not change position of new sand grain
                if (lastPos == nextPos){
                    if (nextPos == Pair(0-minY,s)){
                        i++
                        break@start
                    }
                    break
                }
                grid[lastPos.first][lastPos.second] = '.'
                lastPos = nextPos
                grid[nextPos.first][nextPos.second] = 'o'
            }
            i++
        } catch (e: Exception){
            notAtStart = true
        }
    }
    print(i)
}