package Day5

import java.io.File

fun main() {
    val lines = File("src/main/kotlin/Day5/input1.txt.txt").readLines()
    val cargoInput = lines.filter { it.isNotEmpty() && it[0] != 'm' && it[1] != '1' }
    val moveInput = lines.filter { it.isNotEmpty() && it[0] == 'm' && it[1] != '1' }
    val cargoAmount = lines.find { it[1] == '1' }?.max().let { if (it == null) 0 else Character.digit(it, 10) }
    val cargo = mutableListOf<ArrayDeque<Char>>()
    for (i in 1..cargoAmount) cargo.add(ArrayDeque())
    for (line in cargoInput) {
        for (i in 1..cargoAmount) {
            if (line.getOrNull(1 + (i - 1) * 4) != null && line[1 + (i - 1) * 4] != ' ') cargo[i - 1].add(line[1 + (i - 1) * 4])
        }
    }
    for(stack in cargo){
        stack.reverse()
    }
    val cargoToSort = Cargo(cargo)
    prettyPrint(cargoToSort)
    for (moves in moveInput){
        val move = moves.split(" ")
        cargoToSort.move(move[1].toInt(),move[3].toInt()-1,move[5].toInt()-1)
        prettyPrint(cargoToSort)
    }
    for(stack in cargo){
        print(stack.first())
    }
}

data class Cargo(val stacks: MutableList<ArrayDeque<Char>>) {
    fun move(x: Int, from: Int, to: Int) {
        for (i in 1..x) {
            move(from, to)
        }
    }

    private fun move(from: Int, to: Int) {
        stacks[to].add(stacks[from].removeLast())
    }
}

fun prettyPrint(cargo: Cargo){
    val size = cargo.stacks.map { it.size }.max()
    for(i in size-1 downTo   0){
        for(list in cargo.stacks){
            val c = list.getOrNull(i)
            if(c == null){
                print("    ")
            } else{
                print("[$c] ")
            }
        }
        println()
    }
    println()
}