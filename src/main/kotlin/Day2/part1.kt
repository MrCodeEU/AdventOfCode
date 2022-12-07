package Day2

import java.io.File
import java.lang.Error

fun main() {
    println(File("src/main/kotlin/Day2/input1.txt").readLines().sumOf { it.getScore() })
}

fun Char.getScore(): Int {
    when (this) {
        'A', 'X' -> return 1
        'B', 'Y' -> return 2
        'C', 'Z' -> return 3
    }
    return 0
}

fun String.getScore(): Int {
    val f = this[0]
    val s = this[2]
    if (f.getScore() == s.getScore()) return 3 + s.getScore() // Draw
    if ((s == 'X' && f == 'C') || (s == 'Y' && f == 'A') || (s == 'Z' && f == 'B')) return 6 + s.getScore()
    if ((s == 'X' && f == 'B') || (s == 'Y' && f == 'C') || (s == 'Z' && f == 'A')) return 0 + s.getScore()
    throw Error("Combination elf: $f, me: $s not inside if?")
}