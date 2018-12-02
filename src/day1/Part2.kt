package day1

import java.io.File

fun main() {
    val file = File("src/day1/part1_input.txt")

    var currentFrequency = 0
    var found = false
    val listOfFrequencies: MutableList<Int> = mutableListOf(0)

    while (true) {

        file.useLines { lines ->
            for (it in lines) {
                currentFrequency += it.toInt()
                if (listOfFrequencies.contains(currentFrequency)) {
                    println(currentFrequency)
                    found = true
                    break

                } else {
                    listOfFrequencies.add(currentFrequency)
                }
            }
        }

        if (found) {
            return
        }

    }
}