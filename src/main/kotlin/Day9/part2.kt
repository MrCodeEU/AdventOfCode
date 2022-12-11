package Day9

import java.io.File
import kotlin.math.abs
import kotlin.math.sign

fun main() {
    val visited = mutableSetOf<Pair<Int, Int>>()
    val s = Pair(250, 250)
    val rope = MutableList(10) { Pair(250, 250) }
    visited.add(s)
    val directions = listOf(
        Pair(-1, -1),
        Pair(-1, 0),
        Pair(-1, 1),
        Pair(0, 1),
        Pair(1, 1),
        Pair(1, 0),
        Pair(1, -1),
        Pair(0, -1),
        Pair(0,0)
    )
    val moves = File("src/main/kotlin/Day9/input1.txt").readLines().map {
        val parts = it.split(" ")
        Pair(parts[0], parts[1].toInt())
    }
    for (move in moves) {
        //move H
        for (i in 0 until move.second) {
            // move H
            rope[0] -= when (move.first) {
                "L" -> Pair(0, 1)
                "R" -> Pair(0, -1)
                "U" -> Pair(1, 0)
                "D" -> Pair(-1, 0)
                else -> error("${move.first} is not a expected input")
            }
            println()
            println("H is at: ${rope[0]}")
            //Move each rope element in relation to the previous one if necessary
            for (i in 1..9) {
                var H = rope[i - 1]
                var T = rope[i]
                if (!directions.contains(H - T)) {
                    val dif = T - H
                    if(abs(dif.first) > 1 || abs(dif.second) > 1){
                        T -= Pair(sign(dif.first.toDouble()).toInt(),sign(dif.second.toDouble()).toInt())
                    }
                    println("$i moved to $T for a dif of $dif")
                    if(i == 9) visited.add(T)
                    rope[i-1] = H
                    rope[i] = T
                }
            }
            println()
        }
    }
    println(visited.size)
}

