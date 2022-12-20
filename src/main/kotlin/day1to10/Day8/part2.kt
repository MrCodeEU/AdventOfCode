package day1to10.Day8

import java.io.File

fun main() {
    val grid = MutableList(0) { MutableList(0) { 0 } }
    File("src/main/kotlin/Day8/input1.txt").readLines().forEach { line ->
        grid.add(line.toCharArray().map { it.toString().toInt() }.toMutableList())
    }
    val sceneScore = MutableList(grid.size) { MutableList(grid[0].size) { 0 } }
    //always visible
    var vis = grid.size * 2 + grid[0].size * 2 - 4
    for (x in 1 until (grid.size-1)) {
        for (y in 1 until (grid[x].size-1)) {
            var isVisL = 0
            var isVisR = 0
            var isVisU = 0
            var isVisD = 0
            val height = grid[x][y]
            for (i in (x + 1) until grid.size){
                isVisD++
                if (grid[i][y] >= height) break
            }
            for (i in (y + 1) until grid.size){
                isVisR++
                if (grid[x][i] >= height) break
            }
            for (i in (x - 1) downTo 0){
                isVisU++
                if (grid[i][y] >= height) break
            }
            for (i in (y - 1) downTo 0){
                isVisL++
                if (grid[x][i] >= height) break
            }
            sceneScore[x][y] = isVisD * isVisL * isVisR * isVisU
        }
    }

    println(sceneScore.flatten().max())
}