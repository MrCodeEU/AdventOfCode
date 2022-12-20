package day1to10.Day3

import java.io.File
import java.lang.Error

fun main() {
    println(File("src/main/kotlin/Day3/input1.txt.txt").readLines().sumOf {
        val line = it.chunked(it.length / 2)
        line[0].toCharArray().intersect(line[1].toCharArray().asIterable().toSet()).first().getPriority()
    })
}

fun Char.getPriority(): Int{
    if(this.isLowerCase()){
        for((i,c) in ('a'..'z').withIndex()){
            if (this == c) return i+1
        }
    } else {
        for((i,c) in ('A'..'Z').withIndex()){
            if (this == c) return i+27
        }
    }
    throw Error("char: $this was not found in a-z,A-Z")
}