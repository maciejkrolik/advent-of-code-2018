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
    val listOfTimesByGuard = mutableMapOf<Int, Int>()

    var previousDateTime = LocalDateTime.now()
    var guardNumber = 0
    listOfSortedMoments.forEach { key, value ->
        if (value.startsWith('G')) {
            guardNumber = value.substringAfter('#').substringBefore('b').trim().toInt()
            listOfTimesByGuard.putIfAbsent(guardNumber, 0)
        }
        if (value.startsWith('f')) {
            previousDateTime = key
        }
        if (value.startsWith('w')) {
            val diff = key.minute - previousDateTime.minute
            listOfTimesByGuard[guardNumber] = listOfTimesByGuard[guardNumber]!!.plus(diff)
        }
    }

    var time = 0
    var guardNumberWithMax = 0
    listOfTimesByGuard.forEach { key, value ->
        if (value >= time) {
            time = value
            guardNumberWithMax = key
        }
    }

    println("Guard number: $guardNumberWithMax")

    val minutes = mutableMapOf<Int, Int>()
    var isBadGuard = false
    listOfSortedMoments.forEach { key, value ->
        if (value.startsWith('G')) {
            guardNumber = value.substringAfter('#').substringBefore('b').trim().toInt()
            isBadGuard = guardNumber == guardNumberWithMax
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
    var mostPopularMinute = 0
    minutes.forEach { key, value ->
        if (value >= maxMinute) {
            maxMinute = value
            mostPopularMinute = key
        }
    }

    println("Most popular minute: $mostPopularMinute")
    println("Answer: ${guardNumberWithMax * mostPopularMinute}")
}