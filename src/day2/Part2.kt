package day2

import java.io.File

fun main() {
    val file = File("src/day2/input.txt").readLines()
    val listOfStrings = mutableMapOf<Int, Int>()
    val finalStrings = mutableListOf<String>()

    for (line in file) {
        for ((charNumber, char) in line.withIndex()) {
            for ((lineNumber, string) in file.withIndex()) {
                if (string == line) {
                    continue
                }
                if (string[charNumber] == char) {
                    if (listOfStrings.containsKey(lineNumber)) {
                        val value = listOfStrings[lineNumber]
                        if (value != null) {
                            listOfStrings[lineNumber] = value + 1
                        }
                    } else {
                        listOfStrings[lineNumber] = 1
                    }
                }
            }
        }
        if (listOfStrings.values.max() != null) {
            if (listOfStrings.values.max() as Int >= line.length - 1) {
                finalStrings.add(line)
            }
        }
        listOfStrings.clear()
    }

    print("Answer: ")
    for ((index, char) in finalStrings[0].withIndex()) {
        if (char == finalStrings[1][index]) {
            print(char)
        }
    }
}