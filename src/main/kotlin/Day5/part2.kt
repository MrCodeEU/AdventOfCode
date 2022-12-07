import java.io.File

fun main() {
    File("src/main/kotlin/Day5/input1.txt").readText().split("\r\n\r\n").let { (stackIn, movesIn) ->
        stackIn.lines().let { stkRows ->
            List(stkRows.size) { i -> stkRows[i].chunked(4).map { if (it.isBlank()) null else it[1] } }.let { tbl ->
                List(tbl.last().size) { x -> tbl.dropLast(1).mapNotNull { it.getOrNull(x) }.toMutableList() }
            }
        }.apply {
            movesIn.lineSequence().forEach { str ->
                str.split(' ').drop(1)
                    .let { (move, _, from, _, to) ->
                        repeat(move.toInt()) {
                            this[to.toInt() - 1].add(
                                it,
                                this[from.toInt() - 1].removeFirst()
                            )
                        }
                    }
            }
        }
    }.forEach { print(it.first()) }.also { println() }
}