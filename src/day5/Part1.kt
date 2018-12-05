package day5

import java.io.File

fun main() {
    val file = File("src/day5/input.txt")

    val chars = mutableListOf<Char>()

    file.forEachLine { line ->
        line.forEach { char ->
            chars.add(char)
        }
    }

    var areAllUnitsDeleted = false
    while (!areAllUnitsDeleted) {
        areAllUnitsDeleted = true
        for ((index, char) in chars.withIndex()) {
            val previousChar: Char? = try {
                chars[index - 1]
            } catch (e: IndexOutOfBoundsException) {
                null
            }

            if (char == previousChar?.plus(32) || char == previousChar?.minus(32)) {
                chars.removeAt(index)
                chars.removeAt(index - 1)
                areAllUnitsDeleted = false
                break
            }
        }
    }

    println(chars.size)
}