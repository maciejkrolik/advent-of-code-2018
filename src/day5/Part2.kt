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

    chars.reactPolymer()

    val letterWithNumberOfProblems = mutableMapOf<Int, Int>()

    for (letter in 65..90) {
        val testChars = chars.toMutableList()
        testChars.removeAll(listOf(letter.toChar(), (letter + 32).toChar()))
        testChars.reactPolymer()
        letterWithNumberOfProblems[letter] = testChars.size
    }

    println(letterWithNumberOfProblems.values.min())
}

private fun MutableList<Char>.reactPolymer() {
    var areAllUnitsDeleted = false
    while (!areAllUnitsDeleted) {
        areAllUnitsDeleted = true
        for ((index, char) in this.withIndex()) {
            val previousChar: Char? = try {
                this[index - 1]
            } catch (e: IndexOutOfBoundsException) {
                null
            }

            if (char == previousChar?.plus(32) || char == previousChar?.minus(32)) {
                this.removeAt(index)
                this.removeAt(index - 1)
                areAllUnitsDeleted = false
                break
            }
        }
    }
}