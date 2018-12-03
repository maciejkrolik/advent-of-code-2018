package day3

import java.io.File

fun main() {
    val schema: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()

    var doubledSquareInches = 0

    for (i in 0..1200) {
        for (j in 0..1200) {
            val coordinates = Pair(i, j)
            schema[coordinates] = 0
        }
    }

    File("src/day3/input.txt").forEachLine { line ->
        val leftMargin = line.substringAfterLast('@').substringBeforeLast(',').trim().toInt()
        val upperMargin = line.substringAfterLast(',').substringBeforeLast(':').trim().toInt()
        val width = line.substringAfterLast(':').substringBeforeLast('x').trim().toInt()
        val height = line.substringAfterLast('x').trim().toInt()

        for (i in leftMargin until leftMargin + width) {
            for (j in upperMargin until upperMargin + height) {
                val coordinates = Pair(i, j)
                val value = schema[coordinates]
                schema[coordinates] = value!! + 1
            }
        }
    }

    for (i in 0..1200) {
        for (j in 0..1200) {
            val coordinates = Pair(i, j)
            if (schema[coordinates]!! >= 2) {
                doubledSquareInches++
            }
        }
    }

    println(doubledSquareInches)
}