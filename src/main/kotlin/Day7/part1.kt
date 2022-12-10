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
    println(fs.root.getDirs().filter { it.getSize() <= 100_000 }.sumOf { it.getSize() })
}

abstract class FSNode(val name: String, var parent: FSDirectory?)
class FSFile(val size: Int, name: String, parent: FSDirectory?) : FSNode(name, parent)

class FSDirectory(val children: MutableList<FSNode>, name: String, parent: FSDirectory?) : FSNode(name, parent) {
    fun getSize(): Int {
        var sum = 0
        for (n in children) {
            when (n) {
                is FSFile -> sum += n.size
                is FSDirectory -> sum += n.getSize()
            }
        }
        return sum
    }

    fun getDir(name: String): FSDirectory? {
        for (dir in children) {
            if (dir is FSDirectory) {
                if (dir.name == name) return dir
                else {
                    val nestedDir = dir.getDir(name)
                    if (nestedDir != null) return nestedDir
                }
            }
        }
        return null
    }

    fun getDirs(): List<FSDirectory> {
        val dirs = mutableListOf<FSDirectory>()
        for (dir in children) {
            if (dir is FSDirectory) {
                dirs.add(dir)
                dirs.addAll(dir.getDirs())
            }
        }
        return dirs
    }


}

class FS {
    val root = FSDirectory(mutableListOf(), "/", null)

    fun addDirectory(directory: FSDirectory, name: String, files: MutableList<FSNode>) {
        val newDirectory = FSDirectory(files, name, directory)
        directory.children.add(newDirectory)
    }

    fun getDir(name: String): FSDirectory? {
        return root.getDir(name)
    }
}