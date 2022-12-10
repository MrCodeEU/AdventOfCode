package Day6

import java.io.File

fun main() {
    File("src/main/kotlin/Day6/input1.txt").readLines().forEach {
        println(it.getIndex2())
    }
}

private fun String.getIndex2(): Int {
    for(c in 1..this.length){
        if(this.isDistinct(c)) return c + 14
    }
    return -1
}

fun String.isDistinct(i: Int, n: Int = 14): Boolean {
    // ignore inbounds checks
    val set = mutableSetOf<Char>()
    for (x in i until i + n) {
        if (!set.add(this[x])) return false
    }
    return true
}