package Day3

import java.io.File

fun main() {
    println(File("src/main/kotlin/Day3/input1.txt.txt").readLines().chunked(3).sumOf {
        it[0].toCharArray().intersect(it[1].toCharArray().asIterable().toSet())
            .intersect(it[2].toCharArray().asIterable().toSet()).first().getPriority()
    })
}