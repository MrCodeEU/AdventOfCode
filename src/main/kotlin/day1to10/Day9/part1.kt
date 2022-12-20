package day1to10.Day9

import java.io.File

fun main() {
    val visited = mutableSetOf<Pair<Int,Int>>()
    var H = Pair(250, 250)
    var T = Pair(250, 250)
    val s = Pair(250, 250)
    visited.add(s)
    val directions = listOf(
        Pair(-1, -1),
        Pair(-1, 0),
        Pair(-1, 1),
        Pair(0, 1),
        Pair(1, 1),
        Pair(1, 0),
        Pair(1, -1),
        Pair(0, -1)
    )
    val moves = File("src/main/kotlin/Day9/input1.txt").readLines().map {
        val parts = it.split(" ")
        Pair(parts[0], parts[1].toInt())
    }
    for (move in moves) {
        //move H
        for (i in 0 until move.second) {
            // move H
            when (move.first) {
                "L" -> H -= Pair(0, 1)
                "R" -> H += Pair(0,1)
                "U" -> H -= Pair(1,0)
                "D" -> H += Pair(1,0)
                else -> error("${move.first} is not a expected input")
            }
            println()
            println("H is at: $H")
            println("T is at: $T")
            //Move T if necessary
            if(!directions.contains(H-T)){
                val dif = T - H
                when(dif){
                    Pair(0,2) -> T -= Pair(0,1)
                    Pair(2,0) -> T -= Pair(1,0)
                    Pair(0,-2) -> T -= Pair(0,-1)
                    Pair(-2,0) -> T -= Pair(-1,0)
                    Pair(1,2) -> T -= Pair(1,1)
                    Pair(2,1) -> T -= Pair(1,1)
                    Pair(1,-2) -> T -= Pair(1,-1)
                    Pair(-2,1) -> T -= Pair(-1,1)
                    Pair(-1,2) -> T -= Pair(-1,1)
                    Pair(2,-1) -> T -= Pair(1,-1)
                    Pair(-1,-2) -> T -= Pair(-1,-1)
                    Pair(-2,-1) -> T -= Pair(-1,-1)
                }
                println("T moved to $T for a dif of $dif")
                visited.add(T)
            }
            println()
        }
    }
    println(visited.size)
}

fun printGrid(grid: MutableList<MutableList<Char>>, h: Pair<Int, Int>, t: Pair<Int, Int>, s: Pair<Int, Int>) {
    for (i in 0 until grid.size) {
        for (j in 0 until grid[0].size) {
            when (Pair(i, j)) {
                h -> print('H')
                t -> print('T')
                s -> print('s')
                else ->{
                    if(grid[i][j] == '.') print('.')
                    else print('#')
                }
            }
        }
        println()
    }
    println()
}

operator fun Pair<Int, Int>.minus(o: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first - o.first, this.second - o.second)
}

operator fun Pair<Int, Int>.plus(o: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first + o.first, this.second + o.second)
}

