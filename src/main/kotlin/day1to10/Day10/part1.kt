package day1to10.Day10

import java.io.File
var cycleNr = 0;
var x = 1
var signals = mutableListOf<Int>()
fun main() {
    val commands = File("src/main/kotlin/Day10/input1.txt").readLines().map { it.split(" ") }
    for(command in commands){
        when(command[0]){
            "noop" -> increaseCycle()
            "addx" -> {
                increaseCycle()
                increaseCycle()
                x += command[1].toInt()
            }
        }
    }
    println(signals.sum())
}

fun increaseCycle(){
    cycleNr++
    println("Cycle: $cycleNr, X: $x")
    if(cycleNr == 20 ||((cycleNr-20) % 40) == 0) signals.add(cycleNr * x)
}