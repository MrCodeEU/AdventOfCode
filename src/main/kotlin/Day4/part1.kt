package Day4

import java.io.File

fun main() {
    println(File("src/main/kotlin/Day4/input1.txt.txt").readLines().map {
        val line = it.split(",")
        val range1 = line[0].split("-")
        val range2 = line[1].split("-")
        Pair(range1[0].toInt()..range1[1].toInt(),range2[0].toInt()..range2[1].toInt())
    }.map {
        val intersect = it.first.intersect(it.second)
        if(intersect == it.first.toSet() || intersect == it.second.toSet()) 1 else 0
    }.sum())
}