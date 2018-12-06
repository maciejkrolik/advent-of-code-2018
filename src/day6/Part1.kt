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

    area.forEach { coords, _ ->
        var shortestDistance = 1000
        inputCoordinates.forEach { inputCoords, inputCoordsId ->
            val distance = abs(coords.first - inputCoords.first) + abs(coords.second - inputCoords.second)
            if (distance < shortestDistance) {
                shortestDistance = distance
                area[coords] = inputCoordsId.toString()
            } else if (distance == shortestDistance) {
                area[coords] = "."
            }
        }
    }

    val numberOfOccurrences: MutableMap<String, Int> = mutableMapOf()
    area.forEach { _, coordsId ->
        if (coordsId != ".") {
            if (numberOfOccurrences.containsKey(coordsId)) {
                numberOfOccurrences[coordsId] = numberOfOccurrences[coordsId]!!.plus(1)
            } else {
                numberOfOccurrences[coordsId] = 1
            }
        }
    }

    // Delete edge keys
    for (i in 0..400) {
        val coords1 = Pair(0, i)
        numberOfOccurrences.remove(area[coords1])

        val coords2 = Pair(i, 0)
        numberOfOccurrences.remove(area[coords2])

        val coords3 = Pair(400, i)
        numberOfOccurrences.remove(area[coords3])

        val coords4 = Pair(i, 400)
        numberOfOccurrences.remove(area[coords4])
    }

    println(numberOfOccurrences.values.max())
}