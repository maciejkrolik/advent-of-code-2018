package day6

import java.io.File
import kotlin.math.abs

fun main() {
    val file = File("src/day6/input.txt")

    val area: MutableMap<Pair<Int, Int>, String> = mutableMapOf()
    val inputCoordinates: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()

    for (i in 0..400) {
        for (j in 0..400) {
            val coordinates = Pair(i, j)
            area[coordinates] = 0.toString()
        }
    }

    var currentCoordsId = 0
    file.forEachLine { line ->
        currentCoordsId += 1
        val coordinates = Pair(line.split(',')[0].toInt(), line.split(',')[1].trim().toInt())
        inputCoordinates[coordinates] = currentCoordsId
        area[coordinates] = currentCoordsId.toString()
    }

    var numberOfMatchingCoords = 0

    area.forEach { coords, _ ->
        var sumOfCoords = 0
        inputCoordinates.forEach { inputCoords, _ ->
            val distance = abs(coords.first - inputCoords.first) + abs(coords.second - inputCoords.second)
            sumOfCoords += distance
        }
        if (sumOfCoords < 10000) {
            numberOfMatchingCoords++
        }
    }

    println(numberOfMatchingCoords)
}