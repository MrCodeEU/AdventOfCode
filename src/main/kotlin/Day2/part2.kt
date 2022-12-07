package Day2

import java.io.File
import java.lang.Error

fun main() {
    println(File("src/main/kotlin/Day2/input1.txt").readLines().sumOf { it.getScore2() })
}

fun String.getScore2() : Int{
    val f = this[0]
    val s = this[2]
    val toPlay = if((f == 'A' && s == 'Y') || (f == 'B' && s == 'X') || (f == 'C' && s == 'Z')) 'X'
    else if((f == 'A' && s == 'Z') || (f == 'B' && s == 'Y') || (f == 'C' && s == 'X')) 'Y' else 'Z'
    return when (s) {
        'X' -> 0
        'Y' -> 3
        'Z' -> 6
        else -> 0
    } + toPlay.getScore()
}