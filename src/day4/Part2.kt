package day4

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    val file = File("src/day4/input.txt")
    val listOfMoments = mutableMapOf<LocalDateTime, String>()

    file.forEachLine { line ->
        val dateString = line.substringAfterLast('[').substringBeforeLast(']')
        val date = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        val description = line.substringAfterLast(']').trim()
        listOfMoments[date] = description
    }

    val listOfSortedMoments = listOfMoments.toSortedMap()
    val listOfGuardsNumbers = mutableSetOf<Int>()

    listOfSortedMoments.forEach { _, value ->
        if (value.startsWith('G')) {
            listOfGuardsNumbers.add(value.substringAfter('#').substringBefore('b').trim().toInt())
        }
    }

    var previousDateTime = LocalDateTime.now()
    var guardNumber: Int
    val minutes = mutableMapOf<Int, Int>()
    var isBadGuard = false

    var mostPopularMinute: Int
    listOfGuardsNumbers.forEach { number ->
        mostPopularMinute = 0
        minutes.clear()

        listOfSortedMoments.forEach { key, value ->
            if (value.startsWith('G')) {
                guardNumber = value.substringAfter('#').substringBefore('b').trim().toInt()
                isBadGuard = guardNumber == number
            }
            if (isBadGuard) {
                if (value.startsWith('f')) {
                    previousDateTime = key
                }
                if (value.startsWith('w')) {
                    for (minute in previousDateTime.minute until key.minute) {
                        if (!minutes.containsKey(minute)) {
                            minutes[minute] = 1
                        } else {
                            minutes[minute] = minutes[minute]!!.plus(1)
                        }
                    }
                }
            }
        }

        var maxMinute = 0
        minutes.forEach { key, value ->
            if (value >= maxMinute) {
                maxMinute = value
                mostPopularMinute = key
            }
        }

        println("Guard: $number, Most popular minute: $mostPopularMinute, Number of times: ${minutes[mostPopularMinute]}")
    }
}