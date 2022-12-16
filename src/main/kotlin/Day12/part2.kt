package Day12

import java.io.File

fun main() {
    //read input file as lines of chars and save them as 2d array
    val input = File("src/main/kotlin/Day12/input1.txt").readLines().map { it.toCharArray() }.toTypedArray()
    // get position of S and E
    val s = mutableListOf<Pair<Int,Int>>()
    var e = Pair(0,0)
    input.forEachIndexed{ i,l ->
        l.forEachIndexed{ j, c ->
            if(c == 'S' || c == 'a'){
                s.add(Pair(i,j))
                input[i][j] = 'a'
            }
            if(c == 'E'){
                e = Pair(i,j)
                input[i][j] = 'z'
            }
        }
    }
    val possibleWays = mutableListOf<Int>()
    s.forEach{
        println("Start BFS from $it to $e")
        possibleWays.add((bfs(input,it,e)?.size ?: Int.MAX_VALUE)-1)
    }
    println("shortest way: ${possibleWays.min()}")
}