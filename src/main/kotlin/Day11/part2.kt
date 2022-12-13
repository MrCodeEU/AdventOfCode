package Day11

import java.io.File

fun main() {
    val monkeyInput = File("src/main/kotlin/Day11/input1.txt").readText().split("\r\n\r\n")
    val monkeys = monkeyInput.map {
        val lines = it.split("\r\n")
        val name = lines[0].substringBefore(':').split(" ")[1].toInt()
        val items = ArrayDeque<Long>()
        lines[1].substringAfter(':').split(",").map { itemsMap -> itemsMap.replace(" ", "").toLong() }.forEach { item ->
            items.addLast(item)
        }
        val operation = lines[2].substringAfter('=').split(" ")
        val divisor = lines[3].split(" ").last().toLong()
        val trueMonkey = lines[4].split(" ").last().toInt()
        val falseMonkey = lines[5].split(" ").last().toInt()
        Monkey(name, items, operation, divisor, trueMonkey, falseMonkey)
    }
    val M = monkeys.map { it.divisor }.reduce { acc, i -> acc * i }
    for (i in 1..10_000) {
        if (i == 1 || i == 20 || i % 100 == 0) println("Starting round $i")
        monkeys.forEach {
            it.items.forEach { item ->
                //println("\tMonkey inspects item $item")
                it.inspections++
                val newItemValue = newItemValue(it, item) % M
                //println("\t\t New item value $newItemValue")
                if (newItemValue % it.divisor == 0L) {
                    //println("\t\t Is divisible by ${it.divisor}")
                    getMonkey(monkeys, it.trueMonkey).items.addLast(newItemValue)
                    //println("\t\tGets thrown to Monkey ${getMonkey(monkeys,it.trueMonkey).name}")
                } else {
                    //println("\t\t Is not divisible by ${it.divisor}")
                    getMonkey(monkeys, it.falseMoney).items.addLast(newItemValue)
                    //println("\t\tGets thrown to Monkey ${getMonkey(monkeys,it.falseMoney).name}")
                }
            }
            it.items = ArrayDeque()
            if (i == 1 || i == 20 || i % 1000 == 0) println("Monkey ${it.name}: inspected items ${it.inspections} times")
        }
    }
    val rankingMonkeys = monkeys.sortedBy { it.inspections }.reversed()
    println(rankingMonkeys[0].inspections * rankingMonkeys[1].inspections)
}