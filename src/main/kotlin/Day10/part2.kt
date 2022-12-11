package Day10

import java.io.File
fun main() {
    val commands = File("src/main/kotlin/Day10/input1.txt").readLines().map { it.split(" ") }
    for(command in commands){
        when(command[0]){
            "noop" -> drawCycle()
            "addx" -> {
                drawCycle()
                drawCycle()
                x += command[1].toInt()
            }
        }
    }
}

fun drawCycle(){
    val pos = (cycleNr) % 40
    cycleNr++
    print(if(pos-1 == x || pos == x || pos+1 == x) '#' else '.')
    if(((cycleNr) % 40) == 0) println()
}