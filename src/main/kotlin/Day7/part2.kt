package Day7

import java.io.File

fun main() {
    val commands = File("src/main/kotlin/Day7/input1.txt").readLines().iterator()
    val fs = FS()
    var cwd = fs.root
    var command = commands.next()
    while (commands.hasNext()) {
        val args = command.split(" ")
        if (args[1] == "cd") {
            cwd = if (args[2] == "/") fs.root
            else if (args[2] == "..") cwd.parent ?: fs.root
            else fs.getDir(args[2])!!
        } else if (args[1] == "ls") {
        } else {
            if (args[0] == "dir") fs.addDirectory(cwd, args[1], mutableListOf())
            else cwd.children.add(FSFile(args[0].toInt(), args[1], cwd))
        }
        command = commands.next()
    }
    println(fs.root.getDirs().map{it.getSize()}.filter { it >= (30000000 - (70000000 - fs.root.getSize())) }.min())
}