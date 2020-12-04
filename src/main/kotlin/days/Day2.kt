package days

import java.lang.Exception
import kotlin.math.max


class Day2 : Day(2) {

    private data class Line(val num1: Int, val num2: Int, val char: Char, val pass: String)

    private val pattern = "([0-9]+)-([0-9]+) ([a-z]): ([a-z]+)".toRegex()

    private val parsed = input.map { line ->
        val groups = pattern.find(line)!!.groupValues
        Line(groups[1].toInt(), groups[2].toInt(), groups[3][0], groups[4])
    }

    override fun partOne() = parsed
        .count { (num1, num2, char, pass) ->
            pass.count { it == char } in num1..num2
        }


    override fun partTwo() = parsed
        .count { (num1, num2, char, pass) ->
            (num1 <= pass.count() && pass[num1 - 1] == char) xor (num2 <= pass.count() && pass[num2 - 1] == char)
        }
}
