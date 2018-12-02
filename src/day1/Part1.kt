package day1

import java.io.File

fun main() {
    var counter = 0

    File("src/day1/part1_input.txt").forEachLine {
        counter += it.toInt()
    }

    println(counter)
}