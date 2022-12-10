package Day7

import java.io.File

fun main() {

    fun getDirSizes(input: List<String>): Map<String, Int> {
        val path = mutableListOf<String>()
        val dirSize = mutableMapOf<String, Int>()
        input.forEach { line ->
            val tokens = line.split(" ")
            if (tokens[1] == "cd") {
                if (tokens[2] == "..")
                    path.removeLast()
                else
                    path += tokens[2]
            }
            if (tokens[0][0].isDigit()) {
                path.indices.forEach { i ->

                    val absolutePath = path.take(i + 1).joinToString("/")
                    dirSize[absolutePath] = (dirSize[absolutePath] ?: 0) + tokens[0].toInt()
                }
            }
        }
        return dirSize
    }

    fun part1(input: List<String>): Int {
        return getDirSizes(input).values.sumOf { if (it <= 100000) it else 0 }
    }

    fun part2(input: List<String>): Int {
        val dirSizes = getDirSizes(input)
        return dirSizes.values.filter { it >= (30000000 - (70000000 - dirSizes["/"]!!)) }.min()
    }

    val input = File("src/main/kotlin/Day7/input1.txt").readLines()
    println(part1(input))
    println(part2(input))
}