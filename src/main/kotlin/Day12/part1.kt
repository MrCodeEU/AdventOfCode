package Day12

import java.io.File

fun main() {
    //read input file as lines of chars and save them as 2d array
    val input = File("src/main/kotlin/Day12/input1.txt").readLines().map { it.toCharArray() }.toTypedArray()
    // get position of S and E
    var s = Pair(0,0)
    var e = Pair(0,0)
    input.forEachIndexed{ i,l ->
        l.forEachIndexed{ j, c ->
            if(c == 'S'){
                s = Pair(i,j)
                input[i][j] = 'a'
            }
            if(c == 'E'){
                e = Pair(i,j)
                input[i][j] = 'z'
            }
        }
    }
    println("Start BFS from $s to $e")
    println((bfs(input,s,e)?.size ?: error("Help something wrong"))-1)
}

fun bfs(heightMap: Array<CharArray>, start: Pair<Int, Int>, end: Pair<Int, Int>): List<Pair<Int, Int>>? {
    val visited = mutableSetOf<Pair<Int, Int>>()
    val queue = ArrayDeque<Pair<Int, Int>>()
    val parent = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()
    visited.add(start)
    queue.add(start)

    val dx = intArrayOf(-1, 0, 1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    while (queue.isNotEmpty()) {
        //println(visited)
        val (x, y) = queue.removeFirst()
        if (Pair(x, y) == end) {
            val path = mutableListOf<Pair<Int, Int>>()
            var curr = end
            while (curr != start) {
                path.add(0, curr)
                curr = parent[curr]!!
            }
            path.add(0, start)
            return path
        }
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx >= 0 && nx < heightMap.size && ny >= 0 && ny < heightMap[0].size) {
                val c1 = heightMap[x][y]
                val c2 = heightMap[nx][ny]
                if (c2 - c1 <= 1 && visited.add(Pair(nx, ny))) {
                    parent[Pair(nx, ny)] = Pair(x, y)
                    queue.add(Pair(nx, ny))
                }
            }
        }
    }
    return null
}