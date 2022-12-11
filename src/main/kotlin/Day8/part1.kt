package Day8

import java.io.File

fun main() {
    val grid = MutableList(0) { MutableList(0) { 0 } }
    File("src/main/kotlin/Day8/input1.txt").readLines().forEach { line ->
        grid.add(line.toCharArray().map { it.toString().toInt() }.toMutableList())
    }
    //always visible
    var vis = grid.size * 2 + grid[0].size * 2 - 4
    for (x in 1 until (grid.size-1)) {
        for (y in 1 until (grid[x].size-1)) {
            var isVisL = true
            var isVisR = true
            var isVisU = true
            var isVisD = true
            val height = grid[x][y]
            for (i in (x + 1) until grid.size) if (grid[i][y] >= height) isVisD = false
            for (i in (y + 1) until grid.size) if (grid[x][i] >= height) isVisR = false
            for (i in (x - 1) downTo 0) if (grid[i][y] >= height) isVisU = false
            for (i in (y - 1) downTo 0) if (grid[x][i] >= height) isVisL = false
            if (isVisD || isVisL || isVisR || isVisU){
                vis++
                println("gris[$x][$y]: ${grid[x][y]}")
            }
        }
    }

    println(vis)
}