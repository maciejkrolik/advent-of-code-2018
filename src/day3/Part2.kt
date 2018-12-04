package day3

import java.io.File

fun main() {
    val file = File("src/day3/input.txt")
    val schema: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()

    for (i in 0..1200) {
        for (j in 0..1200) {
            val coordinates = Pair(i, j)
            schema[coordinates] = 0
        }
    }

    file.forEachLine { line ->
        val leftMargin = getLeftMargin(line)
        val upperMargin = getUpperMargin(line)
        val width = getWidth(line)
        val height = getHeight(line)

        for (i in leftMargin until leftMargin + width) {
            for (j in upperMargin until upperMargin + height) {
                val coordinates = Pair(i, j)
                val value = schema[coordinates]
                schema[coordinates] = value!! + 1
            }
        }
    }

    file.forEachLine { line ->
        val leftMargin = getLeftMargin(line)
        val upperMargin = getUpperMargin(line)
        val width = getWidth(line)
        val height = getHeight(line)

        var numberOfFreeSquareInches = 0

        for (i in leftMargin until leftMargin + width) {
            for (j in upperMargin until upperMargin + height) {
                val coordinates = Pair(i, j)
                val value = schema[coordinates]
                if (value == 1) {
                    numberOfFreeSquareInches++
                }
            }
        }

        if (numberOfFreeSquareInches == width * height) {
            println(line)
        }
    }
}

private fun getLeftMargin(line: String) = line.substringAfterLast('@').substringBeforeLast(',').trim().toInt()
private fun getUpperMargin(line: String) = line.substringAfterLast(',').substringBeforeLast(':').trim().toInt()
private fun getWidth(line: String) = line.substringAfterLast(':').substringBeforeLast('x').trim().toInt()
private fun getHeight(line: String) = line.substringAfterLast('x').trim().toInt()