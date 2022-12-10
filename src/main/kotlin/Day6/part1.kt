package Day6

import java.io.File

fun main() {
    File("src/main/kotlin/Day6/input1.txt").readLines().forEach {
        println(it.getIndex())
    }
}

fun String.getIndex() : Int{
    var i = 0;
    var first = this[i+0]
    var second = this[i+1]
    var third = this[i+2]
    var forth = this[i+3]
    while(first == second || first == third || first == forth || third == second || second == forth || forth == third){
        i++
        first = this[i+0]
        second = this[i+1]
        third = this[i+2]
        forth = this[i+3]
    }
    return i + 4
}