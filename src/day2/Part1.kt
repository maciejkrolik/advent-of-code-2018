package day2

import java.io.File

fun main() {
    var twos = 0
    var threes = 0

    File("src/day2/input.txt").forEachLine { line ->
        val lineDictionary = mutableMapOf<Char, Int>()

        line.forEach { char ->
            if (lineDictionary.containsKey(char)) {
                val value = lineDictionary[char]
                if (value != null) {
                    lineDictionary[char] = value + 1
                }
            } else {
                lineDictionary[char] = 1
            }
        }

        for (value in lineDictionary.values) {
            if (value == 2) {
                twos++
                break
            }
        }

        for (value in lineDictionary.values) {
            if (value == 3) {
                threes++
                break
            }
        }
    }

    println("Twos: $twos")
    println("Threes: $threes")
    println("Answer: ${twos * threes}")
}